package org.simplesupermarket.web.db;

import org.simplesupermarket.web.db.model.Address;

public interface ObjectCrudMapper<T> {
    int deleteByPrimaryKey(Long id);
    int insert(T record);
    int insertSelective(T record);
    T selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(T record);
    int updateByPrimaryKey(T record);
}
