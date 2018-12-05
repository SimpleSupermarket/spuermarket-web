package org.simplesupermarket.web.db.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.simplesupermarket.web.db.model.Goods;

public class GoodsSqlProvider {

    public String insertSelective(Goods record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("smbms_goods");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getCode() != null) {
            sql.VALUES("code", "#{code,jdbcType=VARCHAR}");
        }
        
        if (record.getNamne() != null) {
            sql.VALUES("namne", "#{namne,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            sql.VALUES("price", "#{price,jdbcType=DECIMAL}");
        }
        
        if (record.getUnit() != null) {
            sql.VALUES("unit", "#{unit,jdbcType=VARCHAR}");
        }
        
        if (record.getProviderId() != null) {
            sql.VALUES("provider_id", "#{providerId,jdbcType=BIGINT}");
        }
        
        if (record.getGstock() != null) {
            sql.VALUES("gstock", "#{gstock,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedby() != null) {
            sql.VALUES("createdBy", "#{createdby,jdbcType=BIGINT}");
        }
        
        if (record.getCreationdate() != null) {
            sql.VALUES("creationDate", "#{creationdate,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Goods record) {
        SQL sql = new SQL();
        sql.UPDATE("smbms_goods");
        
        if (record.getCode() != null) {
            sql.SET("code = #{code,jdbcType=VARCHAR}");
        }
        
        if (record.getNamne() != null) {
            sql.SET("namne = #{namne,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            sql.SET("price = #{price,jdbcType=DECIMAL}");
        }
        
        if (record.getUnit() != null) {
            sql.SET("unit = #{unit,jdbcType=VARCHAR}");
        }
        
        if (record.getProviderId() != null) {
            sql.SET("provider_id = #{providerId,jdbcType=BIGINT}");
        }
        
        if (record.getGstock() != null) {
            sql.SET("gstock = #{gstock,jdbcType=INTEGER}");
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