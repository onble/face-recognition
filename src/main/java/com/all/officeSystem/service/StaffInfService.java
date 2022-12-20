package com.all.officeSystem.service;

import com.all.officeSystem.bean.StaffInf;
import com.all.officeSystem.common.R;
import org.springframework.stereotype.Service;

/**
 * 员工管理业务层
 */
@Service
public interface StaffInfService {

    // 分页查询
    R getStaffInfByPage(int page, int items) throws Exception;

    // 根据id删除数据
    void deleteById(int id) throws Exception;

    // 根据id批量删除数据
    void batchDelete(int[] ids) throws Exception;


    //添加数据
    void insert(StaffInf th) throws Exception;

    StaffInf selectById(int id) throws Exception;

    // 职员修改个人信息
    void changeByStaff(int staffId, String name, int age, String phone, boolean gender) throws Exception;

    // 根据职员id修改头像文件
    void changeHeaderFile(int staffId, String filename) throws Exception;
}
