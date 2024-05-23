package com.all.faceRecognition.service;

import com.all.faceRecognition.bean.UserTest;
import com.all.faceRecognition.common.R;

import java.util.List;

public interface UserTestService {
    // 按页码获取数据
    R getUserTestInfByPage(int page, int items, int user_id) throws Exception;

    R getFourTestInfByPage(int page, int items, int user_id) throws Exception;
}
