package com.example.web.Service;

import com.example.web.Bean.Money;

import java.util.Date;
import java.util.List;

public interface MoneyService {
    //用于增加一行新的充值或提现的申请
    Boolean addMoney(Integer cash, Integer bud, Integer corolla, String orderTime, String orderStatus,
                     Integer userId,String username,String phoneNum);

    //通过订单号来更新审核状态，且也为了安全也仅允许更新审核状态(例如存在一种可能性是管理员中有内应，从而将某个提现的申请中的现金金额更改)
    Boolean updateMoney(Integer id,String orderStatus);

    //通过订单号获取订单的详细信息
    Money getMoneyById(Integer id);

    //列出所有的订单
    List<Money> getMoneyList();

    //列出所有充值订单
    List<Money> getRechargeList();

    //列出所有提现订单
    List<Money> getWithdrawList();
}
