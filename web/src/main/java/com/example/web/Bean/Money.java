package com.example.web.Bean;

import java.util.Date;

public class Money {


    private int id;
    private int cash;
    private int bud;
    private int corolla;
    private String orderTime;
    private String orderStatus;
    private int userId;
    private String username;
    private String phoneNum;

    public int getId() {
        return id;
    }

    public int getCash() {
        return cash;
    }

    public int getBud() {
        return bud;
    }

    public int getCorolla() {
        return corolla;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public void setBud(int bud) {
        this.bud = bud;
    }

    public void setCorolla(int corolla) {
        this.corolla = corolla;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}
