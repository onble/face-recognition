package com.all.faceRecognition.bean.get.findTest;

import com.all.faceRecognition.bean.get.TestBaseInfoUserGet;
import com.all.faceRecognition.bean.save.ClassificationTestAction;
import com.all.faceRecognition.bean.save.FindTestAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindTestHistory {
    Integer userTestId;
    LocalDateTime doneTime;
    int groupKind;
    int timeSpendSeconds;
    Integer userId;
    FindTestAction findTestAction;
    Integer findInfoId;
    TestBaseInfoUserGet test1_baseInfo;
    TestBaseInfoUserGet test2_baseInfo;
    TestBaseInfoUserGet test3_baseInfo;
    TestBaseInfoUserGet test4_baseInfo;
    TestBaseInfoUserGet test5_baseInfo;
    TestBaseInfoUserGet test6_baseInfo;
    TestBaseInfoUserGet test7_baseInfo;
    TestBaseInfoUserGet test8_baseInfo;
    TestBaseInfoUserGet target_baseInfo;
}
