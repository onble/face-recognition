package com.all.faceRecognition.service;

import com.all.faceRecognition.bean.Todo;
import com.all.faceRecognition.common.R;
import org.springframework.stereotype.Service;

/**
 * 待办事项的业务层
 */
@Service
public interface TodoService {
    // 根据页码和职员id获取待办事项列表
    R getTodoInfByPageByStaffId(int page, int items, int staffId) throws Exception;

    // 根据待办事项id删除
    R deleteById(int id) throws Exception;

    // 根据id批量删除数据
    void batchDelete(int[] ids) throws Exception;

    // 插入数据
    void insert(int staffId, String title, String content, boolean status) throws Exception;

    // 根据id获取一条待办事项
    Todo selectById(int id) throws Exception;

    // 修改数据
    void change(int staffId, String title, String content, boolean status, int todoId) throws Exception;

    int  getNum(int staffId) throws Exception;
}
