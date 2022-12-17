package com.all.officeSystem.service;

import com.all.officeSystem.bean.Staff;
import com.all.officeSystem.common.R;
import org.springframework.stereotype.Service;

/**
 * 职员的业务层
 */
@Service
public interface StaffService {
    // 职员登录
    Staff loginStaff(String account, String password) throws Exception;
    // 职员注册
    R register(String account, String password, String repassword) throws Exception;
    // 根据职员id获取职员信息
}
