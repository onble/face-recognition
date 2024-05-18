package com.all.faceRecognition.service.Impl;

import com.all.faceRecognition.bean.Department;
import com.all.faceRecognition.bean.UserTest;
import com.all.faceRecognition.common.R;
import com.all.faceRecognition.mapper.UserTestMapper;
import com.all.faceRecognition.service.UserTestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTestServiceImpl implements UserTestService {
    @Autowired
    private UserTestMapper userTestMapper;

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
}
