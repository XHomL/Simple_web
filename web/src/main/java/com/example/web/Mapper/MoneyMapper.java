package com.example.web.Mapper;

import com.example.web.Bean.Money;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface MoneyMapper {
    //用于增加一行新的充值或提现的申请
    //如果有多个参数，每一个参数均需要使用@Param声明，否则会报错
    @Insert("INSERT INTO money (id, cash, bud, corolla, orderTime, orderStatus, userId, username, phoneNum) VALUES (DEFAULT, #{cash}, #{bud}," +
            " #{corolla}, #{orderTime}, #{orderStatus}, #{userId}, #{username}, #{phoneNum})")
    Boolean addMoney(@Param("cash") Integer cash,@Param("bud") Integer bud,@Param("corolla") Integer corolla,
                     @Param("orderTime") String orderTime,@Param("orderStatus") String orderStatus,
                     @Param("userId") Integer userId,@Param("username")String username,@Param("phoneNum")String phoneNum);

    //通过订单号来更新审核状态，且也为了安全也仅允许更新审核状态(例如存在一种可能性是管理员中有内应，从而将某个提现的申请中的现金金额更改)
    //如果有多个参数，每一个参数均需要使用@Param声明，否则会报错
    @Update("Update money SET orderStatus = #{orderStatus} WHERE id = #{id}")
    Boolean updateMoney(@Param("id") Integer id,@Param("orderStatus") String orderStatus);

    //通过订单号获取订单的详细信息
    @Select("SELECT * FROM money WHERE id = #{id}")
    Money getMoneyById(Integer id);

    //列出所有的充值订单
    @Select("SELECT * FROM money")
    List<Money> getMoneyList();

    @Select("SELECT * FROM money WHERE cash > 0 AND bud > 0")
    List<Money> getRechargeList();

    @Select("SELECT * FROM money WHERE bud < 0 OR corolla < 0")
    List<Money> getWithdrawList();
}
