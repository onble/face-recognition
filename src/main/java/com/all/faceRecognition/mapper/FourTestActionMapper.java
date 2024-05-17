package com.all.faceRecognition.mapper;

import com.all.faceRecognition.bean.FourTestActions;
import com.all.faceRecognition.bean.FourTestInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FourTestActionMapper {
    // 查找是否存在这样一组操作
    @Select("select id from four_test_action where action1=#{action1} and action2=#{action2} and action3=#{action3} and action4=#{action4}")
    Integer selectActionIdByActions(Integer action1, Integer action2, Integer action3, Integer action4) throws Exception;

    // 插入一组数据
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into four_test_action( action1, action2, action3, action4) value (#{action1},#{action2},#{action3},#{action4})")
    void insertNewTestGroup(FourTestActions fourTestActions) throws Exception;
}
