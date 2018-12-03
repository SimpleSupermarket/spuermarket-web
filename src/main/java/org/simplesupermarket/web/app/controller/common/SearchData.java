package org.simplesupermarket.web.app.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @Time 2018/12/3 22:50
 */
public class SearchData {
    private Integer pageSize; //每页大小
    private Integer sumPage;  //总页数
    private Integer currPage; //当前页
    private Integer count;    //总记录数
    private Map search; //查询条件

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getSumPage() {
        return sumPage;
    }

    public void setSumPage(Integer sumPage) {
        this.sumPage = sumPage;
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Map getSearch() {
        return search;
    }

    public void setSearch(Map search) {
        this.search = search;
    }
}
