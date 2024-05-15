package com.all.faceRecognition.mapper;

import com.all.faceRecognition.bean.MeetingInf;
import com.all.faceRecognition.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/*
 * 用户的持久层
 * */
@Mapper
public interface UserMapper {
    // 根据用户名查询
    @Select("select * from user where account = #{username}")
    User selectByUsername(String username) throws Exception;

    // 注册新用户
    @Insert("insert into user values (default,#{account},#{password},0,default)")
    void insertNewUser(String account, String password) throws Exception;

    // 根据id获取用户
    @Select("select * from user where id=#{id}")
    User selectById(int id) throws Exception;
}
