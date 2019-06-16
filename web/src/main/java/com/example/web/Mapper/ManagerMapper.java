package com.example.web.Mapper;

import com.example.web.Bean.Manager;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


//相关文件com.example.web.Bean.Manager
//此处为了管理员账号的安全，只给出了对于管理员账号的登陆权限，对于删除账户、修改密码等操作均需要系统管理员直接登陆数据库进行操作

public interface ManagerMapper {
    //如果有多个参数，每一个参数均需要使用@Param声明，否则会报错
    @Select("SELECT * FROM manager WHERE managerName = #{managerName} AND password = #{password}")
    Manager managerLogin(@Param("managerName") String managerName,@Param("password") String password);

    //如果有多个参数，每一个参数均需要使用@Param声明，否则会报错
    @Insert("INSERT INTO manager (managerId, managerName, password) VALUES (DEFAULT, #{managerName}, #{password})")
    Boolean addManager(@Param("managerName") String managerName, @Param("password") String password);

    //用于管理员注册的时候检测管理员名是否存在
    @Select("SELECT * FROM manager WHERE managerName = #{managerName}")
    Boolean checkManagerName(String managerName);

    @Select("SELECT * FROM manager")
    List<Manager> getManagerList();
}
