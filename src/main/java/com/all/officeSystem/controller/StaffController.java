package com.all.officeSystem.controller;

import com.all.officeSystem.bean.Staff;
import com.all.officeSystem.common.R;
import com.all.officeSystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 职员的控制器
 */
@RestController
public class StaffController {

    @Autowired
    private StaffService staffService;

    /**
     * \
     * 职员登录控制
     *
     * @param account
     * @param password
     * @return
     */
    @PostMapping("/login_staff")
    public R loginStaff(String account, String password) {
        try {
            Staff staff = staffService.loginStaff(account, password);
            return R.ok().setData("staff", staff);
        } catch (Exception e) {
            return R.error().setData("msg", e.getMessage());
        }
    }

    @PostMapping("/register_staff")
    public R registerNewStaff(String account, String password, String repassword) {
        try {
            R r = staffService.register(account, password, repassword);
            return r;
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
