package com.example.web.Controller;

//相关文件 UserController UserService UserCommon UserServicelmpl UserMapper
//实现了以下功能
//1 - 通过id查找用户              参数：userId
//2 - 通过手机号查找用户          参数：phoneNum
//3 - 添加新用户                  参数：phoneNum
//4 - 更新用户信息                参数：userId,profile,username,password,realName,sex,birthday,signature,photo,address
//5 - 删除用户                    参数：userId
//6 - 获取所有用户                参数：无
//注：更新数据不允许更新手机号，数据库报错，就删掉了
//更新用户信息用map来接收更方便一些

import com.example.web.Bean.Error;
import com.example.web.Bean.User;
import com.example.web.Service.RelationService;
import com.example.web.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private static final String NONE = null;
    @Autowired
    private UserService userService;
    @Autowired
    private RelationService relationService;

    @RequestMapping("/getUserList")
    public List<User> getUserList(){
        return userService.getUserList();
    }

    @RequestMapping("/getUserByUserId")
    public User getUserByUserId(Integer userId){
        return userService.getUserByUserId(userId);
    }


    public User getUserByPhoneNum(String phoneNum){
        return userService.getUserByPhoneNum(phoneNum);
    }

    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST )
    public Boolean deleteUser(@RequestBody Map<String,Object> map){
        System.out.println(map);
        Integer userId =Integer.parseInt( map.get("userId").toString());
        return userService.deleteUser(userId);
    }

    @RequestMapping("/addUser")
    public Boolean addUser(User user){
        return userService.addUser(user);
    }
    public Boolean updateUser(User user){
        return userService.updateUser(user.getUserId(),user);
    }

    @RequestMapping(value = "/checkUserExist" ,method = RequestMethod.POST)
    public List<User> checkUserByUsername(@RequestBody Map<String,Object> map){
        System.out.println(map);
        List<User> res = userService.checkUser(map.get("string").toString());
        for(int i=0;i<res.size();i++){
            res.get(i).setSignature("");
            res.get(i).setAddress("");
            res.get(i).setPassword("");
            res.get(i).setBud(0);
            res.get(i).setPhoto("");
            res.get(i).setRealName("");
            res.get(i).setCorolla(0);
            res.get(i).setBirthday("");
            res.get(i).setProfile("");
            res.get(i).setUserId(0);

        }
        System.out.println("find user result:"+res);
        return res;
    }

}
