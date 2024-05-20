package com.all.faceRecognition.controller;

import com.all.faceRecognition.bean.save.SaveFindTestInfo;
import com.all.faceRecognition.common.R;


import com.all.faceRecognition.service.FindTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class FindTestController {
    @Autowired
    private FindTestService findTestService;

    // 获取题组
    @GetMapping("/find_test/get_tests")
    public R get_test() {
        try {
            HashMap<String, Object> data = findTestService.get_test();
            return R.ok().setData("test", data);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setMessage(e.getMessage());
        }
    }

    // 记录做题信息
    @PostMapping("/find_test/record")
    public R receiveTest(@RequestBody SaveFindTestInfo saveFindTestInfo, @RequestHeader("token") String token) {
        int user_id = Integer.parseInt(token);
        System.out.println(saveFindTestInfo);
        // 处理接收到的 testRequest 对象
        try {
            findTestService.saveRecords(saveFindTestInfo, user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setMessage(e.getMessage());
        }

        return R.ok();
    }
}
