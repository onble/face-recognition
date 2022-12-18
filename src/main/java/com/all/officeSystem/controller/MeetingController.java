package com.all.officeSystem.controller;

import com.all.officeSystem.common.R;
import com.all.officeSystem.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会议的控制器
 */
@RestController
public class MeetingController {
    @Autowired
    private MeetingService meetingService;

    @PostMapping("/meeting/getList")
    public R getListByPage(int page, int items){
        try {
            return meetingService.getMeetingInfByPage(page, items);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
