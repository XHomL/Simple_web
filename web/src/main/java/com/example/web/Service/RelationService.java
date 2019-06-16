package com.example.web.Service;


//实现功能
//关系查询
//关系增加
//关系修改
//新用户增加的时候对所有用户关系表更新

import com.example.web.Bean.Relation;

public interface RelationService {

    Boolean checkPermission(Integer permission);

    int getRelation(Integer userId,Integer targetId);
    Boolean addRelation(Integer userId,Integer targetId,Integer permission);
    Boolean updateRelation(Integer userId,Integer targetId,Integer permission);
    Boolean deleteRelation(Integer userId,Integer targetId);

}
