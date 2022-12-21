package com.all.officeSystem.service.Impl;


import com.all.officeSystem.bean.StaffInf;
import com.all.officeSystem.common.R;
import com.all.officeSystem.mapper.DepartmentMapper;
import com.all.officeSystem.mapper.StaffInfMapper;
import com.all.officeSystem.service.StaffInfService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.MediaSize;
import java.util.List;

/**
 * 员工管理业务层实现
 */
@Service
public class StaffInfServiceImpl implements StaffInfService {

    @Autowired
    private StaffInfMapper staffInfMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public R getStaffInfByPage(int page, int items) throws Exception {
        // 初始化分页信息
        PageHelper.startPage(page, items);
        // 查询全部数据
        List<StaffInf> staffInfs = staffInfMapper.selectAll();
        // 借助分页助手获取分页信息
        PageInfo<StaffInf> pageInfo = new PageInfo<>(staffInfs);
        // 根据部门id查询部门名称
//        departmentMapper.selectById()
        return R.ok().setData("staffInf_inf", pageInfo.getList()).setData("pages", pageInfo.getPages());
    }

    @Override
    public void deleteById(int id) throws Exception {
        staffInfMapper.deleteById(id);
    }

    @Override
    public void batchDelete(int[] ids) throws Exception {
        for (int id : ids) {
            staffInfMapper.deleteById(id);
        }
    }

    @Override
    public void insert(StaffInf th) throws Exception {
        staffInfMapper.insert(th);
    }

    @Override
    public StaffInf selectById(int id) throws Exception {
        return staffInfMapper.selectById(id);
    }

    @Override
    public void changeByStaff(int staffId, String name, int age, String phone, boolean gender) throws Exception {
        staffInfMapper.changeByStaff(staffId, name, age, phone, gender);
    }

    @Override
    public void changeHeaderFile(int staffId, String filename) throws Exception {
        staffInfMapper.changeHeaderFile(staffId, filename);
    }

}
