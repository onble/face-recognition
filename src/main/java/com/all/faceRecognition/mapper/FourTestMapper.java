package com.all.faceRecognition.mapper;

import com.all.faceRecognition.bean.FourTest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/*
 * 四选一测试的持久层
 * */
@Mapper
public interface FourTestMapper {
    // 根据图片路径获取题目id
    @Select("select id from test_base_info where imageIndex = #{imageIndex}")
    Integer selectTestIdByImageIndex(String imageIndex) throws Exception;

    // 插入一个新的题目
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into test_base_info(people_id, imageIndex) VALUE (#{people_id},#{imageIndex})")
    void insertNewTest(FourTest fourTest) throws Exception;
}
