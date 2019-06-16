package com.example.web.Service;

import com.example.web.Bean.VirtualProduction;
import com.example.web.Mapper.VirtualProductionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VirtualProductionServicelmpl implements VirtualProductionService{
    @Autowired
    private VirtualProductionMapper virtualProductionMapper;
    //基础查询
    @Override
    public Boolean checkVirtualProductionByOrderNum(Integer orderNum){
        return virtualProductionMapper.checkVirtualProductionByOrderNum(orderNum);
    }
    //后台查询方式1 - 订单id 2 - 用户id
    @Override
    public List<VirtualProduction> getVirtualProductionList(){
        return virtualProductionMapper.getVirtualProductionList();
    }
    @Override
    public VirtualProduction getVirtualProductionByOrderNum(Integer orderNum){
        return virtualProductionMapper.getVirtualProductionByOrderNum(orderNum);
    }
    @Override
    public List<VirtualProduction> getSendVirtualProductionByUserId(Integer userId){
        return virtualProductionMapper.getSendVirtualProductionByUserId(userId);
    }
    @Override
    public List<VirtualProduction> getReceiveVirtualProductionByUserId(Integer userId){
        return virtualProductionMapper.getReceiveVirtualProductionByUserId(userId);
    }
    //生成新订单
    @Override
    public Boolean addVirtualProduction(VirtualProduction virtualProduction){
        return virtualProductionMapper.addVirtualProduction(virtualProduction);
    }

    //删除订单
    @Override
    public Boolean deleteVirtualProduction(Integer orderNum){
        return virtualProductionMapper.deleteVirtualProduction(orderNum);
    }



}
