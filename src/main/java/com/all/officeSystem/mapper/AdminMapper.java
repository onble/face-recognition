package com.all.officeSystem.mapper;

import com.all.officeSystem.bean.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 管理员的持久层
 */
@Mapper
public interface AdminMapper {

    //根据用户名查询
    @Select("select * from admin where account = #{username}")
    Admin selectByUsername(String username) throws Exception;




}
