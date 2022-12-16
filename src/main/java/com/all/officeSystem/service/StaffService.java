package com.all.officeSystem.service;

import com.all.officeSystem.bean.Staff;
import org.springframework.stereotype.Service;

/**
 * 职员的业务层
 */
@Service
public interface StaffService {
    Staff loginStaff(String account, String password) throws Exception;
}
