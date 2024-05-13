package com.all.faceRecognition.service;

import com.all.faceRecognition.bean.User;

/*
 * 用户的业务层
 * */
public interface UserService {
    User login(String username, String password) throws Exception;
}
