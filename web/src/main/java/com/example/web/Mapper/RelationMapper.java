package com.example.web.Mapper;

import org.apache.ibatis.annotations.*;

public interface RelationMapper {
    @Select("SELECT permission FROM relation WHERE userId = #{userId} and targetId = #{targetId}")
    int getRelation(@Param("userId") Integer userId,@Param("targetId") Integer targetId);
    @Insert("INSERT INTO relation(id,userId,targetId,permission) value(DEFAULT,#{userId},#{targetId},#{permission})")
    Boolean addRelation(@Param("userId") Integer userId,@Param("targetId") Integer targetId,@Param("permission") Integer permission);
    @Update("UPDATE relation SET permission = #{permission} where userId = #{userId} and targetId = #{targetId}")
    Boolean updateRelation(@Param("userId") Integer userId,@Param("targetId") Integer targetId,@Param("permission") Integer permission);
    @Delete("DELETE from relation where userId = #{userId} and targetId = #{targetId}")
    Boolean deleteRelation(@Param("userId") Integer userId,@Param("targetId") Integer targetId);

}
