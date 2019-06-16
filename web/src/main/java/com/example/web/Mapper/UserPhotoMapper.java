package com.example.web.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserPhotoMapper {
    @Insert("INSERT INTO userPhoto (userId, phoneNum, userPhoto) VALUES (#{userId}, #{phoneNum}, #{userPhoto})")
    Boolean uploadUserPhoto(@Param("userId")Integer userId, @Param("phoneNum")String phoneNum,
                            @Param("userPhoto")String userPhoto);

    @Select("SELECT userPhoto FROM userphoto WHERE phoneNum = #{phoneNum}")
    List<String> getUserPhoto(@Param("phoneNum")String phoneNum);
}
