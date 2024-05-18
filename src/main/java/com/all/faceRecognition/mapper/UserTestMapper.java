package com.all.faceRecognition.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface UserTestMapper {
    // 插入一组数据
    @Insert("insert into user_test(test_group_id, done_time, group_kind, time_spend_seconds, user_id, test_action_id) value (#{test_group_id},#{done_time},#{group_kind},#{time_spend_seconds},#{user_id},#{testAction_id})")
    void insert(Integer test_group_id, LocalDateTime done_time, int group_kind, int time_spend_seconds, Integer user_id, Integer testAction_id) throws Exception;
}
