package com.example.web.Controller;


import com.example.web.Bean.Error;
import com.example.web.Service.RelationService;
import com.example.web.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class RelationController {
    private static final String NONE = null;
    @Autowired
    private RelationService relationService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getRelation" ,method = RequestMethod.POST)
    @ResponseBody//返回json格式的数据
    public int getRelation(@RequestBody Map<String ,Object> map){
        System.out.println(map);
        Integer userId = userService.getUserIdByPhoneNum(map.get("userPhoneNum").toString());
        Integer targetId = userService.getUserIdByPhoneNum(map.get("targetPhoneNum").toString());
        if(!(userService.checkUserById(userId) && userService.checkUserById(targetId))){
            //不存在userId或者targetId返回999
            return 999;
        }
        return relationService.getRelation(userId,targetId);
    }

    @RequestMapping("addRelation")
    @ResponseBody
    public Error addRelation(@RequestBody Map<String ,Object> map) {
        Integer userId = Integer.parseInt(map.get("userId").toString());
        Integer targetId = Integer.parseInt(map.get("targetId").toString());
        Integer permission = Integer.parseInt(map.get("permission").toString());
        Error msg = new Error();
        if(null == userService.checkUserById(userId)){
            msg.setErrorMsg(641,"不存在用户");
            return msg;
        }
        if(null == userService.checkUserById(targetId)){
            msg.setErrorMsg(641,"不存在目标用户");
            return msg;
        }
        if(null == relationService.checkPermission(permission)){
            msg.setErrorMsg(645,"不存在此关系");
            return msg;
        }
        msg.setErrorId(0);
        msg.setErrorMsg(relationService.addRelation(userId,targetId,permission).toString());
        return msg;
    }

    @RequestMapping("updateRelation")
    @ResponseBody
    public Error updateRelation(@RequestBody Map<String ,Object> map) {
        System.out.println(map);
        Integer userId = Integer.parseInt(map.get("userId").toString());
        Integer targetId = userService.getUserIdByPhoneNum(map.get("phoneNum").toString());
        Integer permission = Integer.parseInt(map.get("status").toString());
        Error msg = new Error();
        if(!userService.checkUserById(userId)){
            msg.setErrorMsg(641,"不存在用户");
            return msg;
        }else{
            System.out.println(userId);
        }
        if(!userService.checkUserById(targetId)){
            msg.setErrorMsg(621,"不存在目标用户");
            return msg;
        }else{
            System.out.println(targetId);
        }
        if(!relationService.checkPermission(permission)){
            msg.setErrorMsg(625,"不存在此关系");
            return msg;
        }else{
            System.out.println(permission);
        }
        msg.setErrorId(0);
        msg.setErrorMsg(relationService.updateRelation(userId,targetId,permission).toString());
        return msg;
    }

    @RequestMapping("deleteRelation")
    @ResponseBody
    public Error deleteRelation(@RequestBody Map<String ,Object> map) {
        Integer userId = Integer.parseInt(map.get("userId").toString());
        Integer targetId = Integer.parseInt(map.get("targetId").toString());
        Error msg = new Error();
        if(!userService.checkUserById(userId)){
            msg.setErrorMsg(631,"不存在用户");
            return msg;
        }
        if(!userService.checkUserById(targetId)){
            msg.setErrorMsg(631,"不存在目标用户");
            return msg;
        }
        msg.setErrorId(0);
        msg.setErrorMsg(relationService.deleteRelation(userId,targetId).toString());
        return msg;
    }


}
