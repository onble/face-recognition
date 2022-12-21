package com.all.officeSystem.service.Impl;

import com.all.officeSystem.bean.Admin;
import com.all.officeSystem.mapper.AdminMapper;
import com.all.officeSystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理员业务层的实现
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;


    //管理员登录
    @Override
    public Admin login(String username, String password) throws Exception {

        Admin admin = adminMapper.selectByUsername(username);
        if (admin == null) {
            //用户名不存在
            throw new Exception("用户名不存在！");
        }
        if (!password.equals(admin.getPassword())) {
            //密码错误
            throw new Exception("密码错误！");
        }

        return admin;
    }


}
