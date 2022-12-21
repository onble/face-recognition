package com.all.officeSystem.service.Impl;

import com.all.officeSystem.bean.Department;
import com.all.officeSystem.common.R;
import com.all.officeSystem.mapper.DepartmentMapper;
import com.all.officeSystem.service.DepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    // TODO: GJY 参考MeetingService
    @Autowired
    private DepartmentMapper departmentMapper;



    @Override
    public R getDepartmentInfByPage(int page, int items) throws Exception {
        // 初始化分页信息
        PageHelper.startPage(page, items);
        // 查询全部数据
        List<Department> department = departmentMapper.selectAll();
        // 借助分页助手获取分页信息
        PageInfo<Department> pageInfo = new PageInfo<>(department);
        return R.ok().setData("department_inf", pageInfo.getList()).setData("pages", pageInfo.getPages());
//        return R.ok().setData("pageInfo", pageInfo);

    }
    //删除
    @Override
    public R deleteById(int id) {
        try {
            departmentMapper.deleteById(id);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    //批量删除
    @Override
    public void batchDelete(int[] ids) throws Exception {
        for (int id : ids) {
            departmentMapper.deleteById(id);
        }
    }

    //插入
    @Override
    public void insert(String name, String homePage) throws Exception {
        departmentMapper.insert(name,homePage);
    }

    @Override
    public Department selectById(int id) throws Exception {

        return departmentMapper.selectById(id);

    }

    @Override
    public void change( String name, String homePage) throws Exception {
        departmentMapper.change(name,homePage);
    }

    @Override
    public int getNum(int staffId) throws Exception {
        return departmentMapper.getNum(staffId);
    }
}