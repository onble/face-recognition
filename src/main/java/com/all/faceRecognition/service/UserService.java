package com.all.faceRecognition.service;

import com.all.faceRecognition.bean.Staff;
import com.all.faceRecognition.bean.User;
import com.all.faceRecognition.common.R;

/*
 * 用户的业务层
 * */
public interface UserService {
    // 用户登录
    User login(String username, String password) throws Exception;

    // 用户注册
    R register(String account, String password, String repassword) throws Exception;

    // 根据ID获取信息
    User selectById(int id) throws Exception;
}
