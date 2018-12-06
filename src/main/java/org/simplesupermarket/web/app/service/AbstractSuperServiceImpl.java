package org.simplesupermarket.web.app.service;


import org.simplesupermarket.web.db.ObjectCrudMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @date 2018年12月03日
 */
public abstract class AbstractSuperServiceImpl<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSuperServiceImpl.class);

    protected   final DateFormat format =  new SimpleDateFormat("yyyy年MM月dd日HH时");

    @Autowired
    protected ObjectCrudMapper<T> mapper;


    public Boolean add(T data) {
        return mapper.insert(data) == 1;
    }

    public Boolean delete(Long id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public Boolean update(T data) {
        return mapper.updateByPrimaryKey(data) == 1;
    }

    public T get(Long id) {
        if(LOGGER.isInfoEnabled()) { }
        return mapper.selectByPrimaryKey(id);
    }

    public  List getList(Map<String,String> sd){
       return this.mapper.selectAll();
    }
}
