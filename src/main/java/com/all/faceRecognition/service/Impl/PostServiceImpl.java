package com.all.faceRecognition.service.Impl;

import com.all.faceRecognition.bean.Post;
import com.all.faceRecognition.common.R;
import com.all.faceRecognition.mapper.PostMapper;
import com.all.faceRecognition.service.PostService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    // 根据id获取一条数据
    @Override
    public Post selectById(int id) throws Exception {
        return postMapper.selectById(id);
    }

//    @Override
//    public Post selectById(int id) throws Exception {
//        return null;
//    }

//    @Override
//    public List<Post> selectAll() throws Exception {
//        return null;
//    }

    //分页
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
    public R deleteById(int id) {
        try {
            postMapper.deleteById(id);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    //批量删除
    @Override
    public int batchDelete(int[] ids) throws Exception {
        for (int id : ids) {
            postMapper.deleteById(id);
        }
        return 0;
    }


    //插入
    @Override
    public void insert(String name, String duty) throws Exception {
        postMapper.insert(name, duty);

    }

    //修改
    @Override
    public void change(String name, String duty, int postId) throws Exception {

        postMapper.change(name, duty, postId);
    }

    @Override
    public int getNum() throws Exception {

        return postMapper.getNum();
    }

    @Override
    public PageInfo<Post> getPostByPageWithName(int page, int items, String name) throws Exception {
        // 初始化分页信息
        PageHelper.startPage(page, items);
        // 查询全部数据
        List<Post> posts = postMapper.selectByName(name);
        // 借助分页助手获取分页信息
        PageInfo<Post> pageInfo = new PageInfo<>(posts);
        return pageInfo;
    }
}