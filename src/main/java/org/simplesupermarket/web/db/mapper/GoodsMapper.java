package org.simplesupermarket.web.db.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.simplesupermarket.web.db.ObjectCrudMapper;
import org.simplesupermarket.web.db.model.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper extends ObjectCrudMapper<Goods> {
    @Delete({
            "delete from smbms_goods",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into smbms_goods (id, code, ",
            "name, price, unit, ",
            "provider_id, stock, ",
            "createdBy, creationDate)",
            "values (#{id,jdbcType=BIGINT}, #{code,jdbcType=VARCHAR}, ",
            "#{name,jdbcType=VARCHAR}, #{price,jdbcType=DECIMAL}, #{unit,jdbcType=VARCHAR}, ",
            "#{providerId,jdbcType=BIGINT}, #{stock,jdbcType=INTEGER}, ",
            "#{createdby,jdbcType=BIGINT}, #{creationdate,jdbcType=TIMESTAMP})"
    })
    int insert(Goods record);

    @InsertProvider(type = GoodsSqlProvider.class, method = "insertSelective")
    int insertSelective(Goods record);

    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "code", property = "code", jdbcType = JdbcType.VARCHAR),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "price", property = "price", jdbcType = JdbcType.DECIMAL),
            @Result(column = "unit", property = "unit", jdbcType = JdbcType.VARCHAR),
            @Result(column = "provider_id", property = "providerId", jdbcType = JdbcType.BIGINT),
            @Result(column = "stock", property = "stock", jdbcType = JdbcType.INTEGER),
            @Result(column = "createdBy", property = "createdby", jdbcType = JdbcType.BIGINT),
            @Result(column = "creationDate", property = "creationdate", jdbcType = JdbcType.TIMESTAMP)
    })
    @Select({"<script> select",
            "id, code, `name`, price, unit, provider_id, stock, createdBy, creationDate",
            "from smbms_goods",
            "<where> ",
            "<if test='goodsName!=null'>", "name like CONCAT('%',#{goodsName},'%') ", "</if>",
            "<if test='providerName!=null'> AND", " provider_id IN (",
                 "select id from smbms_provider where name like CONCAT('%',#{providerName},'%')",
                ")",
            "</if>",
            "</where>  </script>"
    })
    List<Goods> selectAll(@Param("goodsName") String goodsName, @Param("providerName") String providerName);
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "code", property = "code", jdbcType = JdbcType.VARCHAR),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "price", property = "price", jdbcType = JdbcType.DECIMAL),
            @Result(column = "unit", property = "unit", jdbcType = JdbcType.VARCHAR),
            @Result(column = "provider_id", property = "providerId", jdbcType = JdbcType.BIGINT),
            @Result(column = "stock", property = "stock", jdbcType = JdbcType.INTEGER),
            @Result(column = "createdBy", property = "createdby", jdbcType = JdbcType.BIGINT),
            @Result(column = "creationDate", property = "creationdate", jdbcType = JdbcType.TIMESTAMP)
    })
    @Select({"<script> select",
            "id, code, name, price, unit, provider_id, stock, createdBy, creationDate",
            "from smbms_goods",
            "where id in ",
            "<foreach item='id' index='index' collection='ids' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    List<Goods> selectByIds(@Param("ids") List<Long> ids);

    @Select({
            "select",
            "id, code, name, price, unit, provider_id, stock, createdBy, creationDate",
            "from smbms_goods",
            "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "code", property = "code", jdbcType = JdbcType.VARCHAR),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "price", property = "price", jdbcType = JdbcType.DECIMAL),
            @Result(column = "unit", property = "unit", jdbcType = JdbcType.VARCHAR),
            @Result(column = "provider_id", property = "providerId", jdbcType = JdbcType.BIGINT),
            @Result(column = "stock", property = "stock", jdbcType = JdbcType.INTEGER),
            @Result(column = "createdBy", property = "createdby", jdbcType = JdbcType.BIGINT),
            @Result(column = "creationDate", property = "creationdate", jdbcType = JdbcType.TIMESTAMP)
    })
    Goods selectByPrimaryKey(Long id);

    @UpdateProvider(type = GoodsSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Goods record);

    @Update({
            "update smbms_goods",
            "set code = #{code,jdbcType=VARCHAR},",
            "name = #{name,jdbcType=VARCHAR},",
            "price = #{price,jdbcType=DECIMAL},",
            "unit = #{unit,jdbcType=VARCHAR},",
            "provider_id = #{providerId,jdbcType=BIGINT},",
            "stock = #{stock,jdbcType=INTEGER},",
            "createdBy = #{createdby,jdbcType=BIGINT},",
            "creationDate = #{creationdate,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Goods record);
}