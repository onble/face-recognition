package com.all.faceRecognition.mapper;

import com.all.faceRecognition.bean.FourTestActions;
import com.all.faceRecognition.bean.save.ClassificationTestAction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClassificationTestActionMapper {
    // 查找是否存在这样一组操作
    @Select("select id from classification_test_action where action1=#{action1} and action2=#{action2} and action3=#{action3} and action4=#{action4} and action5=#{action5} and action6=#{action6} and action7=#{action7} and action8=#{action8} and action9=#{action9} and action10=#{action10}")
    Integer selectActionIdByActions(Integer action1, Integer action2, Integer action3, Integer action4, Integer action5, Integer action6, Integer action7, Integer action8, Integer action9, Integer action10) throws Exception;

    // 查找是否存在这样一组操作
    @Select("select id from classification_test_action where action1=#{action1} and action2=#{action2} and action3=#{action3} and action4=#{action4} and action5=#{action5} and action6=#{action6} and action7=#{action7} and action8=#{action8} and action9=#{action9} and action10=#{action10}")
    Integer selectActionIdByActionClass(ClassificationTestAction classificationTestAction) throws Exception;

    // 插入一组数据
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into classification_test_action( action1, action2, action3, action4,action5, action6, action7, action8,action9, action10) value (#{action1},#{action2},#{action3},#{action4},#{action5},#{action6},#{action7},#{action8},#{action9},#{action10})")
    void insertNewTestGroup(ClassificationTestAction classificationTestAction) throws Exception;

    // 根据id获取一组操作
    @Select("select * from classification_test_action where id=#{id}")
    ClassificationTestAction selectById(int id) throws Exception;
}
