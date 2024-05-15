package com.all.faceRecognition.controller;

import com.all.faceRecognition.bean.User;
import com.all.faceRecognition.common.R;
import com.all.faceRecognition.common.Result;
import com.all.faceRecognition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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

    // 注册
    @PostMapping("/user/register")
    public R register(String account, String password, String repassword, String invitation_code) {
        try {
            userService.register(account, password, repassword);
            return R.ok();
        } catch (Exception e) {
            return R.error().setMessage(e.getMessage());
        }
    }

    // 获取用户信息
    @GetMapping("/user/info")
    public R userInfo(@RequestHeader("token") String token) {
        int id = Integer.parseInt(token);
        try {
            User user = userService.selectById(id);
            if (user == null) {
                return R.error().setMessage("用户Token获取用户失败");
            }
            R result = R.ok().setData("user", user);
            return result;
        } catch (Exception e) {
            return R.error().setMessage(e.getMessage());
        }
    }

    // 退出登录
    @PostMapping("/user/logout")
    public R userLogout(@RequestHeader("token") String token) {
        return R.ok();
    }
}
