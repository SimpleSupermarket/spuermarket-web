package org.simplesupermarket.web.app.controller.common;

import org.simplesupermarket.web.app.service.AbstractSuperServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @date 2018年12月03日
 */
public abstract class AbstractSuperController<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSuperController.class);

    @Autowired
    protected AbstractSuperServiceImpl<T> service;

    @PostMapping
    public Boolean add(T data) {
        return service.add(data);
    }

    @DeleteMapping
    public Boolean delete(@RequestParam("id") Long id) {
        return service.delete(id);
    }

    @PatchMapping
    public Boolean update(T data) {
        return service.update(data);
    }

    @GetMapping
    public T get(Long id) {
       return this.service.get(id);
    }

    @GetMapping("s")
    public List<T> list(SearchData searchData) {
        if(LOGGER.isDebugEnabled()){
            LOGGER.info("{}查询 {}",this.getClass().getSimpleName(),searchData);
        }
        return new LinkedList<>();
    }
}
