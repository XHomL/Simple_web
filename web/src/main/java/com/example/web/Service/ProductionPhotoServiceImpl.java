package com.example.web.Service;

import com.example.web.Bean.Production;
import com.example.web.Bean.ProductionPhoto;
import com.example.web.Mapper.ProductionPhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionPhotoServiceImpl implements ProductionPhotoService {
    @Autowired
    ProductionPhotoMapper productionPhotoMapper;

    @Override
    public Boolean uploadProductionPhoto(Integer productionId, String productionPhoto, String productionName)
    {
        return productionPhotoMapper.uploadProductionPhoto(productionId, productionPhoto, productionName);
    }

    @Override
    public List<String> getProductionPhoto(String productionName){
        return productionPhotoMapper.getProductionPhoto(productionName);
    }
}
