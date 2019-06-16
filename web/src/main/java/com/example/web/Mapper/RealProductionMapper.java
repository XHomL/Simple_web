package com.example.web.Mapper;


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
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import com.example.web.Bean.Error;

public interface RealProductionMapper {
    //基础查询
    @Select("SELECT * FROM realProduction WHERE orderNum = #{orderNum}")
    Boolean checkRealProductionByOrderNum(Integer orderNum);

    //后台查询方式1 - 订单id 2 - 用户id
    @Select("SELECT * FROM realProduction")
    List <RealProduction> getRealProductionList();
    @Select("SELECT * FROM realProduction WHERE orderNum = #{orderNum}")
    RealProduction getRealProductionByOrderNum(Integer orderNum);
    @Select("SELECT * FROM realProduction WHERE userId = #{userId}")
    List<RealProduction> getRealProductionByUserId(Integer userId);

    //前台用户查询自己订单方式 1 - 派送状态
    @Select("SELECT * FROM realProduction WHERE userId = #{userId}")
    List<RealProduction> getAllUserRealProductionList(Integer userId);
    @Select("SELECT * FROM realProduction WHERE targetId = #{userId}")
    List<RealProduction> getAllUserReceiveRealProductionList(Integer userId);
    @Select("SELECT * FROM realProduction WHERE userId = #{userId} and deliveryStatus = #{deliveryStatus}")
    List<RealProduction> getUserRealProductionList(@Param("userId")Integer userId,@Param("deliveryStatus")String deliveryStatus);

    //生成新订单
    @Insert("INSERT INTO realProduction(orderNum,userId,targetId,orderTime,productionId,productionNum,price,total,deliveryStatus,deliveryTime,deliveryMen)" +
            "value (DEFAULT,#{userId},#{targetId},#{orderTime},#{productionId},#{productionNum},#{price},#{price}*#{productionNum}," +
            "#{deliveryStatus},#{deliveryTime},#{deliveryMen})")
    Boolean addRealProduction(RealProduction realProduction);

    //更新订单
    @Update("UPDATE realProduction SET deliveryStatus = #{deliveryStatus} , deliveryTime = #{deliveryTime} , deliveryMen = #{deliveryMen} where orderNum = #{orderNum}")
    Boolean updateRealProduction(RealProduction realProduction);

    //删除订单
    @Delete("DELETE from realProduction where orderNum = #{orderNum} ")
    Boolean deleteRealProduction(Integer orderNum);
}
