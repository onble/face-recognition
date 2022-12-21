package com.all.officeSystem.service.Impl;

import com.all.officeSystem.bean.Post;
import com.all.officeSystem.common.R;
import com.all.officeSystem.mapper.PostMapper;
import com.all.officeSystem.service.PostService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    @Override
    public Post selectById(int id) throws Exception {
        return null;
    }

    @Override
    public List<Post> selectAll() throws Exception {
        return null;
    }

    @Override
    public R getPostInfByPage(int page, int items) throws Exception {
        // 初始化分页信息
        PageHelper.startPage(page, items);
        // 查询全部数据
        List<Post> postInfs = postMapper.selectAll();
        // 借助分页助手获取分页信息
        PageInfo<Post> pageInfo = new PageInfo<>(postInfs);
        return R.ok().setData("post_inf", pageInfo.getList()).setData("pages", pageInfo.getPages());

    }

    @Override
    public R deleteById(int id) throws Exception {
        return null;
    }

    @Override
    public void batchDelete(int[] ids) throws Exception {

    }

    @Override
    public void insert(String name, String duty) throws Exception {

    }

    @Override
    public void change(String name, String duty) throws Exception {

    }

    @Override
    public int getNum(int id) throws Exception {
        return 0;
    }
}