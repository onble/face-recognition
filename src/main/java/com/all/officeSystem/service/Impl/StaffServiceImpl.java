package com.all.officeSystem.service.Impl;

import com.all.officeSystem.bean.Staff;
import com.all.officeSystem.common.R;
import com.all.officeSystem.mapper.StaffInfMapper;
import com.all.officeSystem.mapper.StaffMapper;
import com.all.officeSystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private StaffInfMapper staffInfMapper;

    /**
     * 职员登录
     *
     * @param account  账号
     * @param password 密码
     * @return 职员对象
     * @throws Exception
     */
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

        // 检查账号状态
        if (staff.getStatus() != 0) {
            // 不可登录
            throw new Exception("账号不可登录，请联系管理员");
        }
        return staff;
    }

    /**
     * 职员注册
     *
     * @param account
     * @param password
     * @param repassword
     * @return
     * @throws Exception
     */
    @Override
    public R register(String account, String password, String repassword) throws Exception {

        boolean accountCheck = false;
        boolean repasswordCheck = false;
        boolean createStaffCheck = false;

        // 首先检查账号是否存在
        Staff staff = staffMapper.selectByAccount(account);
        if (staff == null) {
            // 账号已存在，不可再注册
            accountCheck = true;
        }
        // 检查密码和重复密码是否一致
        // 首先检查密码是否为空
        if (!"".equals(password.trim())) {
            repasswordCheck = true;
        }
        if (repassword.equals(password)) {
            repasswordCheck = true;
        }
        // 下面去注册
        staffMapper.insertNewStaff(account, password);
        // 下面还要生成职员的信息
        Staff staffNew = staffMapper.selectByAccount(account);
        staffInfMapper.insertNewStaffInf(staffNew.getId());
        createStaffCheck = true;
        return R.ok().setData("account_check", accountCheck).setData("repassword_check", repasswordCheck).setData("create_account", createStaffCheck);
    }


}
