package com.all.officeSystem.service.Impl;

import com.all.officeSystem.common.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostServiceImpl {
    @Autowired
    private PostInfMapper postMapper;

    @Override
    public R getpostByPage(int page, int items) throws Exception {
        // 初始化分页信息
        PageHelper.startPage(page, items);
        // 查询全部数据
        List<post> postInfs = postMapper.selectAll();
        // 借助分页助手获取分页信息
        PageInfo<post> pageInfo = new PageInfo<>(post);
        return R.ok().setData("post_inf", pageInfo.getList()).setData("pages", pageInfo.getPages());

    }
}