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
import org.simplesupermarket.web.db.model.User;

public interface UserMapper {
    @Delete({
        "delete from smbms_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into smbms_user (id, userCode, ",
        "userName, userPassword, ",
        "gender, birthday, phone, ",
        "address, userRole, ",
        "createdBy, creationDate, ",
        "modifyBy, modifyDate, ",
        "state)",
        "values (#{id,jdbcType=BIGINT}, #{usercode,jdbcType=VARCHAR}, ",
        "#{username,jdbcType=VARCHAR}, #{userpassword,jdbcType=CHAR}, ",
        "#{gender,jdbcType=INTEGER}, #{birthday,jdbcType=DATE}, #{phone,jdbcType=VARCHAR}, ",
        "#{address,jdbcType=VARCHAR}, #{userrole,jdbcType=BIGINT}, ",
        "#{createdby,jdbcType=BIGINT}, #{creationdate,jdbcType=TIMESTAMP}, ",
        "#{modifyby,jdbcType=BIGINT}, #{modifydate,jdbcType=TIMESTAMP}, ",
        "#{state,jdbcType=INTEGER})"
    })
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    int insertSelective(User record);

    @Select({
        "select",
        "id, userCode, userName, userPassword, gender, birthday, phone, address, userRole, ",
        "createdBy, creationDate, modifyBy, modifyDate, state",
        "from smbms_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="userCode", property="usercode", jdbcType=JdbcType.VARCHAR),
        @Result(column="userName", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="userPassword", property="userpassword", jdbcType=JdbcType.CHAR),
        @Result(column="gender", property="gender", jdbcType=JdbcType.INTEGER),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="userRole", property="userrole", jdbcType=JdbcType.BIGINT),
        @Result(column="createdBy", property="createdby", jdbcType=JdbcType.BIGINT),
        @Result(column="creationDate", property="creationdate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifyBy", property="modifyby", jdbcType=JdbcType.BIGINT),
        @Result(column="modifyDate", property="modifydate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER)
    })
    User selectByPrimaryKey(Long id);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update smbms_user",
        "set userCode = #{usercode,jdbcType=VARCHAR},",
          "userName = #{username,jdbcType=VARCHAR},",
          "userPassword = #{userpassword,jdbcType=CHAR},",
          "gender = #{gender,jdbcType=INTEGER},",
          "birthday = #{birthday,jdbcType=DATE},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "userRole = #{userrole,jdbcType=BIGINT},",
          "createdBy = #{createdby,jdbcType=BIGINT},",
          "creationDate = #{creationdate,jdbcType=TIMESTAMP},",
          "modifyBy = #{modifyby,jdbcType=BIGINT},",
          "modifyDate = #{modifydate,jdbcType=TIMESTAMP},",
          "state = #{state,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(User record);
}