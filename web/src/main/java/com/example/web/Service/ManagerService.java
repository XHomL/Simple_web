package com.example.web.Service;


//相关文件com.example.web.Bean.Manager
//此处为了管理员账号的安全，只给出了对于管理员账号的登陆权限，对于删除账户、修改密码等操作均需要系统管理员直接登陆数据库进行操作

import com.example.web.Bean.Manager;

import java.util.List;

public interface ManagerService {
    Manager managerLogin(String managerName, String password);

    Boolean addManager(String managerName,String password);

    Boolean checkManagerName(String managerName);

    List<Manager> getManagerList();
}
