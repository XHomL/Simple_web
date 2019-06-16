package com.example.web.Service;

import com.example.web.Mapper.UserPhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserPhotoServiceImpl implements UserPhotoService{
    @Autowired
    UserPhotoMapper userPhotoMapper;

    @Override
    public Boolean uploadUserPhoto(Integer userId, String phoneNum, String userPhoto){
        return userPhotoMapper.uploadUserPhoto(userId, phoneNum, userPhoto);
    }

    @Override
    public List<String> getUserPhoto(String phoneNum){
        return userPhotoMapper.getUserPhoto(phoneNum);
    }

}
