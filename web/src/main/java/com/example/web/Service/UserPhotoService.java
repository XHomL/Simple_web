package com.example.web.Service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserPhotoService {
    Boolean uploadUserPhoto(Integer userId, String phoneNum, String userPhoto);
    List<String> getUserPhoto(String phoneNum);
}
