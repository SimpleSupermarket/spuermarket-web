package org.simplesupermarket.web.db.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.simplesupermarket.web.db.model.Bill;

public class BillSqlProvider {

    public String insertSelective(Bill record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("smbms_bill");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getCode() != null) {
            sql.VALUES("code", "#{code,jdbcType=VARCHAR}");
        }
        
        if (record.getGoodsId() != null) {
            sql.VALUES("goods_id", "#{goodsId,jdbcType=BIGINT}");
        }
        
        if (record.getGoodsCount() != null) {
            sql.VALUES("goodsCount", "#{goodscount,jdbcType=INTEGER}");
        }
        
        if (record.getTotalPrice() != null) {
            sql.VALUES("totalPrice", "#{totalprice,jdbcType=DECIMAL}");
        }
        
        if (record.getIsPayment() != null) {
            sql.VALUES("isPayment", "#{ispayment,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedby() != null) {
            sql.VALUES("createdBy", "#{createdby,jdbcType=BIGINT}");
        }
        
        if (record.getCreationdate() != null) {
            sql.VALUES("creationDate", "#{creationdate,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Bill record) {
        SQL sql = new SQL();
        sql.UPDATE("smbms_bill");
        
        if (record.getCode() != null) {
            sql.SET("code = #{code,jdbcType=VARCHAR}");
        }
        
        if (record.getGoodsId() != null) {
            sql.SET("goods_id = #{goodsId,jdbcType=BIGINT}");
        }
        
        if (record.getGoodsCount() != null) {
            sql.SET("goodsCount = #{goodscount,jdbcType=INTEGER}");
        }
        
        if (record.getTotalPrice() != null) {
            sql.SET("totalPrice = #{totalprice,jdbcType=DECIMAL}");
        }
        
        if (record.getIsPayment() != null) {
            sql.SET("isPayment = #{ispayment,jdbcType=INTEGER}");
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