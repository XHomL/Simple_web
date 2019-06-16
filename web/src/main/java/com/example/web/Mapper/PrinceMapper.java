package com.example.web.Mapper;



import com.example.web.Bean.Prince;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PrinceMapper {
    //当男神女神表中没有对应的user时，增加一行新的记录
    //如果有多个参数，每一个参数均需要使用@Param声明，否则会报错
    @Insert("INSERT INTO prince (id, userId, currentTime, receive, send, phoneNum, sex, username) VALUES " +
            "(DEFAULT, #{userId}, #{currentTime}, " +
            "#{receive}, #{send}, #{phoneNum}, #{sex}, #{username})")
    Boolean addPrince(@Param("userId") Integer userId,@Param("currentTime") String currentTime,@Param("receive") Integer receive,
                      @Param("send") Integer send,@Param("phoneNum")String phoneNum,@Param("sex")String sex,
                      @Param("profile")String profile, @Param("username")String username);

    //通过userId和日期检测男神女神表中是否有对应的用户，若存在对应user则不插入，直接将send或receive的值更改即可
    //如果有多个参数，每一个参数均需要使用@Param声明，否则会报错
    @Select("SELECT * FROM prince WHERE userId = #{userId} AND currentTime = #{currentTime}")
    Prince checkPrince(@Param("userId") Integer userId,@Param("currentTime") String currentTime);

    //当男神女神表中存在对应的user时，直接将send或receive的值增加即可
    //如果有多个参数，每一个参数均需要使用@Param声明，否则会报错
    @Update("Update prince SET send = send + #{send}, receive = receive + #{receive} WHERE userId = #{userId} AND currentTime = #{currentTime}")
    Boolean addGift(@Param("userId") Integer userId,@Param("currentTime") String currentTime,@Param("receive") Integer receive,@Param("send") Integer send);

    //指定某个日期列出所有的男神女神,根据收到礼物的多少进行排名
    @Select("select * from prince where currentTime = #{currentTime} group by sex order by receive desc")
    List<Prince> getPrinceByDateList(@Param("currentTime") String currentTime);

    //列出男神女神表中的所有数据,按收礼的降序排序
    @Select("select * from prince order by receive desc")
    List<Prince> getAllPrinceList();
}
