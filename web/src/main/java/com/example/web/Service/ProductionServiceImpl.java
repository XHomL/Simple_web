package com.example.web.Service;

import com.example.web.Bean.Production;
import com.example.web.Mapper.ProductionMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionServiceImpl implements ProductionService {

    @Autowired
    private ProductionMapper productionMapper;

    @Override
    public Boolean addProduction(String productionName,Integer price,String productionPhoto,String means,String type){
        System.out.println(productionName);
        System.out.println(price);
        System.out.println(productionPhoto);
        System.out.println(means);
        System.out.println(type);
        return productionMapper.addProduction(productionName, price, productionPhoto, means, type);
    }

    @Override
    public Boolean deleteProductionByName(String productionName,String type){
        return productionMapper.deleteProductionByName(productionName,type);
    }

    @Override
    //根据传入的productionid判断商品是否存在
    public Boolean checkProduction(Integer productionId){
        return productionMapper.checkProduction(productionId);
    }

    //获取完整的商品清单
    @Override
    public List<Production> getProductionList(){
        return productionMapper.getProductionList();
    }

    //通过商品Id获取商品
    @Override
    public Production getProductionById(Integer productionId){
        return productionMapper.getProductionById(productionId);
    }

    //通过商品Id获取商品价格
    public Integer getPriceByProductionId(Integer productionId){
        return productionMapper.getPriceByProductionId(productionId);
    }

    @Override
    public Production getProductionByName(String productionName){
        return productionMapper.getProductionByName(productionName);
    }

    @Override
    public List<Production> getProductionByType(String type){
        return productionMapper.getProductionByType(type);
    }

    @Override
    public Boolean updateProduction(String productionName, Integer price, String means){
        return productionMapper.updateProduction(productionName, price, means);
    }

    @Override
    public Boolean updateProductionPhoto(String productionName, String productionPhoto){
        return productionMapper.updateProductionPhoto(productionName,productionPhoto);
    }
}
