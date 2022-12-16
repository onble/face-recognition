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

    @PostMapping("/login_staff")
    public R loginStaff(String account, String password) {
        try {
            Staff staff = staffService.loginStaff(account, password);
            return R.ok().setData("staff", staff);
        } catch (Exception e) {
            return R.error().setData("msg", e.getMessage());
        }
    }
}
