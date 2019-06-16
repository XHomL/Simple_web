package com.example.web.Service;

//相关文件 UserController UserService UserCommon UserServicelmpl UserMapper
//实现了以下功能
//1 - 通过id查找用户              参数：userId
//2 - 通过手机号查找用户          参数：phoneNum
//3 - 添加新用户                  参数：phoneNum
//4 - 更新用户信息                参数：userId,profile,phoneNum,username,password,realName,sex,birthday,signature,photo,address
//5 - 删除用户                    参数：userId
//6 - 获取所有用户                参数：无

import com.example.web.Bean.Error;
import com.example.web.Bean.User;
import com.example.web.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    //此处自动装配会有红线，是因为编写的时候程序未在Mapper文件夹中检测到@Mapper,这是因为我写到了启动中，实际启动后不会出错
    @Autowired
    private UserMapper userMapper;

    //基础查询
    @Override
    public Boolean checkUserById(Integer userId) { return userMapper.checkUserById(userId); }
    @Override
    public Boolean checkUserByPhoneNum(String phoneNum) {
        return userMapper.checkUserByPhoneNum(phoneNum);
    }

    @Override
    public List<User> checkUser(String string) {
        return userMapper.checkUserByUsername(string);
    }


    //查询用户
    @Override
    public User getUserByUserId(Integer userId) {
        return userMapper.getUserByUserId(userId);
    }

    @Override
    public User getUserByPhoneNum(String phoneNum){
        return userMapper.getUserByPhoneNum(phoneNum);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public List<Integer> getUserIdList(){ return userMapper.getUserIdList();}

    @Override
    public Integer getUserIdByPhoneNum(String phoneNum) {
        return userMapper.getUserIdByPhoneNum(phoneNum);
    }

    @Override
    public Boolean addUser(User user) {        return userMapper.addUser(user);    }

    @Override
    public Boolean updateUser(Integer userId, User user) {
        return userMapper.updateUser(userId, user);
    }

    @Override
    public Boolean deleteUser(Integer userId) {
        return userMapper.deleteUser(userId);
    }

    @Override
    public Error chgBud(Integer userId, Integer bud) {
        Error msg = new Error();
        if(null == checkUserById(userId)){
            msg.setErrorMsg(721,"不存在用户");
            return msg;
        }
        User user = getUserByUserId(userId);
        if(0 > bud+ user.getBud()){
            msg.setErrorMsg(728,"用户bud不足");
            return msg;
        }
        user.setBud(user.getBud()+bud);
        msg.setErrorMsg(0,userMapper.updateUser(userId,user).toString());
        return msg;
    }



    @Override
    public Error chgCorolla(Integer userId,Integer corolla){
        Error msg = new Error();
        if(null == checkUserById(userId)){
            msg.setErrorMsg(721,"不存在用户");
            return msg;
        }
        User user =  getUserByUserId(userId);
        if(0 > corolla + user.getCorolla()){
            msg.setErrorMsg(728,"用户corolla不足");
        }
        user.setCorolla(user.getCorolla()+corolla);
        msg.setErrorMsg(0,userMapper.updateUser(userId,user).toString());
        return msg;
    }

    @Override
    public Error chgBud1(Integer userId, Integer bud) {
        Error msg = new Error();
        if(null == checkUserById(userId)){
            msg.setErrorMsg(721,"不存在用户");
            return msg;
        }
        User user = getUserByUserId(userId);
        if(0 > bud+ user.getBud()){
            msg.setErrorMsg(728,"用户bud不足");
            return msg;
        }
        user.setBud(user.getBud()+bud);
        user.setCorolla(user.getCorolla()-bud/1500);
        msg.setErrorMsg(0,userMapper.updateUser(userId,user).toString());
        return msg;
    }
}
