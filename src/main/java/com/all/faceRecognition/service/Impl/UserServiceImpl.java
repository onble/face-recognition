package com.all.faceRecognition.service.Impl;

import com.all.faceRecognition.bean.User;
import com.all.faceRecognition.common.R;
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

    @Override
    public R register(String account, String password, String repassword) throws Exception {
        boolean accountCheck = false;
        boolean repasswordCheck = false;
        boolean createStaffCheck = false;
        // 首先检查账号是否存在
        User user = userMapper.selectByUsername(account);
        if (user == null) {
            accountCheck = true;
        }
        if (!"".equals((password.trim()))) {
            if (repassword.equals(password)) {
                repasswordCheck = true;
            }
        }
        // 下面去注册
        userMapper.insertNewUser(account, password);
        createStaffCheck = true;
        return R.ok().setData("account_check", accountCheck).setData("repassword_check", repasswordCheck).setData("create_account", createStaffCheck);
    }
}
