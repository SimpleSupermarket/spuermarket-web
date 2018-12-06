package org.simplesupermarket.web.app.service;


import org.simplesupermarket.web.auth.UserDetail;
import org.simplesupermarket.web.db.ObjectCrudMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @date 2018年12月03日
 */
public abstract class AbstractSuperServiceImpl<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSuperServiceImpl.class);

    protected final DateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时");

    @Autowired
    protected ObjectCrudMapper<T> mapper;


    public Boolean add(T data, UserDetail userDetail) {
        if (userDetail == null) return false;
        Class dataClass = data.getClass();
        try {
            Field creationdate = dataClass.getDeclaredField("creationdate");
            creationdate.setAccessible(true);
            creationdate.set(data, new Date());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LOGGER.error("设置创建时间失败");
            LOGGER.error("{}:{}", e.getMessage(), e.getLocalizedMessage());
            e.printStackTrace();
        }
        try {
            Field createdby = dataClass.getDeclaredField("createdby");
            createdby.setAccessible(true);
            createdby.set(data, userDetail.getId());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LOGGER.error("设置创建人失败");
            LOGGER.error("{}:{}", e.getMessage(), e.getLocalizedMessage());
            e.printStackTrace();
        }

        return mapper.insert(data) == 1;
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
        return this.mapper.selectAll();
    }
}
