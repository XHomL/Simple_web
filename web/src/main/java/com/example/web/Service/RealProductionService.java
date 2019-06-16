package com.example.web.Service;

//通过订单Id删除订单操作完成
//添加订单完成 参数userId targetId production productionNum price
//getRealProductionList 查询所有订单 返回List
//getRealProductionByOrderNum 订单号查询 参数 orderNum 返回json
//getRealProductionByUserId   查询用户订单 参数userId 返回List
//getUserRealProductionList   查询用户某状态订单 参数userId deliveryStatus 返回List
//addRealProduction  添加订单 参数 userId targetId productionId productionNum price 返回Error信息
//updateRealProduction 更新订单 参数例子：orderNum=1&deliveryStatus=123123&deliveryTime=2018-12-04%2012:12:12&deliveryMen=123 返回Error信息
//deleteRealProduction 删除订单 参数orderNum 返回Error信息


import com.example.web.Bean.RealProduction;
import com.example.web.Bean.User;
import com.example.web.Mapper.RealProductionMapper;
import com.example.web.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.server.InactiveGroupException;
import com.example.web.Bean.Error;
import java.util.Date;
import java.util.List;

public interface RealProductionService {
    //基础查询
    Boolean checkRealProductionByOrderNum(Integer orderNum);
    //后台订单查询
    List <RealProduction> getRealProductionList();
    RealProduction getRealProductionByOrderNum(Integer orderNum);
    List<RealProduction> getRealProductionByUserId(Integer userId);


    //前台用户查询自己订单方式 1 - 派送状态
    List<RealProduction> getUserRealProductionList(Integer userId,String deliveryStatus);
    //生成新订单
    Boolean addRealProduction(RealProduction realProduction);
    //更新订单
    Boolean updateRealProduction(RealProduction realProduction);
    //删除订单
    Boolean deleteRealProduction(Integer orderNum);

    List<RealProduction> getAllUserSendRealProductionList(Integer userId);
    List<RealProduction> getAllUserReceiveRealProductionList(Integer userId);

}


