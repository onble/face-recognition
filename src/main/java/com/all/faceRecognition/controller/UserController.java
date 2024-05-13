package com.all.faceRecognition.controller;

import com.all.faceRecognition.bean.User;
import com.all.faceRecognition.common.R;
import com.all.faceRecognition.common.Result;
import com.all.faceRecognition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 用户的控制器
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // 登录
    @PostMapping("/login_user")
    public R login(String username, String password) {
        try {
            User user = userService.login(username, password);
            R result = R.ok().setData("user", user);
            return result;
        } catch (Exception e) {
            R result = R.error().setMessage(e.getMessage());
            return result;
        }
    }


}
