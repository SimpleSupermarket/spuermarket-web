package org.simplesupermarket.web.db.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.simplesupermarket.web.db.model.User;

public class UserSqlProvider {

    public String insertSelective(User record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("smbms_user");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getUsercode() != null) {
            sql.VALUES("userCode", "#{usercode,jdbcType=VARCHAR}");
        }
        
        if (record.getUsername() != null) {
            sql.VALUES("userName", "#{username,jdbcType=VARCHAR}");
        }
        
        if (record.getUserpassword() != null) {
            sql.VALUES("userPassword", "#{userpassword,jdbcType=CHAR}");
        }
        
        if (record.getGender() != null) {
            sql.VALUES("gender", "#{gender,jdbcType=INTEGER}");
        }
        
        if (record.getBirthday() != null) {
            sql.VALUES("birthday", "#{birthday,jdbcType=DATE}");
        }
        
        if (record.getPhone() != null) {
            sql.VALUES("phone", "#{phone,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            sql.VALUES("address", "#{address,jdbcType=VARCHAR}");
        }
        
        if (record.getUserrole() != null) {
            sql.VALUES("userRole", "#{userrole,jdbcType=BIGINT}");
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
        
        if (record.getState() != null) {
            sql.VALUES("state", "#{state,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(User record) {
        SQL sql = new SQL();
        sql.UPDATE("smbms_user");
        
        if (record.getUsercode() != null) {
            sql.SET("userCode = #{usercode,jdbcType=VARCHAR}");
        }
        
        if (record.getUsername() != null) {
            sql.SET("userName = #{username,jdbcType=VARCHAR}");
        }
        
        if (record.getUserpassword() != null) {
            sql.SET("userPassword = #{userpassword,jdbcType=CHAR}");
        }
        
        if (record.getGender() != null) {
            sql.SET("gender = #{gender,jdbcType=INTEGER}");
        }
        
        if (record.getBirthday() != null) {
            sql.SET("birthday = #{birthday,jdbcType=DATE}");
        }
        
        if (record.getPhone() != null) {
            sql.SET("phone = #{phone,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            sql.SET("address = #{address,jdbcType=VARCHAR}");
        }
        
        if (record.getUserrole() != null) {
            sql.SET("userRole = #{userrole,jdbcType=BIGINT}");
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
        
        if (record.getState() != null) {
            sql.SET("state = #{state,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}