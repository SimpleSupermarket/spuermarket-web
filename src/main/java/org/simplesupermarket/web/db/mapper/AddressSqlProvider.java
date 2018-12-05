package org.simplesupermarket.web.db.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.simplesupermarket.web.db.model.Address;

public class AddressSqlProvider {

    public String insertSelective(Address record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("smbms_address");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getContact() != null) {
            sql.VALUES("contact", "#{contact,jdbcType=VARCHAR}");
        }
        
        if (record.getDesc() != null) {
            sql.VALUES("desc", "#{desc,jdbcType=VARCHAR}");
        }
        
        if (record.getPostcode() != null) {
            sql.VALUES("postCode", "#{postcode,jdbcType=VARCHAR}");
        }
        
        if (record.getTel() != null) {
            sql.VALUES("tel", "#{tel,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedby() != null) {
            sql.VALUES("createdBy", "#{createdby,jdbcType=BIGINT}");
        }
        
        if (record.getCreationdate() != null) {
            sql.VALUES("creationDate", "#{creationdate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Address record) {
        SQL sql = new SQL();
        sql.UPDATE("smbms_address");
        
        if (record.getContact() != null) {
            sql.SET("contact = #{contact,jdbcType=VARCHAR}");
        }
        
        if (record.getDesc() != null) {
            sql.SET("desc = #{desc,jdbcType=VARCHAR}");
        }
        
        if (record.getPostcode() != null) {
            sql.SET("postCode = #{postcode,jdbcType=VARCHAR}");
        }
        
        if (record.getTel() != null) {
            sql.SET("tel = #{tel,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedby() != null) {
            sql.SET("createdBy = #{createdby,jdbcType=BIGINT}");
        }
        
        if (record.getCreationdate() != null) {
            sql.SET("creationDate = #{creationdate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=BIGINT}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}