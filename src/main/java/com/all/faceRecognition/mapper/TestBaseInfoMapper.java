package com.all.faceRecognition.mapper;

import com.all.faceRecognition.bean.TestBaseInfo;
import com.all.faceRecognition.bean.get.TestBaseInfoUserGet;
import org.apache.ibatis.annotations.*;

/*
 * 四选一测试的持久层
 * */
@Mapper
public interface TestBaseInfoMapper {
    // 根据图片路径获取题目id
    @Select("select id from test_base_info where imageIndex = #{imageIndex}")
    Integer selectTestIdByImageIndex(String imageIndex) throws Exception;

    // 插入一个新的题目
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into test_base_info(people_id, imageIndex) VALUE (#{people_id},#{imageIndex})")
    void insertNewTest(TestBaseInfo testBaseInfo) throws Exception;

    // 一个题目的错误次数加一
    @Update("update test_base_info set wrong_times = wrong_times + 1 where id = #{test_id}")
    void addWrongTimes(int test_id) throws Exception;

    // 一个题目的正确次数加一
    @Update("update test_base_info set right_times = right_times + 1 where id = #{test_id}")
    void addRightTimes(int test_id) throws Exception;

    // 根据id获取数据
    @Select("select * from test_base_info where id = #{id}")
    TestBaseInfoUserGet selectById(int id);
}
