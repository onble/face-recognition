package com.all.faceRecognition.service;

import com.all.faceRecognition.bean.Department;
import com.all.faceRecognition.common.R;
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
    void insert(String name, String homePage) throws Exception;

    // 根据id获取一条数据
    Department selectById(int id) throws Exception;

    // 修改数据
    void change(int id, String name, String homePage) throws Exception;

    int getNum(int id) throws Exception;

//    // 职员修改个人信息
//    void changeByStaff(int staffId, String name, int age, String phone, boolean gender) throws Exception;

//    // 根据职员id修改头像文件
//    void changeHeaderFile(int staffId, String filename) throws Exception;

    // 根据名字获取部门信息
    R getDepartmentListByPageByName(int page, int items, String name) throws Exception;
}
