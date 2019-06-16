package com.example.web.Controller;


import com.example.web.Bean.Production;
import com.example.web.Service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ProductionController {
    @Autowired
    private ProductionService productionService;

    @RequestMapping("/getProductionList")
    @ResponseBody
    public List<Production> getProductionList(){
        return productionService.getProductionList();
    }

    @RequestMapping("/addProduction")
    @ResponseBody
    Boolean addProduction(@RequestBody Map<String,Object> map){
        System.out.println(map);
        String productionName=map.get("productionName").toString();
        Integer price=Integer.parseInt(map.get("price").toString());
        String productionPhoto=map.get("productionPhoto").toString();
        String means=map.get("means").toString();
        String type=map.get("type").toString();
        return productionService.addProduction(productionName, price, productionPhoto, means, type);
    }

    //后端调用使用
    public Boolean checkProduction(Integer productionId){
        return productionService.checkProduction(productionId);
    }

    //通过商品Id获取商品价格
    public Integer getPriceByProductionId(Integer productionId){
        return productionService.getPriceByProductionId(productionId);
    }

    public Production getProductionById(Integer productionId){
        return productionService.getProductionById(productionId);
    }

    @RequestMapping("/getVirtualFlowers")
    @ResponseBody
    List<Production> getVirtualFlowers(){
        String type="虚拟鲜花";
        return productionService.getProductionByType(type);
    }

    @RequestMapping("/getVirtual")
    @ResponseBody
    List<Production> getVirtual(){
        String type="虚拟鲜花";
        return productionService.getProductionByType(type);
    }

    @RequestMapping("/getRealFlowers")
    @ResponseBody
    List<Production> getRealFlowers(){
        String type="实物鲜花";
        return productionService.getProductionByType(type);
    }

    @RequestMapping("/getRealGifts")
    @ResponseBody
    List<Production> getRealGifts(){
        String type="实际礼品";
        return productionService.getProductionByType(type);
    }

    @RequestMapping("/getReal")
    @ResponseBody
    List<Production> getReal(){
        String type="实际礼品";
        return productionService.getProductionByType(type);
    }

    @RequestMapping("/deleteProduction")
    @ResponseBody
    Boolean deleteProductionByName(@RequestBody Map<String,Object>map){
        String productionName=map.get("productionName").toString();
        String type=map.get("type").toString();
        System.out.println(map);
        return productionService.deleteProductionByName(productionName,type);
    }
//    @RequestMapping(value = "/uploadProductionPhoto",method = RequestMethod.POST)
//    @ResponseBody
//    public Boolean uploadProductionPhoto()

    @RequestMapping("/updateProduction")
    @ResponseBody
    Boolean updateProduction(@RequestBody Map<String,Object>map){
        System.out.println(map);
        String productionName=map.get("productionName").toString();
        Integer price=Integer.parseInt(map.get("price").toString());
        String means=map.get("means").toString();
        return productionService.updateProduction(productionName,price,means);

    }

    @RequestMapping("/updateProductionPhoto")
    @ResponseBody
    Boolean updateProductionPhoto(@RequestBody Map<String,Object>map){
        System.out.println(map);
        String productionName=map.get("productionName").toString();
        String productionPhoto=map.get("productionPhoto").toString();
        return productionService.updateProductionPhoto(productionName,productionPhoto);
    }
}
