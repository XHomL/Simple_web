package com.example.web.Service;

import com.example.web.Bean.Production;
import com.example.web.Bean.ProductionPhoto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductionPhotoService {

    Boolean uploadProductionPhoto(Integer productionId, String productionPhoto, String productionName);

    List<String> getProductionPhoto(String productionName);
}
