package org.simplesupermarket.web.app.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Time 2018/12/3 22:50
 */
public class SearchData {
    private Integer pageSize; //每页大小
    private Integer sumPage;  //总页数
    private Integer currPage; //当前页
    private Long count;    //总记录数
    private String search; //查询条件
    private List data; //返回数据

    @Override
    public String toString() {
        return "SearchData{" +
                "pageSize=" + pageSize +
                ", sumPage=" + sumPage +
                ", currPage=" + currPage +
                ", count=" + count +
                ", search=" + search +
                ", data=" + data +
                '}';
    }

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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
