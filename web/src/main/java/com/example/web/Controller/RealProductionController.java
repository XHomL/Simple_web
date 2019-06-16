package com.example.web.Controller;

//通过订单Id删除订单操作完成
//添加订单完成 参数userId targetId production productionNum price
//getRealProductionList 查询所有订单 返回List
//getRealProductionByOrderNum 订单号查询 参数 orderNum 返回json
//getRealProductionByUserId   查询用户订单 参数userId 返回List
//getUserRealProductionList   查询用户某状态订单 参数userId deliveryStatus 返回List
//addRealProduction  添加订单 参数 userId targetId productionId productionNum price 返回Error信息
//updateRealProduction 更新订单 参数例子：orderNum=1&deliveryStatus=123123&deliveryTime=2018-12-04%2012:12:12&deliveryMen=123 返回Error信息
//deleteRealProduction 删除订单 参数orderNum 返回Error信息

import com.example.web.Bean.RealProduction;
import com.example.web.Service.ProductionService;
import com.example.web.Service.RealProductionService;
import com.example.web.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.web.Bean.Error;

import java.util.List;
import java.util.Map;

@RestController
public class RealProductionController {

    private static final String NONE = null;
    @Autowired
    private RealProductionService realProductionService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductionService productionService;
    //添加分页功能
    //
    //
    //
    //后台订单查询
    @RequestMapping("/getRealProductionList")
    public List <RealProduction> getRealProductionList(){
        return realProductionService.getRealProductionList();
    }

    @RequestMapping(value = "/getRealProductionByOrderNum",method = RequestMethod.POST)
    public RealProduction getRealProductionByOrderNum(@RequestBody Map<String,Object> map){
        System.out.println("getRealProductionByOrderNum"+map);
        Integer orderNum = Integer.parseInt(map.get("orderNum").toString());
        RealProduction res = realProductionService.getRealProductionByOrderNum(orderNum);
        res.setProductionName(productionService.getProductionById(res.getProductionId()).getProductionName());
        return res;
    }

    @RequestMapping("/getRealProductionByUserId")
    public List<RealProduction> getRealProductionByUserId(Integer userId){
        return realProductionService.getRealProductionByUserId(userId);
    }


    @RequestMapping(value = "/getAllUserSendRealProductionList",method = RequestMethod.POST)
    public List<RealProduction> getAllUserSendRealProductionList(@RequestBody Map<String,Object> map){
        System.out.println("getAllUserSendRealProductionList"+map);
        Integer userId = userService.getUserIdByPhoneNum(map.get("phoneNum").toString());
        List<RealProduction> res = realProductionService.getAllUserSendRealProductionList(userId);
        System.out.println(res.get(0).getTargetId());
        for(int i=0;i<res.size();i++){
            res.get(i).setUsername(userService.getUserByUserId(res.get(i).getUserId()).getUsername());
            res.get(i).setTargetName(userService.getUserByUserId(res.get(i).getTargetId()).getUsername());
            res.get(i).setProductionName(productionService.getProductionById(res.get(i).getProductionId()).getProductionName());
        }
        return res;
    }

    @RequestMapping(value = "/getAllUserReceiveRealProductionList",method = RequestMethod.POST)
    public List<RealProduction> getAllUserReceiveRealProductionList(@RequestBody Map<String,Object> map){
        System.out.println("getAllUserReceiveRealProductionList"+map);
        Integer userId = userService.getUserIdByPhoneNum(map.get("phoneNum").toString());
        List<RealProduction> res = realProductionService.getAllUserReceiveRealProductionList(userId);
        for(int i=0;i<res.size();i++){
            res.get(i).setUsername(userService.getUserByUserId(res.get(i).getUserId()).getUsername());
            res.get(i).setTargetName(userService.getUserByUserId(res.get(i).getTargetId()).getUsername());
            res.get(i).setProductionName(productionService.getProductionById(res.get(i).getProductionId()).getProductionName());
        }
        return res;
    }

    @RequestMapping(value = "/getUserRealProductionList",method = RequestMethod.POST)
    public List<RealProduction> getUserRealProductionList(Integer userId,String deliveryStatus){
        return realProductionService.getUserRealProductionList(userId,deliveryStatus);

    }

    public Boolean addRealProduction(RealProduction realProduction){
        return realProductionService.addRealProduction(realProduction);
    }

    public Boolean updateRealProduction(RealProduction realProduction){
        return realProductionService.updateRealProduction(realProduction);
    }

    //删除订单
    @RequestMapping(value = "/deleteRealProduction" ,method = RequestMethod.POST)
    @ResponseBody//返回json格式的数据
    public Error deleteRealProduction(@RequestBody Map<String,Object> Map){
        Error res = new Error();
        Integer orderNum = Integer.parseInt(Map.get("orderNum").toString());
        //检查orderNum
        if(null == realProductionService.checkRealProductionByOrderNum(orderNum)){
            res.setErrorId(533);
            res.setErrorMsg("不存在此订单Id");
            return res;
        }
        res.setErrorMsg(realProductionService.deleteRealProduction(orderNum).toString());
        return res;
    }
}
