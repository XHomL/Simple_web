package com.example.web.Service;

import com.example.web.Bean.Money;
import com.example.web.Mapper.MoneyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MoneyServiceImpl implements MoneyService{

    //此处自动装配会有红线，是因为编写的时候程序未在Mapper文件夹中检测到@Mapper,这是因为我写到了启动中，实际启动后不会出错
    @Autowired
    private MoneyMapper moneyMapper;

    @Override
    public Boolean addMoney(Integer cash, Integer bud, Integer corolla, String orderTime, String orderStatus,
                            Integer userId, String username, String phoneNum){
        return moneyMapper.addMoney(cash,bud,corolla,orderTime,orderStatus,userId, username, phoneNum);
    }

    @Override
    public Boolean updateMoney(Integer id,String orderStatus){
        return moneyMapper.updateMoney(id,orderStatus);
    }

    @Override
    public Money getMoneyById(Integer id){
        return moneyMapper.getMoneyById(id);
    }

    @Override
    public List<Money> getMoneyList(){
        return moneyMapper.getMoneyList();
    }

    @Override
    public List<Money> getRechargeList(){return moneyMapper.getRechargeList();}

    @Override
    public List<Money> getWithdrawList(){return moneyMapper.getWithdrawList();}
}
