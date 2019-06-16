package com.example.web.Controller;

import com.example.web.Bean.Money;
import com.example.web.Bean.User;
import com.example.web.Service.MoneyService;
import com.example.web.Service.UserService;
import com.example.web.common.UserCommon;
import com.example.web.Bean.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class MoneyController {
    @Autowired
    private MoneyService moneyService;
    @Autowired
    private UserCommon userCommon;
    @Autowired
    private UserService userService;

    @RequestMapping("/addBud")
    @ResponseBody
    public Boolean addBud(@RequestBody Map<String,Object> map) {
        Money money = new Money();
        Integer cash;
        System.out.println(map);
        if(null==map.get("cash"))
            cash=0;
        else
            cash=Integer.parseInt(map.get("cash").toString());
        money.setCash(cash);
        System.out.println(map);
        Integer bud=cash*10;
        money.setBud(bud);
        Integer corolla=0;
        money.setCorolla(corolla);
        String phoneNum=map.get("phoneNum").toString();
        System.out.println(phoneNum);
        User user = userService.getUserByPhoneNum(phoneNum);
        money.setUsername(user.getUsername());
        money.setPhoneNum(phoneNum);
        DateFormat orderTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time=new Date();
        money.setOrderTime(orderTime.format(time));
        money.setOrderStatus("已通过");
        Error error = new Error();
        error = userService.chgBud(user.getUserId(),bud);
        money.setUserId(user.getUserId());
        Boolean res = moneyService.addMoney(money.getCash(),money.getBud(),money.getCorolla(),money.getOrderTime(),
                money.getOrderStatus(),money.getUserId(),money.getUsername(),money.getPhoneNum());
        return res;
    }

    @RequestMapping("/addCash")
    @ResponseBody
    public Boolean addCash(@RequestBody Map<String,Object> map){
        Money money = new Money();
        Integer corolla;
        if(null==map.get("corolla"))
            corolla=0;
        else
            corolla=Integer.parseInt(map.get("corolla").toString());
        money.setCorolla(corolla);
        Integer cash=corolla*5;
        corolla*=-1;
        money.setCash(cash);
        Integer bud=0;
        money.setBud(bud);
        String phoneNum=map.get("phoneNum").toString();
        System.out.println(phoneNum);
        User user = userService.getUserByPhoneNum(phoneNum);
        money.setUsername(user.getUsername());
        money.setPhoneNum(phoneNum);
        DateFormat orderTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time=new Date();
        money.setOrderTime(orderTime.format(time));
        money.setOrderStatus("待审核");
        money.setUserId(user.getUserId());
        Boolean res = moneyService.addMoney(money.getCash(),money.getBud(),money.getCorolla(),money.getOrderTime(),
                money.getOrderStatus(),money.getUserId(),money.getUsername(),money.getPhoneNum());
        return res;
    }

    @RequestMapping("/addCorolla")
    @ResponseBody
    public Boolean addCorolla(@RequestBody Map<String,Object> map){
        Money money = new Money();
        Integer corolla;
        if(null==map.get("corolla"))
            corolla=0;
        else
            corolla=Integer.parseInt(map.get("corolla").toString());
        System.out.println(corolla);
        money.setCorolla(corolla);
        //由于花芽转花冠不涉及现金，故将现金设置为0
        Integer cash=0;
        money.setCash(cash);
        //所需消耗的花芽数是花冠数*1500
        Integer bud=corolla*1500*(-1);
        money.setBud(bud);
        String phoneNum=map.get("phoneNum").toString();
        System.out.println(phoneNum);
        User user = userService.getUserByPhoneNum(phoneNum);
        money.setUsername(user.getUsername());
        money.setPhoneNum(phoneNum);
        //设置花芽转花冠订单时间格式
        DateFormat orderTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time=new Date();
        money.setOrderTime(orderTime.format(time));
        money.setOrderStatus("待审核");
        money.setUserId(user.getUserId());
        //传递给Service，然后再传递给Mapper以将此订单写入数据库中
        Boolean res = moneyService.addMoney(money.getCash(),money.getBud(),money.getCorolla(),money.getOrderTime(),
                money.getOrderStatus(),money.getUserId(),money.getUsername(),money.getPhoneNum());
        return res;
    }

    @RequestMapping("/updateMoney")
    @ResponseBody
    public Boolean updateMoney(@RequestBody Map<String,Object> map){
        Money money = new Money();
        money.setId(Integer.parseInt(map.get("id").toString()));
        money.setOrderStatus(map.get("orderStatus").toString());
        Boolean res = moneyService.updateMoney(money.getId(),money.getOrderStatus());
        return res;
    }

    @RequestMapping("/getMoneyById")
    @ResponseBody
    public Money getMoneyById(Integer id){
        return moneyService.getMoneyById(id);
    }

    @RequestMapping("/getMoneyList")
    @ResponseBody
    public List<Money> getMoneyList(){
        return moneyService.getMoneyList();
    }

    //获取所有充值订单
    @RequestMapping("/getRechargeList")
    @ResponseBody
    public List<Money> getRechargeList(){return moneyService.getRechargeList();}

    //获取所有提现订单
    @RequestMapping("/getWithdrawList")
    @ResponseBody
    public List<Money> getWithdrawList(){return moneyService.getWithdrawList();}


    //审核通过后将花芽转化为花冠
    @RequestMapping("/checkMoney")
    @ResponseBody
    public Boolean budToCorolla(@RequestBody Map<String,Object>map){
        Integer id=Integer.parseInt(map.get("id").toString());
        Money money=getMoneyById(id);
        System.out.println(id);
        String phoneNum=money.getPhoneNum();
        User user=userService.getUserByPhoneNum(phoneNum);
        Error error = new Error();
        Integer bud=money.getBud();
        Integer corolla=money.getCorolla();
        //若Bud<0，说明是花芽转花冠订单
        if(bud<0)
        {
            System.out.println("budbud");
            error=userService.chgBud1(user.getUserId(),bud);
            if(error.getErrorId()==0){
                moneyService.updateMoney(id,"已通过");
                return true;
            }
            else
            {
                moneyService.updateMoney(id,"余额不足");
                return false;
            }
        }
        //若corolla<0，则
        else if(corolla<0)
        {
            System.out.println("corolla");
            error = userService.chgCorolla(user.getUserId(),corolla);
            System.out.println("error msg"+error.getErrorMsg());
            if(error.getErrorId()==0){
                moneyService.updateMoney(id,"已通过");
                return true;
            }
            else
            {
                moneyService.updateMoney(id,"余额不足");
                return false;
            }
        }
        return false;
    }

    //审核通过后将现金转化为花芽
    @RequestMapping("/cashToBud")
    @ResponseBody
    public Boolean cashToBud(@RequestBody Map<String,Object>map){
        Integer cash=Integer.parseInt(map.get("cash").toString());
        String phoneNum=map.get("phoneNum").toString();
        User user=userService.getUserByPhoneNum(phoneNum);
        Integer id=Integer.parseInt(map.get("id").toString());
        Error error = new Error();
        int bud = cash*10;
        error = userService.chgBud(user.getUserId(),bud);
        moneyService.updateMoney(id,"已通过");
        return true;
    }

    //审核通过后将花冠转化为现金
    @RequestMapping("/corollaToCash")
    @ResponseBody
    public Boolean corollaToCash(Integer id, Integer userId, Integer corolla){
        Error error = new Error();
        error = userService.chgCorolla(userId,corolla);
        if(error.getErrorId()!=0){
            moneyService.updateMoney(id,"已通过");
            return true;
        }
        else
        {
            moneyService.updateMoney(id,"余额不足");
            return false;
        }
    }
}
