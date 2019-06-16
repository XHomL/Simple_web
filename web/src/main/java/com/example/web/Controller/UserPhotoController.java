package com.example.web.Controller;

import com.example.web.Bean.Production;
import com.example.web.Bean.User;
import com.example.web.Service.UserPhotoService;
import com.example.web.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

@Controller
public class UserPhotoController {
    @Autowired
    UserService userService;
    @Autowired
    UserPhotoService userPhotoService;


    @RequestMapping(value = "/uploadUserPhoto",method = RequestMethod.POST)
    @ResponseBody
    public Boolean uploadUserPhoto(HttpServletRequest request){
        //通过Http获取前端传递的图片，并存储到项目文件夹下
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("userPhoto");
        String phoneNum=params.getParameter("phoneNum");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        String path="D:\\JavaeeWorkSpace\\web\\src\\main\\resources\\static\\upload\\";
        //String path = "C:\\Users\\James Zhang\\Desktop\\数据库课程\\web\\src\\main\\resources\\static\\upload";
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            System.out.println(file);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(path+file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }
        }
        String userPhoto="\\upload\\"+file.getOriginalFilename();
        User user=userService.getUserByPhoneNum(phoneNum);
        return userPhotoService.uploadUserPhoto(user.getUserId(),phoneNum,userPhoto);
    }


    @RequestMapping("/getUserPhoto")
    @ResponseBody
    public List<String> getUserPhoto(@RequestBody Map<String,Object> map){
        System.out.println(map);
        String phoneNum=map.get("phoneNum").toString();
        System.out.println(phoneNum);
        return userPhotoService.getUserPhoto(phoneNum);
    }
}
