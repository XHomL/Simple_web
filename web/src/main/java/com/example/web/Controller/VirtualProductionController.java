package com.example.web.Controller;


import com.example.web.Bean.VirtualProduction;
import com.example.web.Service.ProductionService;
import com.example.web.Service.UserService;
import com.example.web.Service.VirtualProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class VirtualProductionController {

    @Autowired
    private VirtualProductionService virtualProductionService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductionService productionService;
    public Boolean checkVirtualProductionByOrderNum(Integer orderNum){
        return virtualProductionService.checkVirtualProductionByOrderNum(orderNum);
    }
    //后台订单查询
    @RequestMapping(value = "/getVirtualProductionList",method = RequestMethod.POST)
    public List<VirtualProduction> getVirtualProductionList(){
        return virtualProductionService.getVirtualProductionList();
    }
    @RequestMapping(value = "getVirtualProductionByOrderNum",method = RequestMethod.POST)
    public VirtualProduction getVirtualProductionByOrderNum(Integer orderNum){
        return virtualProductionService.getVirtualProductionByOrderNum(orderNum);
    }

    @RequestMapping(value = "getSendVirtualProductionByUserId",method = RequestMethod.POST)
    public List<VirtualProduction> getSendVirtualProductionByUserId(@RequestBody Map<String,Object> map){

        System.out.println("getSendVirtualProductionByUserId"+map);
        Integer userId = userService.getUserIdByPhoneNum(map.get("phoneNum").toString());
        List<VirtualProduction> res = virtualProductionService.getSendVirtualProductionByUserId(userId);
        for(int i=0;i<res.size();i++){
            res.get(i).setUsername(userService.getUserByUserId(userId).getUsername());
            res.get(i).setTargetName(userService.getUserByUserId(res.get(i).getTargetId()).getUsername());
            res.get(i).setProductionName(productionService.getProductionById(res.get(i).getProductionId()).getProductionName());
        }
        return res;
    }

    @RequestMapping(value = "getReceiveVirtualProductionByUserId",method = RequestMethod.POST)
    public List<VirtualProduction> getReceiveVirtualProductionByUserId(@RequestBody Map<String ,Object> map){
        System.out.println(map);
        Integer userId = userService.getUserIdByPhoneNum(map.get("phoneNum").toString());
        List<VirtualProduction> res = virtualProductionService.getReceiveVirtualProductionByUserId(userId);
        for(int i=0;i<res.size();i++){
            res.get(i).setUsername(userService.getUserByUserId(userId).getUsername());
            res.get(i).setTargetName(userService.getUserByUserId(res.get(i).getTargetId()).getUsername());
            res.get(i).setProductionName(productionService.getProductionById(res.get(i).getProductionId()).getProductionName());
        }
        return res;
    }


    //删除订单
    @RequestMapping(value = "deleteVirtualProduction",method = RequestMethod.POST)
    public Boolean deleteVirtualProduction(Integer orderNum){
        return virtualProductionService.deleteVirtualProduction(orderNum);
    }
}
