package com.all.faceRecognition.controller;


import com.all.faceRecognition.bean.Admin;
import com.all.faceRecognition.common.R;
import com.all.faceRecognition.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员的控制器
 */
@RestController
public class AdminController {


    @Autowired
    private AdminService adminService;


    //登录
    @PostMapping("/login_admin")
    public R login(String username,String password)  {

        try {
            Admin admin = adminService.login(username,password);
            return R.ok().setData("admin",admin);
        } catch (Exception e) {
            return R.error().setData("msg",e.getMessage());
        }

    }


}
