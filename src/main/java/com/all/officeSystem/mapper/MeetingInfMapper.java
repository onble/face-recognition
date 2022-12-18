package com.all.officeSystem.mapper;

import com.all.officeSystem.bean.MeetingInf;
import com.all.officeSystem.common.R;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MeetingInfMapper {
    // 根据分页获取会议信息
    //@Select("SELECT meeting.title as title,meeting.address as address,meeting.content as content,meeting.meeting_id as meetingId,meeting.staff_id as staffId,staff_inf.`name` as `name`,meeting.start_time as startTime,meeting.stop_time as stopTime,meeting.`status` as `status` FROM meeting,staff_inf WHERE staff_inf.staff_id=meeting.staff_id")
    @Select("SELECT meeting.title,meeting.address,meeting.content,meeting.meeting_id,meeting.staff_id,staff_inf.`name`,meeting.start_time,meeting.stop_time,meeting.`status` FROM meeting,staff_inf WHERE staff_inf.staff_id=meeting.staff_id")
    List<MeetingInf> selectAll()throws Exception;
}
