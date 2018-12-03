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
        
        if (record.getRolecode() != null) {
            sql.VALUES("roleCode", "#{rolecode,jdbcType=VARCHAR}");
        }
        
        if (record.getRolename() != null) {
            sql.VALUES("roleName", "#{rolename,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedby() != null) {
            sql.VALUES("createdBy", "#{createdby,jdbcType=BIGINT}");
        }
        
        if (record.getCreationdate() != null) {
            sql.VALUES("creationDate", "#{creationdate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyby() != null) {
            sql.VALUES("modifyBy", "#{modifyby,jdbcType=BIGINT}");
        }
        
        if (record.getModifydate() != null) {
            sql.VALUES("modifyDate", "#{modifydate,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Role record) {
        SQL sql = new SQL();
        sql.UPDATE("smbms_role");
        
        if (record.getRolecode() != null) {
            sql.SET("roleCode = #{rolecode,jdbcType=VARCHAR}");
        }
        
        if (record.getRolename() != null) {
            sql.SET("roleName = #{rolename,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedby() != null) {
            sql.SET("createdBy = #{createdby,jdbcType=BIGINT}");
        }
        
        if (record.getCreationdate() != null) {
            sql.SET("creationDate = #{creationdate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyby() != null) {
            sql.SET("modifyBy = #{modifyby,jdbcType=BIGINT}");
        }
        
        if (record.getModifydate() != null) {
            sql.SET("modifyDate = #{modifydate,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}