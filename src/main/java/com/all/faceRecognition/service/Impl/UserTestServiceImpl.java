package com.all.faceRecognition.service.Impl;

import com.all.faceRecognition.bean.ClassificationTestInfo;
import com.all.faceRecognition.bean.FourTestActions;
import com.all.faceRecognition.bean.FourTestInfo;
import com.all.faceRecognition.bean.UserTest;
import com.all.faceRecognition.bean.get.classification.ClassificationTestHistory;
import com.all.faceRecognition.bean.get.findTest.FindTestHistory;
import com.all.faceRecognition.bean.get.findTest.FindTestInfo;
import com.all.faceRecognition.bean.get.fourTest.fourTestHistory;
import com.all.faceRecognition.bean.save.ClassificationTestAction;
import com.all.faceRecognition.bean.save.FindTestAction;
import com.all.faceRecognition.common.R;
import com.all.faceRecognition.mapper.*;
import com.all.faceRecognition.mapper.findTest.FindTestActionMapper;
import com.all.faceRecognition.mapper.findTest.FindTestInfoMapper;
import com.all.faceRecognition.service.UserTestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserTestServiceImpl implements UserTestService {
    @Autowired
    private UserTestMapper userTestMapper;
    @Autowired
    private FourTestActionMapper fourTestActionMapper;
    @Autowired
    private TestBaseInfoMapper testBaseInfoMapper;
    @Autowired
    private FourTestInfoMapper fourTestInfoMapper;
    @Autowired
    private ClassificationTestActionMapper classificationTestActionMapper;
    @Autowired
    private ClassificationTestInfoMapper classificationTestInfoMapper;
    @Autowired
    private FindTestActionMapper findTestActionMapper;
    @Autowired
    private FindTestInfoMapper findTestInfoMapper;

    @Override
    public R getUserTestInfByPage(int page, int items, int user_id) throws Exception {
        // 初始化分页信息
        PageHelper.startPage(page, items);
        // 查询全部数据
        List<UserTest> userTests = userTestMapper.selectByUserId(user_id);
        // 借助分页助手获取分页信息
        PageInfo<UserTest> pageInfo = new PageInfo<>(userTests);
        return R.ok().setData("inf_list", pageInfo.getList()).setData("pages", pageInfo.getPages()).setData("total", pageInfo.getTotal());

    }

    @Override
    public R getFourTestInfByPage(int page, int items, int user_id) throws Exception {
        // 初始化分页信息
        PageHelper.startPage(page, items);
        // 查询全部数据
        List<UserTest> userTests = userTestMapper.selectFourTestByUserId(user_id);
        // 借助分页助手获取分页信息
        PageInfo<UserTest> pageInfo = new PageInfo<>(userTests);
        userTests = pageInfo.getList();
        List<fourTestHistory> inf_list = new ArrayList<>();
        for (UserTest userTest : userTests) {
            fourTestHistory inf = new fourTestHistory();
            // 保存信息
            inf.setDoneTime(userTest.getDoneTime());
            inf.setGroupKind(userTest.getGroupKind());
            inf.setTimeSpendSeconds(userTest.getTimeSpendSeconds());
            inf.setUserId(userTest.getUserId());
            Integer testInfoId = userTest.getTestGroupId();
            inf.setFourTestInfoId(testInfoId);
            // 根据userTest的actionId去获取action
            FourTestActions fourTestActions = fourTestActionMapper.selectById(userTest.getTestActionId());
            // 根据userTest的testGroupId去获取题组的信息
            FourTestInfo fourTestInfo = fourTestInfoMapper.selectById(testInfoId);
            inf.setUserTestId(userTest.getId());
            inf.setFourTestActions(fourTestActions);
            inf.setTest1_baseInfo(testBaseInfoMapper.selectById(fourTestInfo.getTest1Id()));
            inf.setTest2_baseInfo(testBaseInfoMapper.selectById(fourTestInfo.getTest2Id()));
            inf.setTest3_baseInfo(testBaseInfoMapper.selectById(fourTestInfo.getTest3Id()));
            inf.setTest4_baseInfo(testBaseInfoMapper.selectById(fourTestInfo.getTest4Id()));
            inf_list.add(inf);
        }
        return R.ok().setData("inf_list", inf_list).setData("pages", pageInfo.getPages()).setData("total", pageInfo.getTotal());

    }

    @Override
    public R getClassificationTestInfByPage(int page, int items, int user_id) throws Exception {
        // 初始化分页信息
        PageHelper.startPage(page, items);
        // 查询全部数据
        List<UserTest> userTests = userTestMapper.selectClassificationTestByUserId(user_id);
        // 借助分页助手获取分页信息
        PageInfo<UserTest> pageInfo = new PageInfo<>(userTests);
        userTests = pageInfo.getList();
        List<ClassificationTestHistory> inf_list = new ArrayList<>();
        for (UserTest userTest : userTests) {
            ClassificationTestHistory inf = new ClassificationTestHistory();
            // 保存信息
            inf.setDoneTime(userTest.getDoneTime());
            inf.setGroupKind(userTest.getGroupKind());
            inf.setTimeSpendSeconds(userTest.getTimeSpendSeconds());
            inf.setUserId(userTest.getUserId());
            Integer testInfoId = userTest.getTestGroupId();
            inf.setClassificationInfoId(testInfoId);
            // 根据userTest的actionId去获取action
            ClassificationTestAction classificationTestAction = classificationTestActionMapper.selectById(userTest.getTestActionId());
            // 根据userTest的testGroupId去获取题组的信息
            ClassificationTestInfo classificationTestInfo = classificationTestInfoMapper.selectById(testInfoId);
            inf.setUserTestId(userTest.getId());
            inf.setClassificationTestAction(classificationTestAction);
            inf.setTest1_baseInfo(testBaseInfoMapper.selectById(classificationTestInfo.getTest1Id()));
            inf.setTest2_baseInfo(testBaseInfoMapper.selectById(classificationTestInfo.getTest2Id()));
            inf.setTest3_baseInfo(testBaseInfoMapper.selectById(classificationTestInfo.getTest3Id()));
            inf.setTest4_baseInfo(testBaseInfoMapper.selectById(classificationTestInfo.getTest4Id()));
            inf.setTest5_baseInfo(testBaseInfoMapper.selectById(classificationTestInfo.getTest5Id()));
            inf.setTest6_baseInfo(testBaseInfoMapper.selectById(classificationTestInfo.getTest6Id()));
            inf.setTest7_baseInfo(testBaseInfoMapper.selectById(classificationTestInfo.getTest7Id()));
            inf.setTest8_baseInfo(testBaseInfoMapper.selectById(classificationTestInfo.getTest8Id()));
            inf.setTest9_baseInfo(testBaseInfoMapper.selectById(classificationTestInfo.getTest9Id()));
            inf.setTest10_baseInfo(testBaseInfoMapper.selectById(classificationTestInfo.getTest10Id()));
            inf.setTestA_baseInfo(testBaseInfoMapper.selectById(classificationTestInfo.getAId()));
            inf.setTestB_baseInfo(testBaseInfoMapper.selectById(classificationTestInfo.getBId()));
            inf_list.add(inf);
        }
        return R.ok().setData("inf_list", inf_list).setData("pages", pageInfo.getPages()).setData("total", pageInfo.getTotal());

    }

    @Override
    public R getFindTestInfByPage(int page, int items, int user_id) throws Exception {
        // 初始化分页信息
        PageHelper.startPage(page, items);
        // 查询全部数据
        List<UserTest> userTests = userTestMapper.selectFindTestByUserId(user_id);
        // 借助分页助手获取分页信息
        PageInfo<UserTest> pageInfo = new PageInfo<>(userTests);
        userTests = pageInfo.getList();
        System.out.println("userTests = " + userTests);
        List<FindTestHistory> inf_list = new ArrayList<>();
        for (UserTest userTest : userTests) {
            FindTestHistory inf = new FindTestHistory();
            // 保存信息
            inf.setDoneTime(userTest.getDoneTime());
            inf.setGroupKind(userTest.getGroupKind());
            inf.setTimeSpendSeconds(userTest.getTimeSpendSeconds());
            inf.setUserId(userTest.getUserId());
            Integer testInfoId = userTest.getTestGroupId();
            inf.setFindInfoId(testInfoId);
            // 根据userTest的actionId去获取action
            FindTestAction findTestAction = findTestActionMapper.selectById(userTest.getTestActionId());
            // 根据userTest的testGroupId去获取题组的信息
            FindTestInfo findTestInfo = findTestInfoMapper.selectById(testInfoId);
            inf.setUserTestId(userTest.getId());
            inf.setFindTestAction(findTestAction);
            inf.setTest1_baseInfo(testBaseInfoMapper.selectById(findTestInfo.getTest1Id()));
            inf.setTest2_baseInfo(testBaseInfoMapper.selectById(findTestInfo.getTest2Id()));
            inf.setTest3_baseInfo(testBaseInfoMapper.selectById(findTestInfo.getTest3Id()));
            inf.setTest4_baseInfo(testBaseInfoMapper.selectById(findTestInfo.getTest4Id()));
            inf.setTest5_baseInfo(testBaseInfoMapper.selectById(findTestInfo.getTest5Id()));
            inf.setTest6_baseInfo(testBaseInfoMapper.selectById(findTestInfo.getTest6Id()));
            inf.setTest7_baseInfo(testBaseInfoMapper.selectById(findTestInfo.getTest7Id()));
            inf.setTest8_baseInfo(testBaseInfoMapper.selectById(findTestInfo.getTest8Id()));
            inf.setTarget_baseInfo(testBaseInfoMapper.selectById(findTestInfo.getTargetId()));
            inf_list.add(inf);
        }
        System.out.println(inf_list);
        return R.ok().setData("inf_list", inf_list).setData("pages", pageInfo.getPages()).setData("total", pageInfo.getTotal());
    }
}
