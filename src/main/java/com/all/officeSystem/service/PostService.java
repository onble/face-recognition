package com.all.officeSystem.service;

import com.all.officeSystem.bean.Post;
import com.all.officeSystem.common.R;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * 职务的业务层
 */
@Service
public interface PostService {
  //获取全部信息
//    List<Post> selectAll() throws Exception;

    // 根据页码获取职务列表
    R getPostInfByPage(int page, int items) throws Exception;


    // 根据id删除
    R deleteById(int id) throws Exception;

    // 根据id批量删除数据
    int batchDelete(int[] ids) throws Exception;

    // 插入数据
    void insert(String name, String duty) throws Exception;

    // 根据id获取一条数据
    Post selectById(int id) throws Exception;

    // 修改数据
    void change(String name, String duty, int postId) throws Exception;

    //获取数据
    int getNum() throws Exception;

    PageInfo<Post> getPostByPageWithName(int page, int items, String name) throws Exception;
}



