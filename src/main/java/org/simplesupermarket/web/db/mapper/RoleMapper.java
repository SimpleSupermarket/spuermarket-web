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
import org.simplesupermarket.web.db.model.Role;

public interface RoleMapper  extends ObjectCrudMapper<Role> {
    @Delete({
        "delete from smbms_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into smbms_role (id, roleCode, ",
        "roleName, createdBy, ",
        "creationDate, modifyBy, ",
        "modifyDate)",
        "values (#{id,jdbcType=BIGINT}, #{rolecode,jdbcType=VARCHAR}, ",
        "#{rolename,jdbcType=VARCHAR}, #{createdby,jdbcType=BIGINT}, ",
        "#{creationdate,jdbcType=TIMESTAMP}, #{modifyby,jdbcType=BIGINT}, ",
        "#{modifydate,jdbcType=TIMESTAMP})"
    })
    int insert(Role record);

    @InsertProvider(type=RoleSqlProvider.class, method="insertSelective")
    int insertSelective(Role record);

    @Select({
        "select",
        "id, roleCode, roleName, createdBy, creationDate, modifyBy, modifyDate",
        "from smbms_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="roleCode", property="rolecode", jdbcType=JdbcType.VARCHAR),
        @Result(column="roleName", property="rolename", jdbcType=JdbcType.VARCHAR),
        @Result(column="createdBy", property="createdby", jdbcType=JdbcType.BIGINT),
        @Result(column="creationDate", property="creationdate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifyBy", property="modifyby", jdbcType=JdbcType.BIGINT),
        @Result(column="modifyDate", property="modifydate", jdbcType=JdbcType.TIMESTAMP)
    })
    Role selectByPrimaryKey(Long id);

    @UpdateProvider(type=RoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Role record);

    @Update({
        "update smbms_role",
        "set roleCode = #{rolecode,jdbcType=VARCHAR},",
          "roleName = #{rolename,jdbcType=VARCHAR},",
          "createdBy = #{createdby,jdbcType=BIGINT},",
          "creationDate = #{creationdate,jdbcType=TIMESTAMP},",
          "modifyBy = #{modifyby,jdbcType=BIGINT},",
          "modifyDate = #{modifydate,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Role record);
}