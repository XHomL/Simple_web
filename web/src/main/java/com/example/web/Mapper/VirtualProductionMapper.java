package com.example.web.Mapper;

import com.example.web.Bean.VirtualProduction;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface VirtualProductionMapper {
    @Select("select * from virtualProduction")
    List<VirtualProduction> getVirtualProductionList();
    @Select("select * from virtualProduction where orderNum = #{orderNum}")
    Boolean checkVirtualProductionByOrderNum(Integer orderNum);
    @Select("select * from virtualProduction where orderNum = #{orderNum}")
    VirtualProduction getVirtualProductionByOrderNum(Integer orderNum);
    @Select("select * from virtualProduction where userId = #{userId}")
    List<VirtualProduction> getSendVirtualProductionByUserId(Integer userId);
    @Select("select * from virtualProduction where targetId = #{userId}")
    List<VirtualProduction> getReceiveVirtualProductionByUserId(Integer userId);
    @Insert("Insert into virtualProduction (orderNum,userId,targetId,orderTime,productionId,price,productionNum,total)" +
            "value(DEFAULT,#{userId},#{targetId},#{orderTime},#{productionId},#{price},#{productionNum},#{productionNum}*#{price})")
    Boolean addVirtualProduction(VirtualProduction virtualProduction);
    @Delete("delete from virtualProduction where orderNum = #{orderNum}")
    Boolean deleteVirtualProduction(Integer orderNum);
}
