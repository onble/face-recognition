package com.all.faceRecognition.controller;

import com.all.faceRecognition.bean.FourTestRecord;
import com.all.faceRecognition.common.R;
import com.all.faceRecognition.service.FourTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 四选一题目的控制层
 * */
@RestController
public class FourTestController {
    @Autowired
    private FourTestService fourTestService;

    // 获取题组
    @PostMapping("/four_test/get_tests")
    public R get_test() {
        try {
            List<HashMap<String, Object>> data = fourTestService.get_test(20);
            return R.ok().setData("test_list", data);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setMessage(e.getMessage());
        }
    }

    // 记录做题信息
    @PostMapping("/four_test/record")
    public R receiveTest(@RequestBody FourTestRecord testRequest, @RequestHeader("token") String token) {
        int user_id = Integer.parseInt(token);
        // 处理接收到的 testRequest 对象
        try {
            fourTestService.saveRecords(testRequest,user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setMessage(e.getMessage());
        }
//        System.out.println(testRequest.getAnswerSeconds());
//        System.out.println(testRequest.getAction());
//        System.out.println(testRequest.getTestGroupId());
//        for (FourTestRecord.Test test : testRequest.getTests()) {
//            System.out.println(test.getImage());
//            System.out.println(test.isAnswer());
//            System.out.println(test.getName());
//            System.out.println(test.getId());
//        }
        return R.ok();
    }
}
