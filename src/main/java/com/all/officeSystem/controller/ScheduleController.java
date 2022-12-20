package com.all.officeSystem.controller;

import com.all.officeSystem.bean.Schedule;
import com.all.officeSystem.common.R;
import com.all.officeSystem.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 日程管理的控制器
 */
@Slf4j
@RestController
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/schedule/getList")
    public R getListByPageByStaffId(int page, int items, int staffId) {
        try {
            return scheduleService.getScheduleInfByStaffId(page, items, staffId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    // 根据日程id删除数据
    @DeleteMapping("/schedule/delete")
    public R deleteById(int id) {
        try {
            scheduleService.deleteById(id);
            return R.ok().setData("info", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setData("info", "删除失败");
        }
    }

    // 根据id批量删除
    @DeleteMapping("/schedule/deletes")
    public R batchDelete(int[] ids) {
        try {
            scheduleService.batchDelete(ids);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    // 添加
    @PostMapping("/schedule/add")
    public R add(Schedule schedule) {
        try {
            log.info("schedule.getDate() = " + schedule.getDate()); // 传入后端的时间是对的
            scheduleService.insert(schedule.getStaffId(), schedule.getTitle(), schedule.getContent(), schedule.getDate());
            return R.ok().setData("info", "插入成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setData("info", "插入失败");
        }
    }

    // 根据id获取信息
    @PostMapping("/schedule/get")
    public R get(int id) {
        try {
            Schedule schedule = scheduleService.selectById(id);
            return R.ok().setData("schedule_inf", schedule);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    // 修改数据
    @PostMapping("/schedule/change")
    public R change(int staffId, String title, String content, Date date, int scheduleId) {
        try {
            scheduleService.change(staffId, title, content, date, scheduleId);
            return R.ok().setData("info", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setData("info", "修改失败");
        }
    }

    // 获取数据数量
    @PostMapping("/schedule/num")
    public R getNum(int staffId) {
        try {
            int num = scheduleService.getNum(staffId);
            return R.ok().setData("schedule_inf", num);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
