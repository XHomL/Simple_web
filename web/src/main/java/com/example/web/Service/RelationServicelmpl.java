package com.example.web.Service;

import com.example.web.Mapper.RelationMapper;
import com.example.web.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationServicelmpl implements RelationService{
    @Autowired
    private RelationMapper relationMapper;

    @Override
    public Boolean checkPermission(Integer permission) {
        if(permission == -1 || permission == 0 || permission == 1)
            return true;
        return false;
    }

    @Override
    public int getRelation(Integer userId, Integer targetId) {
        return relationMapper.getRelation(userId,targetId);
    }

    @Override
    public Boolean addRelation(Integer userId, Integer targetId,Integer permission) {
        return relationMapper.addRelation(userId,targetId,permission);
    }

    @Override
    public Boolean updateRelation(Integer userId, Integer targetId,Integer permission) {
        return relationMapper.updateRelation(userId,targetId,permission);
    }

    @Override
    public Boolean deleteRelation(Integer userId, Integer targetId) {
        return relationMapper.deleteRelation(userId,targetId);
    }
}
