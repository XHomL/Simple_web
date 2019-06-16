package com.example.web.Bean;


public class Production {
    private int productionId;
    private String productionName;
    private int price;
    private String productionPhoto;
    private String means;
    private String type;

    public int getProductionId() {
        return productionId;
    }

    public String getProductionName() {
        return productionName;
    }

    public int getPrice() {
        return price;
    }

    public String getProductionPhoto() {
        return productionPhoto;
    }

    public String getMeans() {
        return means;
    }

    public String getType() {
        return type;
    }

    public void setProductionId(int productionId) {
        this.productionId = productionId;
    }

    public void setProductionName(String productionName) {
        this.productionName = productionName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setProductionPhoto(String productionPhoto) {
        this.productionPhoto = productionPhoto;
    }

    public void setMeans(String means) {
        this.means = means;
    }

    public void setType(String type) {
        this.type = type;
    }
}
