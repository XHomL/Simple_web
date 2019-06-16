package com.example.web.Mapper;
//相关文件 UserController UserService UserCommon UserServicelmpl UserMapper
//实现了以下功能
//1 - 通过id查找用户              参数：userId
//2 - 通过手机号查找用户          参数：phoneNum
//3 - 添加新用户                  参数：phoneNum
//4 - 更新用户信息                参数：userId,profile,username,password,realName,sex,birthday,signature,photo,address
//5 - 删除用户                    参数：userId
//6 - 获取所有用户                参数：无
//注：更新数据不允许更新手机号，数据库报错，就删掉了


import com.example.web.Bean.Error;
import com.example.web.Bean.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

// @Mapper 这里可以使用@Mapper注解，但是每个mapper都加注解比较麻烦，所以统一配置@MapperScan在扫描路径在application类中
public interface UserMapper {
    //基础查询
    @Select("SELECT * FROM user WHERE userId = #{userId}")
    Boolean checkUserById(Integer userId);
    @Select("SELECT * FROM user WHERE phoneNum = #{phoneNum}")
    Boolean checkUserByPhoneNum(String phoneNum);
    @Select("SELECT * from user WHERE username LIKE CONCAT('%',#{username},'%') or phoneNum = #{username}")
    List<User> checkUserByUsername(String username);
    @Select("SELECT * from user WHERE userId = #{userId}")
    User getUserByUserId(Integer userId);

    @Select("SELECT * from user WHERE phoneNum = #{phoneNum}")
    User getUserByPhoneNum(String phoneNum);

    @Select("SELECT * FROM user")
    List<User> getUserList();

    @Select("SELECT userId FROM user")
    List<Integer> getUserIdList();

    @Insert("insert into user(userId,phoneNum,bud,corolla) values(DEFAULT, #{phoneNum},0,0)")
    Boolean addUser(User user);


    @Update("UPDATE user SET username = #{user.username} , profile=#{user.profile} , " +
            "password = #{user.password} , realName = #{user.realName} , " +
            "sex = #{user.sex} , birthday = #{user.birthday} , signature = #{user.signature} , " +
            "photo = #{user.photo} , address = #{user.address},bud = #{user.bud},corolla = #{user.corolla} WHERE userId = #{userId} order by UserId")
    Boolean updateUser(@Param("userId") Integer userId, @Param("user") User user);

    @Delete("DELETE from user where userId = #{userId} ")
    Boolean deleteUser(Integer userId);
    @Select("SELECT userId FROM user where phoneNum = #{phoneNum}")
    Integer getUserIdByPhoneNum(String phoneNum);

}
