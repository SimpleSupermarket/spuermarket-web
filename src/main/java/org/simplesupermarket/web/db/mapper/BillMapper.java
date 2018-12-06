package org.simplesupermarket.web.db.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.simplesupermarket.web.db.ObjectCrudMapper;
import org.simplesupermarket.web.db.model.Bill;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillMapper extends ObjectCrudMapper<Bill> {
    @Delete({
            "delete from smbms_bill",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into smbms_bill (id, code, ",
            "goods_id, goodsCount, ",
            "totalPrice, isPayment, ",
            "createdBy, creationDate)",
            "values (#{id,jdbcType=BIGINT}, #{code,jdbcType=VARCHAR}, ",
            "#{goodsId,jdbcType=BIGINT}, #{goodscount,jdbcType=INTEGER}, ",
            "#{totalprice,jdbcType=DECIMAL}, #{ispayment,jdbcType=INTEGER}, ",
            "#{createdby,jdbcType=BIGINT}, #{creationdate,jdbcType=TIMESTAMP})"
    })
    int insert(Bill record);

    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "code", property = "code", jdbcType = JdbcType.VARCHAR),
            @Result(column = "goods_id", property = "goodsId", jdbcType = JdbcType.BIGINT),
            @Result(column = "goodsCount", property = "goodscount", jdbcType = JdbcType.INTEGER),
            @Result(column = "totalPrice", property = "totalprice", jdbcType = JdbcType.DECIMAL),
            @Result(column = "isPayment", property = "ispayment", jdbcType = JdbcType.INTEGER),
            @Result(column = "createdBy", property = "createdby", jdbcType = JdbcType.BIGINT),
            @Result(column = "creationDate", property = "creationdate", jdbcType = JdbcType.TIMESTAMP)
    })
    @Select({"<script>",
            "select",
            "id, code, goods_id, goodsCount, totalPrice, isPayment, createdBy, creationDate",
            "from smbms_bill", " <where> ",
            "<if test='isPayment!=null'>", "AND isPayment = #{isPayment}", "</if>",
            "<if test='goodsName!=null'>", " AND goods_id IN",
            "(SELECT id from smbms where name like CONCAT('%',#{goodsName},'%') ",
            "<if test='providerName!=null'>", " AND provider_id IN (",
            "select id from smbms_provider where name like CONCAT('%',#{providerName},'%')",
            ")",
            "</if>",
            ")",
            "</if>",
            "</where>",
            "</script>"
    })
    List<Bill> selectAll(@Param("isPayment") Integer isPayment,
                         @Param("goodsName") String goodsName,
                         @Param("providerName") String providerName);

    @InsertProvider(type = BillSqlProvider.class, method = "insertSelective")
    int insertSelective(Bill record);

    @Select({
            "select",
            "id, code, goods_id, goodsCount, totalPrice, isPayment, createdBy, creationDate",
            "from smbms_bill",
            "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "code", property = "code", jdbcType = JdbcType.VARCHAR),
            @Result(column = "goods_id", property = "goodsId", jdbcType = JdbcType.BIGINT),
            @Result(column = "goodsCount", property = "goodscount", jdbcType = JdbcType.INTEGER),
            @Result(column = "totalPrice", property = "totalprice", jdbcType = JdbcType.DECIMAL),
            @Result(column = "isPayment", property = "ispayment", jdbcType = JdbcType.INTEGER),
            @Result(column = "createdBy", property = "createdby", jdbcType = JdbcType.BIGINT),
            @Result(column = "creationDate", property = "creationdate", jdbcType = JdbcType.TIMESTAMP)
    })
    Bill selectByPrimaryKey(Long id);

    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "code", property = "code", jdbcType = JdbcType.VARCHAR),
            @Result(column = "goods_id", property = "goodsId", jdbcType = JdbcType.BIGINT),
            @Result(column = "goodsCount", property = "goodscount", jdbcType = JdbcType.INTEGER),
            @Result(column = "totalPrice", property = "totalprice", jdbcType = JdbcType.DECIMAL),
            @Result(column = "isPayment", property = "ispayment", jdbcType = JdbcType.INTEGER),
            @Result(column = "createdBy", property = "createdby", jdbcType = JdbcType.BIGINT),
            @Result(column = "creationDate", property = "creationdate", jdbcType = JdbcType.TIMESTAMP)
    })
    @Select({
            "<script> select",
            "id, code, goods_id, goodsCount, totalPrice, isPayment, createdBy, creationDate",
            "from smbms_bill",
            "where id in ",
            "<foreach item='id' index='index' collection='ids' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    Bill selectByIds(@Param("ids") List<Long> ids);


    @UpdateProvider(type = BillSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Bill record);

    @Update({
            "update smbms_bill",
            "set code = #{code,jdbcType=VARCHAR},",
            "goods_id = #{goodsId,jdbcType=BIGINT},",
            "goodsCount = #{goodscount,jdbcType=INTEGER},",
            "totalPrice = #{totalprice,jdbcType=DECIMAL},",
            "isPayment = #{ispayment,jdbcType=INTEGER},",
            "createdBy = #{createdby,jdbcType=BIGINT},",
            "creationDate = #{creationdate,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Bill record);
}