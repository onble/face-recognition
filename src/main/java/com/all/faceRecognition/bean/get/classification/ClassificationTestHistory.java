package com.all.faceRecognition.bean.get.classification;

import com.all.faceRecognition.bean.FourTestActions;
import com.all.faceRecognition.bean.get.TestBaseInfoUserGet;
import com.all.faceRecognition.bean.save.ClassificationTestAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassificationTestHistory {
    Integer userTestId;
    LocalDateTime doneTime;
    int groupKind;
    int timeSpendSeconds;
    Integer userId;
    ClassificationTestAction classificationTestAction;
    Integer classificationInfoId;
    TestBaseInfoUserGet test1_baseInfo;
    TestBaseInfoUserGet test2_baseInfo;
    TestBaseInfoUserGet test3_baseInfo;
    TestBaseInfoUserGet test4_baseInfo;
    TestBaseInfoUserGet test5_baseInfo;
    TestBaseInfoUserGet test6_baseInfo;
    TestBaseInfoUserGet test7_baseInfo;
    TestBaseInfoUserGet test8_baseInfo;
    TestBaseInfoUserGet test9_baseInfo;
    TestBaseInfoUserGet test10_baseInfo;
    TestBaseInfoUserGet testA_baseInfo;
    TestBaseInfoUserGet testB_baseInfo;
}
