package org.simplesupermarket.web.app.service;


import org.simplesupermarket.web.db.ObjectCrudMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 董文强
 * @version 1.0
 * @date 2018年12月03日
 */
public abstract class AbstractSuperServiceImpl<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSuperServiceImpl.class);

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
        if(LOGGER.isInfoEnabled()) {
            LOGGER.info(this.getClass().toString());
            LOGGER.info(this.mapper.getClass().toString());
        }
        return mapper.selectByPrimaryKey(id);
    }

    public abstract List<T> getList();


}
