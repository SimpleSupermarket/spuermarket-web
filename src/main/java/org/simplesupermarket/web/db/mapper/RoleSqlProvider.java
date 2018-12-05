package org.simplesupermarket.web.db.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.simplesupermarket.web.db.model.Role;

public class RoleSqlProvider {

    public String insertSelective(Role record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("smbms_role");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getCode() != null) {
            sql.VALUES("code", "#{code,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedby() != null) {
            sql.VALUES("createdBy", "#{createdby,jdbcType=BIGINT}");
        }
        
        if (record.getCreationdate() != null) {
            sql.VALUES("creationDate", "#{creationdate,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Role record) {
        SQL sql = new SQL();
        sql.UPDATE("smbms_role");
        
        if (record.getCode() != null) {
            sql.SET("code = #{code,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
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