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
        
        if (record.getBillcode() != null) {
            sql.VALUES("billCode", "#{billcode,jdbcType=VARCHAR}");
        }
        
        if (record.getProductname() != null) {
            sql.VALUES("productName", "#{productname,jdbcType=VARCHAR}");
        }
        
        if (record.getProductdesc() != null) {
            sql.VALUES("productDesc", "#{productdesc,jdbcType=VARCHAR}");
        }
        
        if (record.getProductunit() != null) {
            sql.VALUES("productUnit", "#{productunit,jdbcType=VARCHAR}");
        }
        
        if (record.getProductcount() != null) {
            sql.VALUES("productCount", "#{productcount,jdbcType=DECIMAL}");
        }
        
        if (record.getTotalprice() != null) {
            sql.VALUES("totalPrice", "#{totalprice,jdbcType=DECIMAL}");
        }
        
        if (record.getIspayment() != null) {
            sql.VALUES("isPayment", "#{ispayment,jdbcType=INTEGER}");
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
        
        if (record.getProviderid() != null) {
            sql.VALUES("providerId", "#{providerid,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Bill record) {
        SQL sql = new SQL();
        sql.UPDATE("smbms_bill");
        
        if (record.getBillcode() != null) {
            sql.SET("billCode = #{billcode,jdbcType=VARCHAR}");
        }
        
        if (record.getProductname() != null) {
            sql.SET("productName = #{productname,jdbcType=VARCHAR}");
        }
        
        if (record.getProductdesc() != null) {
            sql.SET("productDesc = #{productdesc,jdbcType=VARCHAR}");
        }
        
        if (record.getProductunit() != null) {
            sql.SET("productUnit = #{productunit,jdbcType=VARCHAR}");
        }
        
        if (record.getProductcount() != null) {
            sql.SET("productCount = #{productcount,jdbcType=DECIMAL}");
        }
        
        if (record.getTotalprice() != null) {
            sql.SET("totalPrice = #{totalprice,jdbcType=DECIMAL}");
        }
        
        if (record.getIspayment() != null) {
            sql.SET("isPayment = #{ispayment,jdbcType=INTEGER}");
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
        
        if (record.getProviderid() != null) {
            sql.SET("providerId = #{providerid,jdbcType=BIGINT}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}