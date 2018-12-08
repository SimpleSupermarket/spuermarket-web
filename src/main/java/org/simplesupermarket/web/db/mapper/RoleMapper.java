package org.simplesupermarket.web.db.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.simplesupermarket.web.db.ObjectCrudMapper;
import org.simplesupermarket.web.db.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends ObjectCrudMapper<Role> {
    @Delete({
        "delete from smbms_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into smbms_role (id, code, ",
        "name, createdBy, ",
        "creationDate)",
        "values (#{id,jdbcType=BIGINT}, #{code,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{createdby,jdbcType=BIGINT}, ",
        "#{creationdate,jdbcType=TIMESTAMP})"
    })
    int insert(Role record);

    @InsertProvider(type=RoleSqlProvider.class, method="insertSelective")
    int insertSelective(Role record);

    @Select({
        "select",
        "id, code, name, createdBy, creationDate",
        "from smbms_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="createdBy", property="createdby", jdbcType=JdbcType.BIGINT),
        @Result(column="creationDate", property="creationdate", jdbcType=JdbcType.TIMESTAMP)
    })
    Role selectByPrimaryKey(Long id);

    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="createdBy", property="createdby", jdbcType=JdbcType.BIGINT),
            @Result(column="creationDate", property="creationdate", jdbcType=JdbcType.TIMESTAMP)
    })
    @Select({
            "<script> select",
            "id, code, name, createdBy, creationDate",
            "from smbms_role",
            "where id in ",
            "<foreach item='id' index='index' collection='ids' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    List<Role> selectByIds(@Param("ids") List<Long> id);


    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="createdBy", property="createdby", jdbcType=JdbcType.BIGINT),
            @Result(column="creationDate", property="creationdate", jdbcType=JdbcType.TIMESTAMP)
    })
    @Select({
            " select",
            "id, code, name, createdBy, creationDate",
            "from smbms_role"
    })
    List<Role> selectAll();
    @UpdateProvider(type=RoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Role record);

    @Update({
        "update smbms_role",
        "set code = #{code,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "createdBy = #{createdby,jdbcType=BIGINT},",
          "creationDate = #{creationdate,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Role record);


}