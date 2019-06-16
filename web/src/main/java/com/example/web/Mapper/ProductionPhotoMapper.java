package com.example.web.Mapper;

import com.example.web.Bean.ProductionPhoto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductionPhotoMapper {
    @Insert("INSERT INTO productionPhoto (productionId, productionPhoto, productionName) VALUES (#{productionId}, " +
            "#{productionPhoto}, #{productionName})")
    Boolean uploadProductionPhoto(@Param("productionId")Integer productionId,@Param("productionPhoto")String productionPhoto,
                                  @Param("productionName")String productionName);

    @Select("SELECT productionPhoto FROM productionPhoto WHERE productionName = #{productionName}")
    List<String> getProductionPhoto(@Param("productionName")String productionName);
}
