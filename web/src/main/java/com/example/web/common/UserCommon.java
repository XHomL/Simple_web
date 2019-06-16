package com.example.web.common;

import com.example.web.Bean.Error;
import com.example.web.Bean.User;
import com.example.web.Controller.UserController;
import com.example.web.Service.RealProductionService;
import com.example.web.Service.RelationService;
import com.example.web.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.soap.Text;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
public class UserCommon {
    private static final String NONE = null;
    @Autowired
    private RealProductionService realProductionService;
    @Autowired
    private UserService userService;

    @Autowired
    private RelationService relationService;


    /**
     * 管理员更新用户信息
     * checkDown
     * @param map
     * @return
     */
    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    public Error updateUser(@RequestBody Map<String,Object> map){
        User user = new User();
        Error msg = new Error();
        System.out.println(map);
        user = userService.getUserByPhoneNum(map.get("phoneNum").toString());
        user.setPassword(map.get("password").toString());
        user.setAddress(map.get("address").toString());
        user.setSignature(map.get("signature").toString());
        user.setUsername(map.get("username").toString());
        msg.setErrorMsg(0,userService.updateUser(user.getUserId(),user).toString());
        return msg;
    }

    /**
     * 用户登录检查用户名和密码
     * @param map
     * @return
     */
    @RequestMapping(value = "/checkUser" ,method = RequestMethod.POST)
    public Error Login(HttpServletResponse response, HttpServletRequest request, @RequestBody Map<String, Object> map){
        Cookie cookie = new Cookie("phoneNum",map.get("account").toString());
        HttpSession session = request.getSession();
        response.addCookie(cookie);
        session.setAttribute("phoneNum",map.get("account").toString());
        Error msg = new Error();
        User loginUser;
        String phoneNum = map.get("account").toString();
        String password = map.get("psd").toString();
        System.out.println(phoneNum+"  "+password);
        //检查手机号是否存在
        if(null == userService.checkUserByPhoneNum(phoneNum)){
            msg.setErrorMsg(711,"不存在手机号");
            return msg;
        }
        loginUser = userService.getUserByPhoneNum(phoneNum);
        if(!password.equals(loginUser.getPassword())){
            msg.setErrorMsg(717,"密码错误");
            return msg;
        }else{
            msg.setErrorMsg(0,"success");
            return msg;
        }
    }

    /**
     * 用户注册界面
     * checkDown
     * @param
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Error register(HttpServletRequest request){
        Error msg = new Error();
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("profile");
        String username=params.getParameter("username");
        String password=params.getParameter("password");
        String realName=params.getParameter("realName");
        String phoneNum=params.getParameter("phoneNum");
        if(!(null == userService.getUserIdByPhoneNum(phoneNum))){
            msg.setErrorMsg(746,"手机号已注册");
            return msg;
        }
        String address=params.getParameter("address");
        String signature=params.getParameter("signature");
        String sex=params.getParameter("sex");
        String birthday=params.getParameter("birthday");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        String path="D:\\JavaeeWorkSpace\\web\\src\\main\\resources\\static\\upload\\";
        //String path = "C:\\Users\\James Zhang\\Desktop\\web - 副本 (3)\\src\\main\\resources\\static\\upload\\";
        if(files.size()>0) {
            for (int i = 0; i < files.size(); ++i) {
                file = files.get(i);
                System.out.println(file);
                if (!file.isEmpty()) {
                    try {
                        byte[] bytes = file.getBytes();
                        stream = new BufferedOutputStream(new FileOutputStream(
                                new File(path + file.getOriginalFilename())));
                        stream.write(bytes);
                        stream.close();
                    } catch (Exception e) {
                        stream = null;
                        msg.setErrorMsg(999, "文件上传错误");
                        return msg;
                    }
                } else {
                    msg.setErrorMsg(999, "文件上传错误");
                    return msg;
                }
            }
        }
        User user=new User();
        user.setPhoneNum(phoneNum);
        userService.addUser(user);
        user=userService.getUserByPhoneNum(phoneNum);
        user.setAddress(address);
        user.setUsername(username);
        user.setPassword(password);
        user.setSignature(signature);
        user.setRealName(realName);
        user.setSex(sex);
        user.setBirthday(birthday);
        String profile;
        if(files.size()>0)
            profile="\\upload\\"+file.getOriginalFilename();
        else
            profile=null;
        user.setProfile(profile);
        System.out.println(user.getUserId());
        List<Integer> userId = userService.getUserIdList();
        for(int i=0;i<userId.size()-1;i++){
            relationService.addRelation(userId.get(i),user.getUserId(),0);
            relationService.addRelation(user.getUserId(), userId.get(i),0);
        }
        relationService.addRelation(user.getUserId(),user.getUserId(),1);
        msg.setErrorMsg(0,userService.updateUser(user.getUserId(),user).toString());
        return msg;
        }
    @RequestMapping("/getUserByPhoneNum")
    @ResponseBody
    public User getUserByPhoneNum(@RequestBody Map<String,Object> map){
        return userService.getUserByPhoneNum(map.get("phoneNum").toString());
    }

    @RequestMapping(value = "/getmypage",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getMyPage(HttpServletRequest request,@RequestBody Map<String,Object> map){
        System.out.println("getmypage"+map);
        String phoneNum = map.get("phoneNum").toString();
        request.setAttribute("phoneNum",phoneNum);
        User user = userService.getUserByPhoneNum(phoneNum);
        Map<String,Object> res = new HashMap<>();
        res.put("username",user.getUsername());
        res.put("profile",user.getProfile());
        res.put("bud",user.getBud());
        res.put("corolla",user.getCorolla());
        res.put("phoneNum",user.getPhoneNum());
        res.put("birthday",user.getBirthday());
        res.put("address",user.getAddress());
        res.put("signature",user.getSignature());
        return res;
    }

}
