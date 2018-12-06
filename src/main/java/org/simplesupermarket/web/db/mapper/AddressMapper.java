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
import org.simplesupermarket.web.db.model.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressMapper {
    @Delete({
        "delete from smbms_address",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into smbms_address (id, contact, ",
        "desc, postCode, ",
        "tel, createdBy, creationDate, ",
        "user_id)",
        "values (#{id,jdbcType=BIGINT}, #{contact,jdbcType=VARCHAR}, ",
        "#{desc,jdbcType=VARCHAR}, #{postcode,jdbcType=VARCHAR}, ",
        "#{tel,jdbcType=VARCHAR}, #{createdby,jdbcType=BIGINT}, #{creationdate,jdbcType=TIMESTAMP}, ",
        "#{userId,jdbcType=BIGINT})"
    })
    int insert(Address record);

    @InsertProvider(type=AddressSqlProvider.class, method="insertSelective")
    int insertSelective(Address record);

    @Select({
        "select",
        "id, contact, desc, postCode, tel, createdBy, creationDate, user_id",
        "from smbms_address",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="contact", property="contact", jdbcType=JdbcType.VARCHAR),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="postCode", property="postcode", jdbcType=JdbcType.VARCHAR),
        @Result(column="tel", property="tel", jdbcType=JdbcType.VARCHAR),
        @Result(column="createdBy", property="createdby", jdbcType=JdbcType.BIGINT),
        @Result(column="creationDate", property="creationdate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT)
    })
    Address selectByPrimaryKey(Long id);

    @UpdateProvider(type=AddressSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Address record);

    @Update({
        "update smbms_address",
        "set contact = #{contact,jdbcType=VARCHAR},",
          "desc = #{desc,jdbcType=VARCHAR},",
          "postCode = #{postcode,jdbcType=VARCHAR},",
          "tel = #{tel,jdbcType=VARCHAR},",
          "createdBy = #{createdby,jdbcType=BIGINT},",
          "creationDate = #{creationdate,jdbcType=TIMESTAMP},",
          "user_id = #{userId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Address record);
}