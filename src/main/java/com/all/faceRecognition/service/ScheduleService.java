package com.all.faceRecognition.service;

import com.all.faceRecognition.bean.Schedule;
import com.all.faceRecognition.common.R;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 日程安排的业务层
 */
@Service
public interface ScheduleService {
    // 根据页码和职员id获取待办日程列表
    R getScheduleInfByStaffId(int page, int items, int staffId) throws Exception;

    // 根据日程id删除
    R deleteById(int scheduleId) throws Exception;

    // 根据id批量删除
    void batchDelete(int[] ids) throws Exception;

    // 插入数据
    void insert(int staffId, String title, String content, Date date) throws Exception;

    // 根据id获取一个日程
    Schedule selectById(int id) throws Exception;

    // 修改数据
    void change(int staffId, String title, String content, Date date, int scheduleId) throws Exception;

    // 获取数量
    int getNum(int scheduleId) throws Exception;
}
