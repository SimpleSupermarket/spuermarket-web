package org.simplesupermarket.web.db.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.simplesupermarket.web.db.ObjectCrudMapper;
import org.simplesupermarket.web.db.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends ObjectCrudMapper<User> {
    @Delete({
        "delete from smbms_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into smbms_user (id, code, ",
        "name, password, gender, ",
        "birthday, phone, address, ",
        "role_id, createdBy, ",
        "creationDate, state)",
        "values (#{id,jdbcType=BIGINT}, #{code,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{password,jdbcType=CHAR}, #{gender,jdbcType=INTEGER}, ",
        "#{birthday,jdbcType=DATE}, #{phone,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
        "#{roleId,jdbcType=BIGINT}, #{createdby,jdbcType=BIGINT}, ",
        "#{creationdate,jdbcType=TIMESTAMP}, #{state,jdbcType=INTEGER})"
    })
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    int insertSelective(User record);

    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.CHAR),
            @Result(column="gender", property="gender", jdbcType=JdbcType.INTEGER),
            @Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
            @Result(column="createdBy", property="createdby", jdbcType=JdbcType.BIGINT),
            @Result(column="creationDate", property="creationdate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="state", property="state", jdbcType=JdbcType.INTEGER)
    })
    @Select({
            "<script> select",
            "id, `code`, `name`, password, gender, birthday, phone, address, role_id, createdBy, ",
            "creationDate, state",
            "from smbms_user",
            "<where><if test='name!=null'>",
                " name like CONCAT('%',#{name},'%') ",
            "</if>",
            "<if test='code!=null'>",
                 "and code like CONCAT('%',#{code},'%') ",
            "</if></where>",
            "</script>"
    })
    List<User> selectAll(@Param("name") String name,@Param("code") String code);


    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="gender", property="gender", jdbcType=JdbcType.INTEGER),
            @Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="createdBy", property="createdby", jdbcType=JdbcType.BIGINT),
            @Result(column="creationDate", property="creationdate", jdbcType=JdbcType.TIMESTAMP)
    })
    @Select({
            "<script> select",
            "id, `code`, `name`,  gender, birthday, phone, address, createdBy,creationDate",
            "from smbms_user",
            "where id in ",
            "<foreach item='id' index='index' collection='ids' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    List<User> selectByIds(@Param("ids") List<Long> ids);

    @Select({
        "select",
        "id, code, name, password, gender, birthday, phone, address, role_id, createdBy, ",
        "creationDate, state",
        "from smbms_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.CHAR),
        @Result(column="gender", property="gender", jdbcType=JdbcType.INTEGER),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="createdBy", property="createdby", jdbcType=JdbcType.BIGINT),
        @Result(column="creationDate", property="creationdate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER)
    })
    User selectByPrimaryKey(Long id);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update smbms_user",
        "set code = #{code,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=CHAR},",
          "gender = #{gender,jdbcType=INTEGER},",
          "birthday = #{birthday,jdbcType=DATE},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "role_id = #{roleId,jdbcType=BIGINT},",
          "createdBy = #{createdby,jdbcType=BIGINT},",
          "creationDate = #{creationdate,jdbcType=TIMESTAMP},",
          "state = #{state,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(User record);
}