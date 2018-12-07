package org.simplesupermarket.web.db.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.simplesupermarket.web.db.ObjectCrudMapper;
import org.simplesupermarket.web.db.model.Provider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderMapper extends ObjectCrudMapper<Provider> {
    @Delete({
        "delete from smbms_provider",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into smbms_provider (id, code, ",
        "name, `desc`, contact, ",
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
    @Select({
            "<script> select",
            "id, `code`, `name`, `desc`, contact, phone, address, fax, createdBy, creationDate",
            "from smbms_provider",
            "<if test='providerName!=null'>",
                "where name like CONCAT('%',#{providerName},'%') ",
            "</if>",
            "</script>"
    })
    List<Provider> selectAll(@Param("providerName") String providerName);


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
    @Select({
            "<script> select",
            "id, code, `name`, `desc`, contact, phone, address, fax, createdBy, creationDate",
            "from smbms_provider",
            "where id in ",
            "<foreach item='id' index='index' collection='ids' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    List<Provider> selectByIds(@Param("ids") List<Long> ids);

    @Select({
        "select",
        "id, `code`, `name`, `desc`, contact, phone, address, fax, createdBy, creationDate",
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