package com.example.web.Service;


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
import java.util.List;

public interface UserService {
    //基础查询
    Boolean checkUserById(Integer userId);
    Boolean checkUserByPhoneNum(String phoneNum);
    List<User> checkUser(String string);
    //用户查询
    User getUserByUserId(Integer userId);
    User getUserByPhoneNum(String phoneNum);
    List<User> getUserList();
    //userId查询
    List<Integer> getUserIdList();
    Integer getUserIdByPhoneNum(String phoneNum);
    //增删改
    Boolean addUser(User user);
    Boolean updateUser(Integer userId, User user);
    Boolean deleteUser(Integer userId);
    //其他查询
    Error chgBud(Integer userId,Integer corolla);
    Error chgCorolla(Integer userId,Integer corolla);
    Error chgBud1(Integer userId, Integer bud);
}
