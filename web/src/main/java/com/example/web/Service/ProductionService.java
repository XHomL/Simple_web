package com.example.web.Service;

import com.example.web.Bean.Production;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductionService {

    Boolean addProduction(String productionName,Integer price,String productionPhoto,String means,String type);

    Boolean deleteProductionByName(String productionName,String type);

    //根据传入的productionid判断商品是否存在
    Boolean checkProduction(Integer productionId);

    //获取完整的商品清单
    List<Production> getProductionList();

    //通过商品Id获取商品a
    Production getProductionById(Integer productionId);

    //通过商品Id获取商品价格
    Integer getPriceByProductionId(Integer productionId);

    //通过商品名称获取商品
    Production getProductionByName(String productionName);

    List<Production> getProductionByType(String type);

    Boolean updateProduction(String productionName, Integer price, String means);

    Boolean updateProductionPhoto(String productionName, String productionPhoto);
}
