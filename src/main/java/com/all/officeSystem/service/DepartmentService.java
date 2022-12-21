package com.all.officeSystem.service;

import com.all.officeSystem.bean.Department;
import com.all.officeSystem.common.R;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {
    // TODO: GJY 参考MeetingService
    // 分页显示
    R getDepartmentInfByPage(int page, int items) throws Exception;

    // 根据id删除
    R deleteById(int id) throws Exception;

    // 根据id批量删除数据
    void batchDelete(int[] ids) throws Exception;

    // 插入数据
    void insert( String name, String homePage) throws Exception;

    // 根据id获取一条数据
    Department selectById(int id) throws Exception;

    // 修改数据
    void change(String name, String homePage) throws Exception;

    int  getNum(int id) throws Exception;
}
