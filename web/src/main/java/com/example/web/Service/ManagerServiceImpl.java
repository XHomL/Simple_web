package com.example.web.Service;

import com.example.web.Bean.Manager;
import com.example.web.Mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    //此处自动装配会有红线，是因为编写的时候程序未在Mapper文件夹中检测到@Mapper,这是因为我写到了启动中，实际启动后不会出错
    @Autowired
    private ManagerMapper managerMapper;

    //管理员登陆
    @Override
    public Manager managerLogin(String managerName, String password){
        Manager manager = managerMapper.managerLogin(managerName, password);
        return manager;
    }

    @Override
    public Boolean addManager(String managerName,String password){
        return managerMapper.addManager(managerName, password);
    }

    @Override
    public Boolean checkManagerName(String managerName){
        return managerMapper.checkManagerName(managerName);
    }

    @Override
    public List<Manager> getManagerList(){return managerMapper.getManagerList();}
}
