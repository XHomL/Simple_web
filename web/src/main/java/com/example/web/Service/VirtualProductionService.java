package com.example.web.Service;


import com.example.web.Bean.VirtualProduction;

import java.util.List;

public interface VirtualProductionService {
    //基础查询
    Boolean checkVirtualProductionByOrderNum(Integer orderNum);
    //后台订单查询
    List<VirtualProduction> getVirtualProductionList();
    VirtualProduction getVirtualProductionByOrderNum(Integer orderNum);
    List<VirtualProduction> getSendVirtualProductionByUserId(Integer userId);
    List<VirtualProduction> getReceiveVirtualProductionByUserId(Integer userId);

    //生成新订单
    Boolean addVirtualProduction(VirtualProduction virtualProduction);
    //删除订单
    Boolean deleteVirtualProduction(Integer orderNum);
}
