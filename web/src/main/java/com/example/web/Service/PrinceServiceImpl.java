package com.example.web.Service;

import com.example.web.Bean.Prince;
import com.example.web.Mapper.PrinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PrinceServiceImpl implements PrinceService{

    //此处自动装配会有红线，是因为编写的时候程序未在Mapper文件夹中检测到@Mapper,这是因为我写到了启动中，实际启动后不会出错
    @Autowired
    private PrinceMapper princeMapper;
    @Autowired
    private UserService userService;
    @Override
    //当男神女神表中没有对应的user时，增加一行新的记录
    public Boolean addPrince(Integer userId, String currentTime, Integer receive, Integer send, String phoneNum,String sex,
                             String profile,String username){
        return princeMapper.addPrince(userId,currentTime,receive,send,phoneNum,sex,profile,username);
    }

    @Override
    //通过userId和日期检测男神女神表中是否有对应的用户，若存在对应user则不插入，直接将send或receive的值更改即可
    public Prince checkPrince(Integer userId, String currentTime){
        return princeMapper.checkPrince(userId, currentTime);
    }

    @Override
    //当男神女神表中存在对应的user时，直接将send或receive的值增加即可
    public Boolean addGift(Integer userId, String currentTime, Integer receive, Integer send){
        if(checkPrince(userId,currentTime) == null){
            return addPrince(userId,currentTime,receive,send,userService.getUserByUserId(userId).getPhoneNum(),userService.getUserByUserId(userId).getSex(),
                    userService.getUserByUserId(userId).getProfile(),userService.getUserByUserId(userId).getUsername());
        }else{
            return princeMapper.addGift(userId, currentTime, receive, send);
        }

    }

    @Override
    //指定某个日期列出所有的男神女神
    public List<Prince> getPrinceByDateList(String currentTime){
        return princeMapper.getPrinceByDateList(currentTime);
    }

    @Override
    //列出男神女神表中的所有数据
    public List<Prince> getAllPrinceList(){
        return princeMapper.getAllPrinceList();
    }

    @Override
    //后台给出今日时间来获取男神女神
    public List<Prince> getTodayPrince() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//创建一个日期格式.
        System.out.println(dateFormat);
        Date timeDate = new Date();//util类型
        String tmp = dateFormat.format(timeDate);
        timeDate=dateFormat.parse(tmp);
        System.out.println(tmp);
        System.out.println(princeMapper.getPrinceByDateList(tmp));
        return princeMapper.getPrinceByDateList(tmp);
    }
}
