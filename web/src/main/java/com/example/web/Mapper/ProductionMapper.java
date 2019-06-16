package com.example.web.Mapper;

import com.example.web.Bean.Production;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProductionMapper {
    //如果有多个参数，每一个参数均需要使用@Param声明，否则会报错
    @Insert("INSERT INTO production (productionId, productionName, price, productionPhoto, means, type) VALUES (DEFAULT, #{productionName}, #{price}, #{productionPhoto}, #{means}, #{type})")
    Boolean addProduction(@Param("productionName") String productionName, @Param("price") Integer price, @Param("productionPhoto") String productionPhoto, @Param("means") String means,@Param("type") String type);

    @Delete("DELETE FROM production WHERE productionName = #{productionName} AND type = #{type}")
    Boolean deleteProductionByName(String productionName,@Param("type")String type);

    //根据传入的productionId判断商品是否存在
    @Select("SELECT * FROM production WHERE productionId = #{productionId}")
    Boolean checkProduction(Integer productionId);

    //获取完整的商品清单
    @Select("SELECT * FROM production")
    List<Production> getProductionList();

    //通过商品Id获取商品
    @Select("SELECT * FROM production WHERE productionId = #{productionId}")
    Production getProductionById(Integer productionId);

    //通过商品Id获取商品价格
    @Select("SELECT price FROM production WHERE productionId = #{productionId}")
    Integer getPriceByProductionId(Integer productionId);

    @Select("SELECT * FROM production WHERE productionName = #{productionName}")
    Production getProductionByName(@Param("productionName") String productionName);

    @Select("SELECT * FROM production WHERE type = #{type}")
    List<Production> getProductionByType(String type);

    @Update("UPDATE production SET  price = #{price}, " +
            " means = #{means} WHERE productionName = #{productionName}")
    Boolean updateProduction(@Param("productionName") String productionName,@Param("price") Integer price,
                             @Param("means") String means);

    @Update("UPDATE production SET productionPhoto = #{productionPhoto} WHERE productionName = #{productionName}")
    Boolean updateProductionPhoto(@Param("productionName") String productionName,
                                  @Param("productionPhoto") String productionPhoto);
}
