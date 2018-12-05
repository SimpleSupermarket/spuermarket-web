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
import org.simplesupermarket.web.db.model.Provider;

public interface ProviderMapper extends ObjectCrudMapper {
    @Delete({
        "delete from smbms_provider",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into smbms_provider (id, code, ",
        "name, desc, contact, ",
        "phone, address, ",
        "fax, createdBy, creationDate)",
        "values (#{id,jdbcType=BIGINT}, #{code,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{desc,jdbcType=VARCHAR}, #{contact,jdbcType=VARCHAR}, ",
        "#{phone,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
        "#{fax,jdbcType=VARCHAR}, #{createdby,jdbcType=BIGINT}, #{creationdate,jdbcType=TIMESTAMP})"
    })
    int insert(Provider record);

    @InsertProvider(type=ProviderSqlProvider.class, method="insertSelective")
    int insertSelective(Provider record);

    @Select({
        "select",
        "id, code, name, desc, contact, phone, address, fax, createdBy, creationDate",
        "from smbms_provider",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="contact", property="contact", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="fax", property="fax", jdbcType=JdbcType.VARCHAR),
        @Result(column="createdBy", property="createdby", jdbcType=JdbcType.BIGINT),
        @Result(column="creationDate", property="creationdate", jdbcType=JdbcType.TIMESTAMP)
    })
    Provider selectByPrimaryKey(Long id);

    @UpdateProvider(type=ProviderSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Provider record);

    @Update({
        "update smbms_provider",
        "set code = #{code,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "desc = #{desc,jdbcType=VARCHAR},",
          "contact = #{contact,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "fax = #{fax,jdbcType=VARCHAR},",
          "createdBy = #{createdby,jdbcType=BIGINT},",
          "creationDate = #{creationdate,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Provider record);
}