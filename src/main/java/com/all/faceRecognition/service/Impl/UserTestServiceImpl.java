package com.all.faceRecognition.service.Impl;

import com.all.faceRecognition.bean.FourTestActions;
import com.all.faceRecognition.bean.FourTestInfo;
import com.all.faceRecognition.bean.UserTest;
import com.all.faceRecognition.bean.get.fourTest.fourTestHistory;
import com.all.faceRecognition.common.R;
import com.all.faceRecognition.mapper.FourTestActionMapper;
import com.all.faceRecognition.mapper.FourTestInfoMapper;
import com.all.faceRecognition.mapper.TestBaseInfoMapper;
import com.all.faceRecognition.mapper.UserTestMapper;
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
}
