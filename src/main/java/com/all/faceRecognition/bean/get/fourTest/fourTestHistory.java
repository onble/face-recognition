package com.all.faceRecognition.bean.get.fourTest;

import com.all.faceRecognition.bean.FourTestActions;
import com.all.faceRecognition.bean.TestBaseInfo;
import com.all.faceRecognition.bean.get.TestBaseInfoUserGet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class fourTestHistory {
    Integer userTestId;
    LocalDateTime doneTime;
    int groupKind;
    int timeSpendSeconds;
    Integer userId;
    FourTestActions fourTestActions;
    Integer fourTestInfoId;
    TestBaseInfoUserGet test1_baseInfo;
    TestBaseInfoUserGet test2_baseInfo;
    TestBaseInfoUserGet test3_baseInfo;
    TestBaseInfoUserGet test4_baseInfo;
}
