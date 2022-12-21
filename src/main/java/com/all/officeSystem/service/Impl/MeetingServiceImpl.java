package com.all.officeSystem.service.Impl;

import com.all.officeSystem.bean.MeetingInf;
import com.all.officeSystem.bean.Todo;
import com.all.officeSystem.common.R;
import com.all.officeSystem.mapper.MeetingInfMapper;
import com.all.officeSystem.service.MeetingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private MeetingInfMapper meetingInfMapper;

    @Override
    public R getMeetingInfByPage(int page, int items) throws Exception {
        // 初始化分页信息
        PageHelper.startPage(page,items);
        // 查询全部数据
        List<MeetingInf> meetingInfs = meetingInfMapper.selectAll();
        // 借助分页助手获取分页信息
        PageInfo<MeetingInf> pageInfo = new PageInfo<>(meetingInfs);
        return R.ok().setData("meeting_inf",pageInfo.getList()).setData("pages",pageInfo.getPages());
    }

    @Override
    public R deleteById(int meetingId) {
        try {
            meetingInfMapper.deleteById(meetingId);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    @Override
    public void batchDelete(int[] ids) throws Exception {
        for (int meetingId : ids) {
            meetingInfMapper.deleteById(meetingId);
        }
    }

    @Override
    public void insert(String title, String address, String content, int staffId , Data startTime, Data stopTime, int status) throws Exception {
        meetingInfMapper.insert( title, address, content, staffId , startTime, stopTime,status);
    }

    @Override
    public MeetingInf selectById(int meetingId) throws Exception {

        return meetingInfMapper.selectById(meetingId);

    }

    @Override
    public void change(int meetingId, String title, String address, String content, int staffId , Data startTime, Data stopTime, int status) throws Exception {
        meetingInfMapper.change(meetingId, title, address, content, staffId , startTime, stopTime,status);
    }

    @Override
    public int getNum(int staffId) throws Exception {
        return meetingInfMapper.getNum(staffId);
    }
}
