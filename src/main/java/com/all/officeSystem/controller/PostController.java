package com.all.officeSystem.controller;

import com.all.officeSystem.common.R;
import com.all.officeSystem.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
