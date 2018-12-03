package org.simplesupermarket.web.db.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.simplesupermarket.web.db.ObjectCrudMapper;
import org.simplesupermarket.web.db.model.Address;
import org.simplesupermarket.web.db.model.Bill;
import org.springframework.stereotype.Repository;


@Repository
public interface BillMapper extends ObjectCrudMapper<Bill> {
    @Delete({
        "delete from smbms_bill",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into smbms_bill (id, billCode, ",
        "productName, productDesc, ",
        "productUnit, productCount, ",
        "totalPrice, isPayment, ",
        "createdBy, creationDate, ",
        "modifyBy, modifyDate, ",
        "providerId)",
        "values (#{id,jdbcType=BIGINT}, #{billcode,jdbcType=VARCHAR}, ",
        "#{productname,jdbcType=VARCHAR}, #{productdesc,jdbcType=VARCHAR}, ",
        "#{productunit,jdbcType=VARCHAR}, #{productcount,jdbcType=DECIMAL}, ",
        "#{totalprice,jdbcType=DECIMAL}, #{ispayment,jdbcType=INTEGER}, ",
        "#{createdby,jdbcType=BIGINT}, #{creationdate,jdbcType=TIMESTAMP}, ",
        "#{modifyby,jdbcType=BIGINT}, #{modifydate,jdbcType=TIMESTAMP}, ",
        "#{providerid,jdbcType=BIGINT})"
    })
    int insert(Bill record);


    @Select({
        "select",
        "id, billCode, productName, productDesc, productUnit, productCount, totalPrice, ",
        "isPayment, createdBy, creationDate, modifyBy, modifyDate, providerId",
        "from smbms_bill",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="billCode", property="billcode", jdbcType=JdbcType.VARCHAR),
        @Result(column="productName", property="productname", jdbcType=JdbcType.VARCHAR),
        @Result(column="productDesc", property="productdesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="productUnit", property="productunit", jdbcType=JdbcType.VARCHAR),
        @Result(column="productCount", property="productcount", jdbcType=JdbcType.DECIMAL),
        @Result(column="totalPrice", property="totalprice", jdbcType=JdbcType.DECIMAL),
        @Result(column="isPayment", property="ispayment", jdbcType=JdbcType.INTEGER),
        @Result(column="createdBy", property="createdby", jdbcType=JdbcType.BIGINT),
        @Result(column="creationDate", property="creationdate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifyBy", property="modifyby", jdbcType=JdbcType.BIGINT),
        @Result(column="modifyDate", property="modifydate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="providerId", property="providerid", jdbcType=JdbcType.BIGINT)
    })
    Bill selectByPrimaryKey(Long id);


    @Update({
        "update smbms_bill",
        "set billCode = #{billcode,jdbcType=VARCHAR},",
          "productName = #{productname,jdbcType=VARCHAR},",
          "productDesc = #{productdesc,jdbcType=VARCHAR},",
          "productUnit = #{productunit,jdbcType=VARCHAR},",
          "productCount = #{productcount,jdbcType=DECIMAL},",
          "totalPrice = #{totalprice,jdbcType=DECIMAL},",
          "isPayment = #{ispayment,jdbcType=INTEGER},",
          "createdBy = #{createdby,jdbcType=BIGINT},",
          "creationDate = #{creationdate,jdbcType=TIMESTAMP},",
          "modifyBy = #{modifyby,jdbcType=BIGINT},",
          "modifyDate = #{modifydate,jdbcType=TIMESTAMP},",
          "providerId = #{providerid,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Bill record);
}