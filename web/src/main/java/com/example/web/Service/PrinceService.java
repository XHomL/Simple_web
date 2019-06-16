package com.example.web.Service;

import com.example.web.Bean.Prince;

import java.text.ParseException;
import java.util.List;

public interface PrinceService {
    //当男神女神表中没有对应的user时，增加一行新的记录
    Boolean addPrince(Integer userId, String currentTime, Integer receive, Integer send, String phoneNum,String sex,
                      String profile,String username);

    //通过userId和日期检测男神女神表中是否有对应的用户，若存在对应user则不插入，直接将send或receive的值更改即可
    Prince checkPrince(Integer userId, String currentTime);

    //当男神女神表中存在对应的user时，直接将send或receive的值增加即可
    Boolean addGift(Integer userId, String currentTime, Integer receive, Integer send);

    //指定某个日期列出所有的男神女神
    List<Prince> getPrinceByDateList(String currentTime);

    //列出男神女神表中的所有数据
    List<Prince> getAllPrinceList();

    //后台给出今日时间来获取男神女神
    List<Prince> getTodayPrince() throws ParseException;
}
