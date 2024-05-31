package com.all.faceRecognition.mapper.findTest;

import com.all.faceRecognition.bean.save.ClassificationTestAction;
import com.all.faceRecognition.bean.save.FindTestAction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FindTestActionMapper {
    // 查找是否存在这样一组操作
    @Select("select id from find_test_action where action1=#{action1} and action2=#{action2} and action3=#{action3} and action4=#{action4} and action5=#{action5} and action6=#{action6} and action7=#{action7} and action8=#{action8}")
    Integer selectActionIdByActionClass(FindTestAction findTestAction) throws Exception;

    // 插入一组数据
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into find_test_action( action1, action2, action3, action4,action5, action6, action7, action8) value (#{action1},#{action2},#{action3},#{action4},#{action5},#{action6},#{action7},#{action8})")
    void insertNewTestGroup(FindTestAction findTestAction) throws Exception;

    // 根据id获取一组操作
    @Select("select * from find_test_action where id=#{id}")
    FindTestAction selectById(int id) throws Exception;
}
