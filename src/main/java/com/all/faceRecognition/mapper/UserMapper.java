package com.all.faceRecognition.mapper;

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
    User selectByUsername(String uusername) throws Exception;

    // 注册新用户
    @Insert("insert into user values (default,#{account},#{password},0)")
    void insertNewUser(String account, String password) throws Exception;
}
