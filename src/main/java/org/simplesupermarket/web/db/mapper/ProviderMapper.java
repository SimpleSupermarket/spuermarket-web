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
import org.simplesupermarket.web.db.model.Provider;

public interface ProviderMapper {
    @Delete({
        "delete from smbms_provider",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into smbms_provider (id, proCode, ",
        "proName, proDesc, ",
        "proContact, proPhone, ",
        "proAddress, proFax, ",
        "createdBy, creationDate, ",
        "modifyDate, modifyBy)",
        "values (#{id,jdbcType=BIGINT}, #{procode,jdbcType=VARCHAR}, ",
        "#{proname,jdbcType=VARCHAR}, #{prodesc,jdbcType=VARCHAR}, ",
        "#{procontact,jdbcType=VARCHAR}, #{prophone,jdbcType=VARCHAR}, ",
        "#{proaddress,jdbcType=VARCHAR}, #{profax,jdbcType=VARCHAR}, ",
        "#{createdby,jdbcType=BIGINT}, #{creationdate,jdbcType=TIMESTAMP}, ",
        "#{modifydate,jdbcType=TIMESTAMP}, #{modifyby,jdbcType=BIGINT})"
    })
    int insert(Provider record);

    @InsertProvider(type=ProviderSqlProvider.class, method="insertSelective")
    int insertSelective(Provider record);

    @Select({
        "select",
        "id, proCode, proName, proDesc, proContact, proPhone, proAddress, proFax, createdBy, ",
        "creationDate, modifyDate, modifyBy",
        "from smbms_provider",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="proCode", property="procode", jdbcType=JdbcType.VARCHAR),
        @Result(column="proName", property="proname", jdbcType=JdbcType.VARCHAR),
        @Result(column="proDesc", property="prodesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="proContact", property="procontact", jdbcType=JdbcType.VARCHAR),
        @Result(column="proPhone", property="prophone", jdbcType=JdbcType.VARCHAR),
        @Result(column="proAddress", property="proaddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="proFax", property="profax", jdbcType=JdbcType.VARCHAR),
        @Result(column="createdBy", property="createdby", jdbcType=JdbcType.BIGINT),
        @Result(column="creationDate", property="creationdate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifyDate", property="modifydate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifyBy", property="modifyby", jdbcType=JdbcType.BIGINT)
    })
    Provider selectByPrimaryKey(Long id);

    @UpdateProvider(type=ProviderSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Provider record);

    @Update({
        "update smbms_provider",
        "set proCode = #{procode,jdbcType=VARCHAR},",
          "proName = #{proname,jdbcType=VARCHAR},",
          "proDesc = #{prodesc,jdbcType=VARCHAR},",
          "proContact = #{procontact,jdbcType=VARCHAR},",
          "proPhone = #{prophone,jdbcType=VARCHAR},",
          "proAddress = #{proaddress,jdbcType=VARCHAR},",
          "proFax = #{profax,jdbcType=VARCHAR},",
          "createdBy = #{createdby,jdbcType=BIGINT},",
          "creationDate = #{creationdate,jdbcType=TIMESTAMP},",
          "modifyDate = #{modifydate,jdbcType=TIMESTAMP},",
          "modifyBy = #{modifyby,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Provider record);
}