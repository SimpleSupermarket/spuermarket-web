package org.simplesupermarket.web.app.controller.common;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.simplesupermarket.web.app.service.AbstractSuperServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public T get(@RequestParam("id") Long id) {
       return this.service.get(id);
    }

    @GetMapping("/list")
    public SearchData list(SearchData searchData) {
        if(LOGGER.isInfoEnabled()){
            LOGGER.info("{}查询 {}",this.getClass().getSimpleName(),searchData);

        }
        Integer page = searchData.getCurrPage();
        Integer pageSize = searchData.getPageSize();
        if(page == null || page == 0){
            page = 1;
        }
        if(pageSize == null || pageSize == 0 ){
            pageSize = 10;
        }
        Page p = PageHelper.startPage(page,pageSize);
        searchData.setData(service.getList(searchData.getSearch()));
        searchData.setCount(p.getTotal());
        searchData.setCurrPage(p.getPageNum());
        searchData.setPageSize(p.getPageSize());
        searchData.setSumPage(p.getPages());
        return searchData;
    }
}
