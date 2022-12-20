package com.all.officeSystem.service.Impl;

import com.all.officeSystem.bean.Schedule;
import com.all.officeSystem.common.R;
import com.all.officeSystem.mapper.ScheduleMapper;
import com.all.officeSystem.service.ScheduleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public R getScheduleInfByStaffId(int page, int items, int staffId) throws Exception {
        // 初始化分页信息
        PageHelper.startPage(page, items);
        // 查询全部数据
        List<Schedule> schedules = scheduleMapper.selectByStaffIs(staffId);
        // 借助分页助手获取分页信息
        PageInfo<Schedule> pageInfo = new PageInfo<>(schedules);
        return R.ok().setData("schedule_inf", pageInfo.getList()).setData("pages", pageInfo.getPages());
    }

    @Override
    public R deleteById(int scheduleId) {
        try {
            scheduleMapper.deleteById(scheduleId);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    @Override
    public void batchDelete(int[] ids) throws Exception {
        for (int id : ids) {
            scheduleMapper.deleteById(id);
        }
    }

    @Override
    public void insert(int staffId, String title, String content, Date date) throws Exception {
        scheduleMapper.insert(staffId, title, content, date);
    }

    @Override
    public Schedule selectById(int id) throws Exception {
        return scheduleMapper.selectById(id);
    }

    @Override
    public void change(int staffId, String title, String content, Date date, int scheduleId) throws Exception {
        scheduleMapper.change(scheduleId, title, content, date);
    }

    @Override
    public int getNum(int scheduleId) throws Exception {
        return scheduleMapper.getNum(scheduleId);
    }
}
