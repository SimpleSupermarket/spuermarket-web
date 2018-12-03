package org.simplesupermarket.web.app.controller;

import org.simplesupermarket.web.app.service.AbstractSuperServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 董文强
 * @version 1.0
 * @date 2018年12月03日
 */
public abstract class AbstractSuperController<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSuperController.class);

    protected AbstractSuperServiceImpl<T> service;
    protected AbstractSuperController(Object service){
        if(service instanceof  AbstractSuperServiceImpl) {
            this.service = (AbstractSuperServiceImpl<T>) service;
        }
    }

    @PostMapping
    public Boolean add(T data) {
        LOGGER.info(this.getClass().toString());
        return false;
    }

    @DeleteMapping
    public Boolean delete(Long id) {
        return false;
    }

    @PatchMapping
    public Boolean update(T data) {
        return false;
    }

    @GetMapping
    public T get(Long id) {
        if(LOGGER.isInfoEnabled()) {
            LOGGER.info(this.getClass().toString());
            LOGGER.info(this.service.getClass().toString());
        }
       return this.service.get(id);
    }

    @GetMapping("s")
    public List<T> list() {
        return new LinkedList<>();
    }
}
