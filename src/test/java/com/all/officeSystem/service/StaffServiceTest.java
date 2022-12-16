package com.all.officeSystem.service;

import com.all.officeSystem.bean.Staff;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StaffServiceTest {

    @Autowired
    private StaffService staffService;

    /**
     * 这个测试用于检查能否根据用户名密码获取职员对象
     */
    @Test
    void loginStaff() {
        try {
            Staff staff = staffService.loginStaff("xiaoming", "123456");
            System.out.println(staff);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}