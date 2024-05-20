package com.all.faceRecognition.controller;

import com.all.faceRecognition.bean.FourTestRecord;
import com.all.faceRecognition.bean.save.SaveClassificationTestInfo;
import com.all.faceRecognition.common.R;
import com.all.faceRecognition.service.ClassificationTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class ClassificationTestController {
    @Autowired
    ClassificationTestService classificationTestService;

    // 获取题组
    @GetMapping("/classification_test/get_tests")
    public R get_test() {
        try {
            HashMap<String, Object> data = classificationTestService.get_test();
            return R.ok().setData("test", data);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setMessage(e.getMessage());
        }
    }
    // 记录做题信息
    @PostMapping("/classification_test/record")
    public R receiveTest(@RequestBody SaveClassificationTestInfo saveClassificationTestInfo, @RequestHeader("token") String token) {
        int user_id = Integer.parseInt(token);
        System.out.println(saveClassificationTestInfo);
        // 处理接收到的 testRequest 对象
        try {
            classificationTestService.saveRecords(saveClassificationTestInfo,user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setMessage(e.getMessage());
        }

        return R.ok();
    }
}
