package com.all.faceRecognition.mapper;

import com.all.faceRecognition.bean.Staff;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StaffMapper {

    // 根据职员账号获取职员对象
    @Select("select * from staff where account = #{account}")
    Staff selectByAccount(String account) throws Exception;

    // 注册新职员账号
    @Insert("insert into staff values (default,#{account},#{password},0)")
    void insertNewStaff(String account, String password) throws Exception;

}
