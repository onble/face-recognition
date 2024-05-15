package com.all.faceRecognition.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/*
 * 人物信息的持久层
 * */
@Mapper
public interface PeopleBaseInfoMapper {
    // 根据人物姓名获取对应的人物id
    @Select("select id from people_base_info where name =#{peopleName}")
    Integer selectIdByPeopleName(String peopleName) throws Exception;
}
