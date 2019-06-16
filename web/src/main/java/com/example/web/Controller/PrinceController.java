package com.example.web.Controller;

import com.example.web.Bean.Prince;
import com.example.web.Service.PrinceService;
import com.example.web.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
public class PrinceController {
    @Autowired
    private PrinceService princeService;
    @Autowired
    private UserService userService;
    @RequestMapping("/addPrince")
    @ResponseBody
    public Boolean addPrince(@RequestParam Map<String,Object> map) throws ParseException {
        Prince prince = new Prince();
        prince.setUserId(Integer.parseInt(map.get("userId").toString()));
        DateFormat currentTime = new SimpleDateFormat("yyyy-MM-dd");
        prince.setCurrentTime(map.get("currentTime").toString());
        prince.setReceive(Integer.parseInt(map.get("receive").toString()));
        prince.setSend(Integer.parseInt(map.get("send").toString()));
        prince.setPhoneNum(map.get("phoneNum").toString());
        prince.setSex(map.get("sex").toString());
        prince.setProfile(map.get("profile").toString());
        prince.setUsername(map.get("username").toString());
        Prince check=princeService.checkPrince(prince.getUserId(),prince.getCurrentTime());
        //若通过日期和userId未找到匹配的行，则生成新行
        if(null==check){
            return princeService.addPrince(prince.getUserId(),prince.getCurrentTime(),prince.getReceive(),prince.getSend(),
                    prince.getPhoneNum(),prince.getSex(),prince.getProfile(),prince.getUsername());
        }
        //若通过日期和userId找到匹配行，则直接将该行的receive和send增加
        else{
            return princeService.addGift(prince.getUserId(),prince.getCurrentTime(),prince.getReceive(),prince.getSend());
        }
    }

    public Boolean addPrince(Prince prince){
        System.out.println(prince.getUserId());
        Prince check=princeService.checkPrince(prince.getUserId(),prince.getCurrentTime());
        prince.setPhoneNum(userService.getUserByUserId(prince.getUserId()).getPhoneNum());
        prince.setSex(userService.getUserByUserId(prince.getUserId()).getSex());
        prince.setProfile(userService.getUserByUserId(prince.getUserId()).getProfile());
        prince.setUsername(userService.getUserByUserId(prince.getUserId()).getUsername());
        //若通过日期和userId未找到匹配的行，则生成新行
        if(null==check){
            return princeService.addPrince(prince.getUserId(),prince.getCurrentTime(),prince.getReceive(),prince.getSend(),
                    prince.getPhoneNum(),prince.getSex(),prince.getProfile(),prince.getUsername());
        }
        //若通过日期和userId找到匹配行，则直接将该行的receive和send增加
        else{
            return princeService.addGift(prince.getUserId(),prince.getCurrentTime(),prince.getReceive(),prince.getSend());
        }
    }

    @RequestMapping("/getPrinceByDateList")
    @ResponseBody
    public List<Prince> getPrinceByDateList(String currentTime){
        return princeService.getPrinceByDateList(currentTime);
    }

    @RequestMapping("/getAllPrinceList")
    @ResponseBody
    public List<Prince> getAllPrinceList(){
        return princeService.getAllPrinceList();
    }

    @RequestMapping("/getTodayPrince")
    @ResponseBody
    public List<Prince> getTodayPrince() throws ParseException{
        return princeService.getTodayPrince();
    }
}
