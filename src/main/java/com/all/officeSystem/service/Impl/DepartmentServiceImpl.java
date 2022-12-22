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
    @Autowired
    private DepartmentMapper departmentMapper;

    //分页
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
        departmentMapper.insert(name, homePage);
    }

    @Override
    public Department selectById(int id) throws Exception {

        return departmentMapper.selectById(id);

    }

    //修改
    @Override
    public void change(int id, String name, String homePage) throws Exception {
        departmentMapper.change(id, name, homePage);
    }

    @Override
    public int getNum(int staffId) throws Exception {
        return departmentMapper.getNum(staffId);
    }

    //按照姓名查询
    public R getDepartmentListByPageByName(int page, int items, String name) throws Exception {
        // 新建部门列表
        List<Department> department =  departmentMapper.selectByName(name);
//        PageInfo<Department> departmentListByPageByName = getDepartmentListByPageByName(page, items, name);
//        return R.ok().setData("department_list", department).setData("pages", departmentListByPageByName.getPages());
        PageInfo<Department> pageInfo = new PageInfo<>(department);
        return R.ok().setData("department_list", pageInfo.getList()).setData("pages", pageInfo.getPages());
    }
    
}