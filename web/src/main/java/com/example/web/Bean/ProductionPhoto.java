package com.example.web.Bean;

public class ProductionPhoto {
    private Integer productionId;
    private String productionPhoto;
    private String productionName;

    public Integer getProductionId() {
        return productionId;
    }

    public String getProductionName() {
        return productionName;
    }

    public String getProductionPhoto() {
        return productionPhoto;
    }

    public void setProductionId(Integer productionId) {
        this.productionId = productionId;
    }

    public void setProductionName(String productionName) {
        this.productionName = productionName;
    }

    public void setProductionPhoto(String productionPhoto) {
        this.productionPhoto = productionPhoto;
    }
}
