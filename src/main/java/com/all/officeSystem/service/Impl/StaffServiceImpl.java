package com.all.officeSystem.service.Impl;

import com.all.officeSystem.bean.*;
import com.all.officeSystem.common.R;
import com.all.officeSystem.mapper.*;
import com.all.officeSystem.service.StaffService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private StaffInfMapper staffInfMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private OnlineResultMapper onlineResultMapper;

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

    @Override
    public PageInfo<StaffInf> getStaffInfListByPage(int page, int items) throws Exception {
        // 初始化分页信息
        PageHelper.startPage(page, items);
        // 查询全部数据
        List<StaffInf> staffInfs = staffInfMapper.selectAll();
        // 借助分页助手获取分页信息
        PageInfo<StaffInf> pageInfo = new PageInfo<>(staffInfs);

        return pageInfo;
    }

    @Override
    public PageInfo<StaffInf> getStaffInfListByPageByName(int page, int items, String name) throws Exception {
        // 初始化分页信息
        PageHelper.startPage(page, items);
        // 查询全部数据
        List<StaffInf> staffInfs = staffInfMapper.selectByName(name);
        // 借助分页助手获取分页信息
        PageInfo<StaffInf> pageInfo = new PageInfo<>(staffInfs);

        return pageInfo;
    }


    @Override
    public R getAddressListByPage(int page, int items) throws Exception {
        // 新建通讯录列表
        List<AddressInf> addressInfs = new ArrayList<AddressInf>();
        // 获取分页的职员信息
        PageInfo<StaffInf> staffInfListByPage = getStaffInfListByPage(page, items);
        List<StaffInf> staffs = staffInfListByPage.getList();
        for (StaffInf staff : staffs) {
            // 新建一个地址信息对象
            AddressInf addressInf = new AddressInf();
            addressInf.setId(staff.getStaffId());
            addressInf.setName(staff.getName());
            addressInf.setPhone(staff.getPhone());
            addressInf.setGender(staff.isGender());
            // 去查询部门名称
            if (!String.valueOf(staff.getDepartmentId()).equals("") && staff.getDepartmentId() != 0) {
                Department department = departmentMapper.selectById(staff.getDepartmentId());
                addressInf.setDepartment(department.getName());
            }
            // 去查询职务名称
            if (!String.valueOf(staff.getPositionId()).equals("") && staff.getPositionId() != 0) {
                Post post = postMapper.selectById(staff.getPositionId());
                addressInf.setPost(post.getName());
            }
            // 将职务信息添加到列表中
            addressInfs.add(addressInf);
        }
        return R.ok().setData("phone_list", addressInfs).setData("pages", staffInfListByPage.getPages());
    }

    @Override
    public R getAddressListByPageByName(int page, int items, String name) throws Exception {
        // 新建通讯录列表
        List<AddressInf> addressInfs = new ArrayList<AddressInf>();
        // 获取分页的职员信息
        PageInfo<StaffInf> staffInfListByPageByName = getStaffInfListByPageByName(page, items, name);
        List<StaffInf> staffs = staffInfListByPageByName.getList();
        for (StaffInf staff : staffs) {
            // 新建一个地址信息对象
            AddressInf addressInf = new AddressInf();
            addressInf.setId(staff.getStaffId());
            addressInf.setName(staff.getName());
            addressInf.setPhone(staff.getPhone());
            addressInf.setGender(staff.isGender());
            // 去查询部门名称
            if (!String.valueOf(staff.getDepartmentId()).equals("") && staff.getDepartmentId() != 0) {
                Department department = departmentMapper.selectById(staff.getDepartmentId());
                addressInf.setDepartment(department.getName());
            }
            // 去查询职务名称
            if (!String.valueOf(staff.getPositionId()).equals("") && staff.getPositionId() != 0) {
                Post post = postMapper.selectById(staff.getPositionId());
                addressInf.setPost(post.getName());
            }
            // 将职务信息添加到列表中
            addressInfs.add(addressInf);
        }
        return R.ok().setData("phone_list", addressInfs).setData("pages", staffInfListByPageByName.getPages());
    }

    @Override
    public R getOnlineResultByPage(int page, int items) throws Exception {
        // 初始化分页信息
        PageHelper.startPage(page, items);
        // 查询全部数据
        List<OnlineResult> onlineResults = onlineResultMapper.selectAll();
        // 借助分页助手获取分页信息
        PageInfo<OnlineResult> pageInfo = new PageInfo<>(onlineResults);
        return R.ok().setData("online_inf", pageInfo.getList());
    }

    @Override
    public R getOnlineResultByPageByName(int page, int items, String name) throws Exception {
        // 初始化分页信息
        PageHelper.startPage(page, items);
        // 查询全部数据
        List<OnlineResult> onlineResults = onlineResultMapper.selectByName(name);
        // 借助分页助手获取分页信息
        PageInfo<OnlineResult> pageInfo = new PageInfo<>(onlineResults);
        return R.ok().setData("online_inf", pageInfo.getList());
    }


}
