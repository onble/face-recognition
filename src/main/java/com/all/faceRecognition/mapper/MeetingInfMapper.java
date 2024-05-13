package com.all.faceRecognition.mapper;

import com.all.faceRecognition.bean.MeetingInf;
import lombok.Data;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MeetingInfMapper {
    // 根据分页获取会议信息
    //@Select("SELECT meeting.title as title,meeting.address as address,meeting.content as content,meeting.meeting_id as meetingId,meeting.meeting_id as meetingId,meeting_inf.`name` as `name`,meeting.start_time as startTime,meeting.stop_time as stopTime,meeting.`status` as `status` FROM meeting,meeting_inf WHERE meeting_inf.meeting_id=meeting.meeting_id")
    @Select("SELECT meeting.title,meeting.address,meeting.content,meeting.meeting_id,meeting.meeting_id,meeting.start_time,meeting.stop_time,meeting.`status`,staff_inf.name as `name`  FROM meeting,staff_inf where meeting.staff_id=staff_inf.staff_id")
    List<MeetingInf> selectAll() throws Exception;

    //获取全部数据
//    @Select("select * from department")
//    List<Department> selectAll() throws Exception;

    // 根据会议id获取信息
    @Select("select * from meeting where meeting_id=#{meetingId}")
    MeetingInf selectById(int meetingId) throws Exception;

    //根据id删除数据
    @Delete("delete from meeting where meeting_id=#{meetingId}")
    void deleteById(int meetingId) throws Exception;

    // 插入数据
    @Insert("insert into meeting value (default,#{title},#{address},#{content},#{satffId},#{startTime},#{stopTime},#{status})")
    void insert(String title, String address, String content, int staffId, Data startTime, Data stopTime, int status);


    // 修改数据
    @Update("update meeting set title=#{title},address=#{address},content=#{content},staff_id=#{satffId},start_time=#{startTime},stop_time=#{stopTime},status=#{status} where meeting_id=#{meetingId}")
    void change(int meetingId, String title, String address, String content, int staffId, Data startTime, Data stopTime, int status) throws Exception;

    // 获取数据数量
    @Select("select count(meeting_id) from meeting where meeting_id =#{meetingId}")
    int getNum(int meetingId) throws Exception;
}
