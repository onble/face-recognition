package com.all.officeSystem.controller;

import com.all.officeSystem.bean.Post;
import com.all.officeSystem.common.R;
import com.all.officeSystem.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 职务的控制器
 */

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/post/getList")
    public R getListByPage(int page, int items, int adminId) {
        try {
            return postService.getPostInfByPage(page, items);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    // 根据职务id删除数据
    @DeleteMapping("/post/delete")
    public R deleteById(int id) {
        try {
            postService.deleteById(id);
            return R.ok().setData("info", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setData("info", "删除失败");
        }
    }

    // 根据id批量删除
    @DeleteMapping("/post/deletes")
    public R batchDelete(int[] ids) {
        try {
            postService.batchDelete(ids);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    // 添加
    @PostMapping("/post/add")
    public R add(String name, String duty, int adminId) {
        try {
            postService.insert(name, duty);
            return R.ok().setData("info", "插入成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setData("info", "插入失败");
        }
    }

    // 根据id获取信息
    @PostMapping("/post/get")
    public R get(int id) {
        try {
            Post post = postService.selectById(id);
            return R.ok().setData("post_inf", post);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    // 修改数据
    @PostMapping("/post/change")
    public R change(int adminId, String name, String duty, int postId) {
        try {
            postService.change(name, duty, postId);
            return R.ok().setData("info", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setData("info", "修改失败");
        }
    }

    // 获取数据数量
    @PostMapping("/post/num")
    public R getNum(int adminId) {
        try {
            int num = postService.getNum();
            return R.ok().setData("post_inf", num);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
