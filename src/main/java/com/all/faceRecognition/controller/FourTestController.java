package com.all.faceRecognition.controller;

import com.all.faceRecognition.common.R;
import com.all.faceRecognition.service.FourTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
            List<HashMap<String, Object>> data = fourTestService.get_test(30);
            return R.ok().setData("test_list", data);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setMessage(e.getMessage());
        }
    }
}
