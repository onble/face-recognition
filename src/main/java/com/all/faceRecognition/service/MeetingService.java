package com.all.faceRecognition.service;

import com.all.faceRecognition.bean.MeetingInf;
import com.all.faceRecognition.common.R;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * 会议的业务层
 */
@Service
public interface MeetingService {
    // 根据页码获取会议列表
    R getMeetingInfByPage(int page, int items) throws Exception;

    // 根据id删除
    R deleteById(int meetingId) throws Exception;

    // 根据id批量删除数据
    void batchDelete(int[] ids) throws Exception;

    // 插入数据
    void insert(String title, String address, String content, int staffId , Data startTime, Data stopTime, int status) throws Exception;

    // 根据id获取一条数据
    MeetingInf selectById(int meetingId) throws Exception;

    // 修改数据
    void change(int meetingId,String title, String address, String content, int staffId , Data startTime, Data stopTime, int status) throws Exception;

    int  getNum(int meetingId) throws Exception;


}
