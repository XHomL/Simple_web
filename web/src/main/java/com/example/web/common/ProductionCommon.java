package com.example.web.common;

import com.example.web.Bean.Error;
import com.example.web.Bean.Prince;
import com.example.web.Bean.RealProduction;
import com.example.web.Bean.VirtualProduction;
import com.example.web.Controller.PrinceController;
import com.example.web.Controller.ProductionController;
import com.example.web.Controller.RealProductionController;
import com.example.web.Controller.VirtualProductionController;
import com.example.web.Service.PrinceService;
import com.example.web.Service.RealProductionService;
import com.example.web.Service.UserService;
import com.example.web.Service.VirtualProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ProductionCommon {
    @Autowired
    private UserService userService;
    @Autowired
    private RealProductionService realProductionService;

    @Autowired
    private VirtualProductionService virtualProductionService;
    @Autowired
    private PrinceService princeService;
    private PrinceController princeController;

    @RequestMapping("/addRealProduction")
    @ResponseBody//返回json格式的数据
    public Error addRealProduction(@RequestBody Map<String, Object> map) {
        RealProduction realProduction = new RealProduction();
        System.out.println(map);
        System.out.println((ArrayList<Integer>) map.get("itemArr"));
        List<Integer> productionId = (ArrayList<Integer>) map.get("itemArr");
        List<Integer> productionNum = (ArrayList<Integer>) map.get("numArr");
        List<Integer> productionPrice = (ArrayList<Integer>) map.get("priceArr");
        System.out.println(productionNum.get(0));
        //map获取前台传参
        realProduction.setUserId(userService.getUserIdByPhoneNum((map.get("phoneNum").toString())));
        realProduction.setUsername(userService.getUserByPhoneNum(map.get("phoneNum").toString()).getUsername());
        realProduction.setTargetId(userService.getUserIdByPhoneNum(map.get("targetId").toString()));
        realProduction.setTargetName(userService.getUserByUserId(realProduction.getTargetId()).toString());
        if (productionId.get(0) == null || productionNum.get(0) == null || productionPrice.get(0) == null || !(productionId.size() == productionNum.size() && productionNum.size() == productionPrice.size())) {
            Error res = new Error();
            res.setErrorMsg(999, "前端的锅");
            return res;
        }
        Integer TotalMoney = 0;
        for (int i = 0; i < productionPrice.size(); i++) {
            TotalMoney += productionPrice.get(i) * productionNum.get(i);
        }
        //装载订单时间
        Date date = new Date(System.currentTimeMillis());
        //不知道这行是干嘛的，留着万一用得到
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        realProduction.setOrderTime(dateFormat.format(date));
        //装载总价格
        realProduction.setTotal(realProduction.getOrderNum() * realProduction.getPrice());
        //装载状态时间和配送员
        realProduction.setDeliveryStatus("准备配送");
        realProduction.setDeliveryTime("2018-12-20 11:11:11");
        realProduction.setDeliveryMen("娃哈哈");


        //检查信息错误
        Error res = new Error();
        if (null == userService.checkUserById((realProduction.getUserId()))) {
            res.setErrorId(541);
            res.setErrorMsg("不存在用户");
            return res;
        }
        if (null == userService.checkUserById(realProduction.getTargetId())) {
            res.setErrorId(541);
            res.setErrorMsg("不存在目标用户");
            return res;
        }
        if (userService.chgBud(realProduction.getUserId(), -TotalMoney).getErrorId() == 0) {
            for (int i = 0; i < productionId.size(); i++) {
                realProduction.setProductionId(Integer.parseInt(productionId.get(i).toString()));
                realProduction.setProductionNum(Integer.parseInt(productionNum.get(i).toString()));
                realProduction.setPrice(Integer.parseInt(productionPrice.get(i).toString()));
                realProductionService.addRealProduction(realProduction);

                princeService.addGift(realProduction.getTargetId(),dateFormat2.format(date),TotalMoney,0);

                princeService.addGift(realProduction.getUserId(),dateFormat2.format(date),0,TotalMoney);
            }
            res.setErrorMsg(0, "add Finish");
            return res;
        } else {
            res.setErrorMsg(548, "穷逼");
        }
        return res;
    }

    /**
     * 后台更新配送状态，时间，配送员
     *
     * @param map
     * @return
     * @throws ParseException
     */
    @RequestMapping("/updateRealProduction")
    @ResponseBody
    public Error updateRealProduction(@RequestBody Map<String, Object> map) throws ParseException {
        RealProduction realProduction = new RealProduction();
        System.out.println(map);
        realProduction.setOrderNum(Integer.parseInt(map.get("orderNum").toString()));
        //只允许更改配送状态、配送时间、配送员
        realProduction.setDeliveryStatus(map.get("deliveryStatus").toString());
//        SimpleDateFormat transDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        realProduction.setDeliveryTime(map.get("deliveryTime").toString());
        realProduction.setDeliveryMen(map.get("deliveryMen").toString());
        Error res = new Error();
        //检查orderNum
        if (null == realProductionService.checkRealProductionByOrderNum(realProduction.getOrderNum())) {
            res.setErrorId(523);
            res.setErrorMsg("不存在此订单Id");
            return res;
        }
        //检查userId
//        System.out.println(realProductionService.updateRealProduction(realProduction));
        res.setErrorMsg(realProductionService.updateRealProduction(realProduction).toString());
        return res;
    }

    @RequestMapping("/addVirtualProduction")
    @ResponseBody
    public Error addVirtualProduction(@RequestBody Map<String, Object> map) {
        VirtualProduction virtualProduction = new VirtualProduction();
        System.out.println(map);
        System.out.println((ArrayList<Integer>) map.get("itemArr"));
        List<Integer> productionId = (ArrayList<Integer>) map.get("itemArr");
        List<Integer> productionNum = (ArrayList<Integer>) map.get("numArr");
        List<Integer> productionPrice = (ArrayList<Integer>) map.get("priceArr");
        System.out.println(productionNum.get(0));
        //map获取前台传参
        virtualProduction.setUserId(userService.getUserIdByPhoneNum((map.get("phoneNum").toString())));
        virtualProduction.setUsername(userService.getUserByPhoneNum(map.get("phoneNum").toString()).getUsername());
        virtualProduction.setTargetId(userService.getUserIdByPhoneNum(map.get("targetId").toString()));
        virtualProduction.setTargetName(userService.getUserByUserId(virtualProduction.getTargetId()).toString());
        if (productionId.get(0) == null || productionNum.get(0) == null || productionPrice.get(0) == null || !(productionId.size() == productionNum.size() && productionNum.size() == productionPrice.size())) {
            Error res = new Error();
            res.setErrorMsg(999, "前端的锅");
            return res;
        }
        Integer TotalMoney = 0;
        for (int i = 0; i < productionPrice.size(); i++) {
            TotalMoney += productionPrice.get(i) * productionNum.get(i);
        }
        //装载订单时间
        Date date = new Date(System.currentTimeMillis());
        //不知道这行是干嘛的，留着万一用得到
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        virtualProduction.setOrderTime(dateFormat.format(date));
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        //装载总价格
        virtualProduction.setTotal(virtualProduction.getOrderNum() * virtualProduction.getPrice());


        //检查信息错误
        Error res = new Error();
        if (null == userService.checkUserById((virtualProduction.getUserId()))) {
            res.setErrorId(541);
            res.setErrorMsg("不存在用户");
            return res;
        }
        if (null == userService.checkUserById(virtualProduction.getTargetId())) {
            res.setErrorId(541);
            res.setErrorMsg("不存在目标用户");
            return res;
        }
        System.out.println(TotalMoney);
        if (userService.chgBud(virtualProduction.getUserId(), -TotalMoney).getErrorId() == 0) {
            for (int i = 0; i < productionId.size(); i++) {
                virtualProduction.setProductionId(Integer.parseInt(productionId.get(i).toString()));
                virtualProduction.setProductionNum(Integer.parseInt(productionNum.get(i).toString()));
                virtualProduction.setPrice(Integer.parseInt(productionPrice.get(i).toString()));
                virtualProductionService.addVirtualProduction(virtualProduction);
            }
            res.setErrorMsg(0, "add Finish");
            princeService.addGift(virtualProduction.getTargetId(),dateFormat2.format(date),TotalMoney,0);
            princeService.addGift(virtualProduction.getUserId(),dateFormat2.format(date),0,TotalMoney);
            return res;
        } else {
            res.setErrorMsg(548, "穷逼");
        }
        return res;

    }
}

