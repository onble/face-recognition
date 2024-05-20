package com.all.faceRecognition.service;

import com.all.faceRecognition.bean.save.SaveClassificationTestInfo;

import java.util.HashMap;
import java.util.List;

public interface ClassificationTestService {
    // 获取数据
    HashMap<String, Object> get_test() throws Exception;
    // 存储做题记录
    void saveRecords(SaveClassificationTestInfo saveClassificationTestInfo, int user_id) throws Exception;
}
