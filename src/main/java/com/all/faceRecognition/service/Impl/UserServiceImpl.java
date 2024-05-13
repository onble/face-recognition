package com.all.faceRecognition.service.Impl;

import com.all.faceRecognition.bean.User;
import com.all.faceRecognition.mapper.UserMapper;
import com.all.faceRecognition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * 用户业务层的实现
 * */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) throws Exception {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            // 用户不存在
            throw new Exception("用户名不存在!");
        }
        if (!password.equals(user.getPassword())) {
            // 密码错误
            throw new Exception("密码错误");
        }
        return user;
    }
}
