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
        
        if (record.getProcode() != null) {
            sql.VALUES("proCode", "#{procode,jdbcType=VARCHAR}");
        }
        
        if (record.getProname() != null) {
            sql.VALUES("proName", "#{proname,jdbcType=VARCHAR}");
        }
        
        if (record.getProdesc() != null) {
            sql.VALUES("proDesc", "#{prodesc,jdbcType=VARCHAR}");
        }
        
        if (record.getProcontact() != null) {
            sql.VALUES("proContact", "#{procontact,jdbcType=VARCHAR}");
        }
        
        if (record.getProphone() != null) {
            sql.VALUES("proPhone", "#{prophone,jdbcType=VARCHAR}");
        }
        
        if (record.getProaddress() != null) {
            sql.VALUES("proAddress", "#{proaddress,jdbcType=VARCHAR}");
        }
        
        if (record.getProfax() != null) {
            sql.VALUES("proFax", "#{profax,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedby() != null) {
            sql.VALUES("createdBy", "#{createdby,jdbcType=BIGINT}");
        }
        
        if (record.getCreationdate() != null) {
            sql.VALUES("creationDate", "#{creationdate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifydate() != null) {
            sql.VALUES("modifyDate", "#{modifydate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyby() != null) {
            sql.VALUES("modifyBy", "#{modifyby,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Provider record) {
        SQL sql = new SQL();
        sql.UPDATE("smbms_provider");
        
        if (record.getProcode() != null) {
            sql.SET("proCode = #{procode,jdbcType=VARCHAR}");
        }
        
        if (record.getProname() != null) {
            sql.SET("proName = #{proname,jdbcType=VARCHAR}");
        }
        
        if (record.getProdesc() != null) {
            sql.SET("proDesc = #{prodesc,jdbcType=VARCHAR}");
        }
        
        if (record.getProcontact() != null) {
            sql.SET("proContact = #{procontact,jdbcType=VARCHAR}");
        }
        
        if (record.getProphone() != null) {
            sql.SET("proPhone = #{prophone,jdbcType=VARCHAR}");
        }
        
        if (record.getProaddress() != null) {
            sql.SET("proAddress = #{proaddress,jdbcType=VARCHAR}");
        }
        
        if (record.getProfax() != null) {
            sql.SET("proFax = #{profax,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedby() != null) {
            sql.SET("createdBy = #{createdby,jdbcType=BIGINT}");
        }
        
        if (record.getCreationdate() != null) {
            sql.SET("creationDate = #{creationdate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifydate() != null) {
            sql.SET("modifyDate = #{modifydate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyby() != null) {
            sql.SET("modifyBy = #{modifyby,jdbcType=BIGINT}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}