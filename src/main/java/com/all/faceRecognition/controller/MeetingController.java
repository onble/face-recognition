package com.all.faceRecognition.controller;

import com.all.faceRecognition.bean.MeetingInf;
import com.all.faceRecognition.common.R;
import com.all.faceRecognition.service.MeetingService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    // 根据id删除数据
    @DeleteMapping("/meeting/delete")
    public R deleteById(int id) {
        try {
            meetingService.deleteById(id);
            return R.ok().setData("info", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setData("info", "删除失败");
        }
    }

    // 根据id批量删除
    @DeleteMapping("/meeting/deletes")
    public R batchDelete(int[] ids) {
        try {
            meetingService.batchDelete(ids);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    // 添加
    @PostMapping("/meeting/add")
    public R add(String title, String address, String content, int staffId , Data startTime, Data stopTime, int status) {
        try {
            meetingService.insert( title, address, content, staffId , startTime, stopTime,status);
            return R.ok().setData("info", "插入成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setData("info", "插入失败");
        }
    }


    // 根据id获取信息
    @PostMapping("/meeting/get")
    public R get(int id) {

        try {
            MeetingInf meeting = meetingService.selectById(id);
            return R.ok().setData("meeting_inf", meeting);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }

    }

    // 修改数据
    @PostMapping("/meeting/change")
    public R change( int meetingId, String title, String address,String content,int staffId ,Data startTime,Data stopTime,int status ) {
        try {
            meetingService.change(meetingId, title, address,content, staffId, startTime,stopTime,status);
            return R.ok().setData("info", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setData("info", "修改失败");
        }
    }

    // 获取数据数量
//    @PostMapping("/meeting/num")
//    public R getNum(int staffId) {
//        try {
//            int num = meetingService.getNum(staffId);
//            return R.ok().setData("meeting_inf", num);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return R.error();
//        }
//    }

}
