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
}
