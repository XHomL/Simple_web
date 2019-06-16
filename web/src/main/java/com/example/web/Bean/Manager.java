package com.example.web.Bean;


public class Manager {


    private int managerId;
    private String  profile;
    private String managerName;
    private String  password;

    public int getManagerId(){        return managerId;    }
    public String getProfile(){        return profile;    }

    public String getManagerName() {        return managerName;    }

    public String getPassword() {        return password;    }

    public void setManagerId(int managerId) {        this.managerId = managerId;    }

    public void setManagerName(String managerName) {        this.managerName = managerName;    }

    public void setProfile(String profile) {        this.profile = profile;    }

    public void setPassword(String password) {        this.password = password;    }

    public Manager(){

    }
    public Manager(String managerName, String password){
        this.managerName = managerName;
        this.password=password;
    }


}

