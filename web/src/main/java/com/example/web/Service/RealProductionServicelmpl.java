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

import com.example.web.Bean.Error;
import com.example.web.Bean.RealProduction;
import com.example.web.Mapper.RealProductionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RealProductionServicelmpl implements RealProductionService{
    @Autowired
    private RealProductionMapper realProductionMapper;
    //基础查询
    @Override
    public Boolean checkRealProductionByOrderNum(Integer orderNum){
        return realProductionMapper.checkRealProductionByOrderNum(orderNum);
    }
    //后台查询方式1 - 订单id 2 - 用户id
    @Override
    public List<RealProduction> getRealProductionList(){
        return realProductionMapper.getRealProductionList();
    }
    @Override
    public RealProduction getRealProductionByOrderNum(Integer orderNum){
        return realProductionMapper.getRealProductionByOrderNum(orderNum);
    }
    @Override
    public List<RealProduction> getRealProductionByUserId(Integer userId){
        return realProductionMapper.getRealProductionByUserId(userId);
    }
    //前台用户查询自己订单方式 1 - 派送状态
    @Override
        public List<RealProduction> getAllUserSendRealProductionList(Integer userId) {
        return realProductionMapper.getAllUserRealProductionList(userId);
    }
    @Override
        public List<RealProduction> getAllUserReceiveRealProductionList(Integer userId){
        return realProductionMapper.getAllUserReceiveRealProductionList(userId);
    }
    @Override
    public List<RealProduction> getUserRealProductionList(Integer userId,String deliveryStatus){

        return realProductionMapper.getUserRealProductionList(userId,deliveryStatus);
    }
    //生成新订单
    @Override
    public Boolean addRealProduction(RealProduction realProduction){
        return realProductionMapper.addRealProduction(realProduction);
    }
    //更新订单
    @Override
    public Boolean updateRealProduction(RealProduction realProduction){
        return realProductionMapper.updateRealProduction(realProduction);
    }
    //删除订单
    @Override
    public Boolean deleteRealProduction(Integer orderNum){
        return realProductionMapper.deleteRealProduction(orderNum);
    }


}
