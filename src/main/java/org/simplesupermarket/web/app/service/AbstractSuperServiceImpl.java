package org.simplesupermarket.web.app.service;


import org.simplesupermarket.web.app.exception.BusinessException;
import org.simplesupermarket.web.app.domain.annotation.FromDb;
import org.simplesupermarket.web.app.service.datahandle.DataHandle;
import org.simplesupermarket.web.app.domain.annotation.ViewClass;
import org.simplesupermarket.web.app.service.datahandle.standard.StandardDataHandle;
import org.simplesupermarket.web.auth.UserDetail;
import org.simplesupermarket.web.db.ObjectCrudMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.management.RuntimeErrorException;
import java.lang.reflect.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0
 * @date 2018年12月03日
 */
public abstract class AbstractSuperServiceImpl<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSuperServiceImpl.class);

    protected final DateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时");

    public AbstractSuperServiceImpl() {
        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));  // 设置北京时区
    }

    @Autowired
    protected ObjectCrudMapper<T> mapper;


    public Boolean add(T data, UserDetail userDetail) {
        if (userDetail == null) return false;
        data = this.getDataHandle().handleData(data, userDetail);
        return mapper.insert(data) == 1;
    }


    /**
     * 可以在子类中自己定义数据添加的预处理类
     */
    protected DataHandle getDataHandle() {
        return new StandardDataHandle();
    }


    public Boolean delete(Long id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public Boolean update(T data) {
        return mapper.updateByPrimaryKey(data) == 1;
    }

    public T get(Long id) {
        if (LOGGER.isInfoEnabled()) {
        }
        return mapper.selectByPrimaryKey(id);
    }

    public List getList(Map<String, String> sd) {
        List<T> list = getDbData(sd);
        if (list == null || list.isEmpty()) {
            return null;
        }
        try {
            return model2View(list);
        } catch (Exception e) {
            LOGGER.error("转换异常{}", e.getLocalizedMessage());
            return list;
        }
    }

    /**
     * 需要子类自己实现获取数据库数据
     */
    abstract protected List<T> getDbData(Map<String, String> sd);

    //将数据转换成视图
    protected List model2View(List<T> data) {
        if (!needTransform(data)) {
            return data;
        }
        List viewList = new Vector(data.size());
        Map viewMap = new ConcurrentHashMap(data.size());
        // view中的Field对应的映射
        Map<Field, Map<Object, List>> fieldMapMap = new ConcurrentHashMap<>();
        for (Field viewField : viewFieldByModelField.keySet()) {
            fieldMapMap.put(viewField, new ConcurrentHashMap<>());
        }
        data.forEach(dataItem -> {
            Object view = dataObjectToViewObject(dataItem, viewClass);
            if (view == null) {
                //无法构建view对象
                viewList.add(dataItem);
                return;
            }
            viewList.add(view);
            if (!viewFieldByModelField.isEmpty()) {
                try {
                    Object id = this.modelGetId.get(dataItem);
                    if (id == null) return;
                    viewMap.put(id, view);
                    for (Field viewField : viewFieldByModelField.keySet()) {
                        Field dataField = viewFieldByModelField.get(viewField);
                        Object mapperId = dataField.get(dataItem);
                        if (mapperId == null) continue;
                        Map<Object, List> modelidTviewid = fieldMapMap.get(viewField);
                        List userIds = modelidTviewid.get(mapperId);
                        if (userIds == null) {
                            userIds = new ArrayList();
                            modelidTviewid.put(mapperId, userIds);
                        }
                        userIds.add(id);
                    }
                } catch (IllegalAccessException e) {
                    LOGGER.error("转换异常{}", dataItem);
                }
            }
        });

        if (!fieldMapMap.isEmpty()) {
            fieldMapMap.forEach((field, modelidTviewid) -> {
                try {
                    fillViewData(viewMap, modelidTviewid, field.getType(), field);
                } catch (NoSuchFieldException e) {
                    LOGGER.error("填充映射数据异常{}", field.getName());
                }
            });
        }

        return viewList;
    }

    /**
     * 获取视图类，如果没有指定则返回NULL
     */
    private Class getViewClass() {
        if (viewClass != null) return viewClass;
        Method getDbDataMethod = null;
        try {
            getDbDataMethod = this.getClass().getMethod("getDbData", Map.class);
        } catch (NoSuchMethodException e) {
            LOGGER.error("NO - getDbData @Override");
            throw new RuntimeErrorException(new Error("NO - getDbData @Override"));
        }
        ViewClass getViewClass = getDbDataMethod.getAnnotation(ViewClass.class);
        if (getViewClass == null) {
            return null;
        } else {
            viewClass = getViewClass.value();
        }
        return viewClass;
    }

    /**
     * 《viewField,modelField》
     */
    private Map<Field, Field> getMapperField() {
        if (viewFieldByModelField != null) return viewFieldByModelField;
        viewFieldByModelField = new HashMap<>();
        Field[] viewfields = viewClass.getDeclaredFields();
        for (Field viewfield : viewfields) {
            if (viewfield.getAnnotation(FromDb.class) != null) {
                viewfield.setAccessible(true);
                String mapName = viewfield.getAnnotation(FromDb.class).value();
                try {
                    if (mapName != null && !mapName.isEmpty()) {
                        Field modelFeild = modelClass.getDeclaredField(mapName);
                        modelFeild.setAccessible(true);
                        viewFieldByModelField.put(viewfield, modelFeild);
                    } else {
                        Field modelFeild = modelClass.getDeclaredField(viewfield.getName());
                        modelFeild.setAccessible(true);
                        viewFieldByModelField.put(viewfield, modelFeild);
                    }
                } catch (NoSuchFieldException e) {
                    LOGGER.error("{}没有model对应属性{}", viewfield.getName(), mapName);
                }
            }
        }
        if (!viewFieldByModelField.isEmpty()) {
            try {
                modelGetId = modelClass.getDeclaredField("id");
                modelGetId.setAccessible(true);
            } catch (NoSuchFieldException e) {
                //如果出了问题，说明你的model中得加一个id属性;
                throw new RuntimeException();
            }
        }
        return viewFieldByModelField;
    }

    private Field modelGetId = null;
    private Class viewClass = null;
    private Class modelClass = null;
    private Map<Field, Field> viewFieldByModelField = null; //《viewField,modelField》
    private Boolean needTransform = true;

    private Boolean needTransform(List data) {
        if (!needTransform) {
            return false;
        }
        if (modelClass == null) {
            modelClass = data.get(0).getClass();
        }
        if (getViewClass() == null) {
            viewClass = modelClass;
            needTransform = false;
            return false;
        }
        if (modelClass == viewClass) return false;
        getMapperField();
        return needTransform;
    }


    @Autowired
    private ApplicationContext applicationContext;


    /**
     * @param viewData       将要转换的对象的Map <Integer:id-Object:data></id-data>
     * @param modelidTviewid view属性中和mapper的映射Map <Integer:fileId - List:Integer:viewId>
     */
    private void fillViewData(Map viewData, Map<? extends Object, List> modelidTviewid, Class modelClass, Field viewFiled) throws NoSuchFieldException {
        String modelName = modelClass.getSimpleName();
        String mapperBeanName = modelName.substring(0, 1).toLowerCase() + modelName.substring(1) + "Mapper";
        ObjectCrudMapper objectCrudMapper = applicationContext.getBean(mapperBeanName, ObjectCrudMapper.class);
        Field modelGetId = modelClass.getDeclaredField("id");
        modelGetId.setAccessible(true);
        viewFiled.setAccessible(true);
        objectCrudMapper.selectByIds(
                Arrays.asList(modelidTviewid.keySet().toArray(new Long[0])))
                .forEach(obj -> {
                    try {
                        Object objId = modelGetId.get(obj);
                        List viewIds = modelidTviewid.get(objId);
                        viewIds.forEach(id -> {
                            Object view = viewData.get(id);
                            try {
                                viewFiled.set(view, obj);
                            } catch (IllegalAccessException e) {
                            }
                        });
                    } catch (IllegalAccessException e) {
                        LOGGER.error("{}", e.fillInStackTrace());
                    }

                });

    }


    private Constructor viewConstructor = null;
    private Boolean hasParam = true;

    protected Object dataObjectToViewObject(Object v, Class viewClass) {
        if (viewConstructor == null) {
            Class dataClass = v.getClass();
            try {
                Constructor hasParamConstructor = viewClass.getConstructor(dataClass);
                hasParam = true;
                this.viewConstructor = hasParamConstructor;
            } catch (NoSuchMethodException e) {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn("{}没有model类转view的有参构造函数public {}({} data)", viewClass.getSimpleName(), viewClass.getSimpleName(), dataClass.getSimpleName());
                    LOGGER.warn("将使用默认无参构造函数");
                }
            }
            try {
                if (viewConstructor == null) {
                    Constructor noParamConstructor = viewClass.getConstructor();
                    hasParam = false;
                    this.viewConstructor = noParamConstructor;
                }
            } catch (NoSuchMethodException e) {
                //你的视图类没构造函数
                LOGGER.error("出大事了{}", e.fillInStackTrace());
            }
        }
        if (hasParam) {
            try {
                Object viewObject = this.viewConstructor.newInstance(v);
                return viewObject;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                LOGGER.error("{}", e.fillInStackTrace());
            }
        } else {
            try {
                Object viewObject = this.viewConstructor.newInstance();
                BeanUtils.copyProperties(v, viewObject);
                return viewObject;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                LOGGER.error("{}", e.fillInStackTrace());

            }
        }
        return null;
    }
}
