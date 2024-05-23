package com.all.faceRecognition.controller;

import com.all.faceRecognition.common.R;
import com.all.faceRecognition.service.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserTestController {
    @Autowired
    private UserTestService userTestService;

    @GetMapping("/trainRecords/history/pages")
    public R trainHistory(@RequestHeader("token") String token, int page, int items) {
        try {
            int user_id = Integer.parseInt(token);
            R result = userTestService.getUserTestInfByPage(page, items, user_id);
            return result;
        } catch (Exception e) {
            return R.error().setMessage(e.getMessage());
        }
    }
    @GetMapping("/trainRecords/fourTest/pages")
    public R FourTestHistory(@RequestHeader("token") String token, int page, int items) {
        try {
            int user_id = Integer.parseInt(token);
            R result = userTestService.getFourTestInfByPage(page, items, user_id);
            return result;
        } catch (Exception e) {
            return R.error().setMessage(e.getMessage());
        }
    }
}
