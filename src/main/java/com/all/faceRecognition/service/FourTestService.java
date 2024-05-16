package com.all.faceRecognition.service;

import com.all.faceRecognition.bean.FourTestRecord;
import com.all.faceRecognition.common.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 四选一测试的业务层
 * */
public interface FourTestService {
    // 获取数据
    List<HashMap<String, Object>> get_test(int num) throws Exception;

    HashMap<String, Object> getData() throws Exception;

    // 存储做题记录
    void saveRecords(FourTestRecord testRequest) throws Exception;
}
