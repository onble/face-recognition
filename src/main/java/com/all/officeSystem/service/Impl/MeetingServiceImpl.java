package com.all.officeSystem.service.Impl;

import com.all.officeSystem.bean.MeetingInf;
import com.all.officeSystem.common.R;
import com.all.officeSystem.mapper.MeetingInfMapper;
import com.all.officeSystem.service.MeetingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
}
