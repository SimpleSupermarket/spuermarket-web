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
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
     * 可以在子类中自己定义数据的预处理类
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
        } catch (IllegalAccessException |InstantiationException e) {
            throw new BusinessException("99999",e.getMessage());
        }
    }
    abstract protected List<T> getDbData(Map<String, String> sd);

    //TODO
    protected List model2View(List<T> dbData) throws IllegalAccessException, InstantiationException {
        Method getDbDataMethod = null;
        try {
             getDbDataMethod = this.getClass().getMethod("getDbData", Map.class);
             if(getDbDataMethod == null)return null;
        } catch (NoSuchMethodException e) {
           throw new BusinessException("99999",e.getMessage());
        }
        Class viewClass = getDbDataMethod.getAnnotation(ViewClass.class).value();
        //TODO model -> view todo
        //viewClass.newInstance();
        List viewList = new ArrayList(dbData.size());
        dbData.forEach(v->{
            //id = v.getId();
        });
        //TODO view.model = model
        Map<Field,Map<Integer,Integer>> fieldMapMap = new HashMap();
        Field[] fields = viewClass.getDeclaredFields();
        for (Field field : fields) {
            if(field.getAnnotation(FromDb.class)!=null){
                fieldMapMap.put(field,new ConcurrentHashMap<>());
            }
        }


        return null;
    }


}
