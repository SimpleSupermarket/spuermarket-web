package org.simplesupermarket.web.db.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.simplesupermarket.web.db.model.Provider;

public class ProviderSqlProvider {

    public String insertSelective(Provider record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("smbms_provider");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getCode() != null) {
            sql.VALUES("code", "#{code,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getDesc() != null) {
            sql.VALUES("desc", "#{desc,jdbcType=VARCHAR}");
        }
        
        if (record.getContact() != null) {
            sql.VALUES("contact", "#{contact,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            sql.VALUES("phone", "#{phone,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            sql.VALUES("address", "#{address,jdbcType=VARCHAR}");
        }
        
        if (record.getFax() != null) {
            sql.VALUES("fax", "#{fax,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedby() != null) {
            sql.VALUES("createdBy", "#{createdby,jdbcType=BIGINT}");
        }
        
        if (record.getCreationdate() != null) {
            sql.VALUES("creationDate", "#{creationdate,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Provider record) {
        SQL sql = new SQL();
        sql.UPDATE("smbms_provider");
        
        if (record.getCode() != null) {
            sql.SET("code = #{code,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getDesc() != null) {
            sql.SET("desc = #{desc,jdbcType=VARCHAR}");
        }
        
        if (record.getContact() != null) {
            sql.SET("contact = #{contact,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            sql.SET("phone = #{phone,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            sql.SET("address = #{address,jdbcType=VARCHAR}");
        }
        
        if (record.getFax() != null) {
            sql.SET("fax = #{fax,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedby() != null) {
            sql.SET("createdBy = #{createdby,jdbcType=BIGINT}");
        }
        
        if (record.getCreationdate() != null) {
            sql.SET("creationDate = #{creationdate,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}