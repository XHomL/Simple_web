package com.example.web.Controller;

import com.example.web.Bean.Error;
import com.example.web.Bean.Manager;
import com.example.web.Service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @RequestMapping("/managerLogin")
    @ResponseBody
    //返回一个Map
    //若用户名与密码在数据库中不匹配或不存在，则Map中的String值为fail,Object值为manager=null
    //若用户成功登陆,则返回String值success，前端通过Boolean值判断是否登陆成功
    public Boolean managerLogin(HttpServletResponse response, HttpServletRequest request, @RequestBody Map<String, Object> map) {
        String managerName=map.get("managerName").toString();
        String password=map.get("password").toString();
        System.out.println(managerName);
        System.out.println(password);
        Manager manager = managerService.managerLogin(managerName, password);
        System.out.println(manager);
        if (null == manager) {
            System.out.println("false");
            return false;
        } else {
            Cookie cookie = new Cookie("managerName",managerName);
            HttpSession session = request.getSession();
            response.addCookie(cookie);
            System.out.println("true");
            return true;
        }
    }

    @RequestMapping("/addManager")
    @ResponseBody
    public Boolean addManager(@RequestBody Map<String,Object> map) {
        System.out.println(map);
        String managerName=map.get("managerName").toString();
        String password=map.get("password").toString();
        Manager manager = new Manager(managerName, password);
        if (null == managerService.checkManagerName(manager.getManagerName()))
            return managerService.addManager(manager.getManagerName(), manager.getPassword());
        //若数据库表中已存在相同的用户名，则返回false
        else
            return false;
    }

    @RequestMapping("/adminLogin")
    public String adminLoginhtml(){
        return "adminLogin";
    }

    @RequestMapping("/getManagerList")
    @ResponseBody
    public List<Manager> getManagerList(){return managerService.getManagerList();}
}
