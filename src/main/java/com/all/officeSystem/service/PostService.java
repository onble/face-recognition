package com.all.officeSystem.service;

import com.all.officeSystem.bean.Post;
import com.all.officeSystem.common.R;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *职务的业务层
 */
@Service
public interface PostService {

    // 根据id获取一条数据
    Post selectById(int id) throws Exception;
    //获取全部信息
    List<Post> selectAll() throws Exception;

    // 根据页码获取职务列表
    R getPostInfByPage(int page, int items) throws Exception;

    // 根据id删除
    R deleteById(int id) throws Exception;

    // 根据id批量删除数据
    void batchDelete(int[] ids) throws Exception;

    // 插入数据
    void insert( String name, String duty) throws Exception;

    // 修改数据
    void change(String name, String duty) throws Exception;

    //获取数据
    int  getNum(int id) throws Exception;


}



