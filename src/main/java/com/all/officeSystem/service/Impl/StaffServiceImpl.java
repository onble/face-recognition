package com.all.officeSystem.service.Impl;

import com.all.officeSystem.bean.Staff;
import com.all.officeSystem.mapper.StaffMapper;
import com.all.officeSystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;

    @Override
    public Staff loginStaff(String account, String password) throws Exception {

        // 根据职员账号查取职员对象
        Staff staff = staffMapper.selectByAccount(account);
        if (account == null) {
            // 职员账号不存在
            throw new Exception("账号不存在!");
        }

        // 否则查询到后检查密码是否一致
        if (!password.equals(staff.getPassword())) {
            // 密码错误
            throw new Exception("密码错误");
        }
        return staff;
    }
}
