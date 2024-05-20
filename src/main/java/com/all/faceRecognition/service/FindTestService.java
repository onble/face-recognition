package com.all.faceRecognition.service;

import com.all.faceRecognition.bean.save.SaveFindTestInfo;

import java.util.HashMap;

public interface FindTestService {
    // 获取数据
    HashMap<String, Object> get_test() throws Exception;

    // 存储做题记录
    void saveRecords(SaveFindTestInfo saveFindTestInfo, int user_id) throws Exception;
}
