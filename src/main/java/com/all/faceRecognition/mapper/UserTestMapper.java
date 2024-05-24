package com.all.faceRecognition.mapper;

import com.all.faceRecognition.bean.UserTest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserTestMapper {
    // 插入一组数据
    @Insert("insert into user_test(test_group_id, done_time, group_kind, time_spend_seconds, user_id, test_action_id) value (#{test_group_id},#{done_time},#{group_kind},#{time_spend_seconds},#{user_id},#{testAction_id})")
    void insert(Integer test_group_id, LocalDateTime done_time, int group_kind, int time_spend_seconds, Integer user_id, Integer testAction_id) throws Exception;

    // 按用户查询数据
    @Select("select * from user_test where user_id=#{user_id}")
    List<UserTest> selectByUserId(Integer user_id) throws Exception;

    // 搜索对应题目的数据
    @Select("select * from user_test where user_id=#{user_id} and group_kind=1")
    List<UserTest> selectFourTestByUserId(Integer user_id) throws Exception;
    // 搜索对应题目的数据
    @Select("select * from user_test where user_id=#{user_id} and group_kind=2")
    List<UserTest> selectClassificationTestByUserId(Integer user_id) throws Exception;
}
