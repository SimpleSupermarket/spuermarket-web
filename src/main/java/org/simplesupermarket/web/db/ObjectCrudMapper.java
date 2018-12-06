package org.simplesupermarket.web.db;


import java.util.List;

public interface ObjectCrudMapper<T> {
    int deleteByPrimaryKey(Long id);
    int insert(T record);
    T selectByPrimaryKey(Long id);
    int updateByPrimaryKey(T record);
    List<T> selectAll();
    List<T> selectByIds(List<Long> ids);
}
