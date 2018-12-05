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
import org.simplesupermarket.web.db.model.Goods;

public interface GoodsMapper extends ObjectCrudMapper {
    @Delete({
        "delete from smbms_goods",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into smbms_goods (id, code, ",
        "namne, price, unit, ",
        "provider_id, gstock, ",
        "createdBy, creationDate)",
        "values (#{id,jdbcType=BIGINT}, #{code,jdbcType=VARCHAR}, ",
        "#{namne,jdbcType=VARCHAR}, #{price,jdbcType=DECIMAL}, #{unit,jdbcType=VARCHAR}, ",
        "#{providerId,jdbcType=BIGINT}, #{gstock,jdbcType=INTEGER}, ",
        "#{createdby,jdbcType=BIGINT}, #{creationdate,jdbcType=TIMESTAMP})"
    })
    int insert(Goods record);

    @InsertProvider(type=GoodsSqlProvider.class, method="insertSelective")
    int insertSelective(Goods record);

    @Select({
        "select",
        "id, code, namne, price, unit, provider_id, gstock, createdBy, creationDate",
        "from smbms_goods",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="namne", property="namne", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="unit", property="unit", jdbcType=JdbcType.VARCHAR),
        @Result(column="provider_id", property="providerId", jdbcType=JdbcType.BIGINT),
        @Result(column="gstock", property="gstock", jdbcType=JdbcType.INTEGER),
        @Result(column="createdBy", property="createdby", jdbcType=JdbcType.BIGINT),
        @Result(column="creationDate", property="creationdate", jdbcType=JdbcType.TIMESTAMP)
    })
    Goods selectByPrimaryKey(Long id);

    @UpdateProvider(type=GoodsSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Goods record);

    @Update({
        "update smbms_goods",
        "set code = #{code,jdbcType=VARCHAR},",
          "namne = #{namne,jdbcType=VARCHAR},",
          "price = #{price,jdbcType=DECIMAL},",
          "unit = #{unit,jdbcType=VARCHAR},",
          "provider_id = #{providerId,jdbcType=BIGINT},",
          "gstock = #{gstock,jdbcType=INTEGER},",
          "createdBy = #{createdby,jdbcType=BIGINT},",
          "creationDate = #{creationdate,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Goods record);
}