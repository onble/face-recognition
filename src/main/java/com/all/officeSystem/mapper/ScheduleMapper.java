package com.all.officeSystem.mapper;

import com.all.officeSystem.bean.Schedule;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface ScheduleMapper {
    // 根据职员id获取全部日程
    @Select("select * from schedule where staff_id =#{staffId}")
    List<Schedule> selectByStaffIs(int staffId) throws Exception;

    // 根据日程id删除数据
    @Delete("delete from schedule where schedule_id=#{scheduleId}")
    void deleteById(int scheduleId) throws Exception;

    // 插入数据
    @Insert("insert into schedule value (default,#{title},#{date},#{content},#{staffId})")
    void insert(int staffId, String title, String content, Date date);

    // 根据id获取一条日程信息
    @Select("select * from schedule where schedule_id=#{scheduleId}")
    Schedule selectById(int scheduleId) throws Exception;

    // 修改数据
    @Update("update schedule set title=#{title},content=#{content},date=#{date} where schedule_id=#{scheduleId}")
    void change(int scheduleId, String title, String content, Date date) throws Exception;

    // 获取数据数量
    @Select("select count(schedule_id) from schedule where staff_id=#{staffId}")
    int getNum(int staffId) throws Exception;
}
