package com.example.web.Bean;


public class Prince {


    private int id;
    private int userId;
    private String currentTime;
    private int receive;
    private int send;
    private String phoneNum;
    private String sex;
    private String profile;
    private String username;


    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public int getReceive() {
        return receive;
    }

    public int getSend() {
        return send;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getSex() {
        return sex;
    }

    public String getUsername() {
        return username;
    }

    public String getProfile() {
        return profile;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public void setReceive(int receive) {
        this.receive = receive;
    }

    public void setSend(int send) {
        this.send = send;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
