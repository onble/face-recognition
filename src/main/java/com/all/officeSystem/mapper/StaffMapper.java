package com.all.officeSystem.mapper;

import com.all.officeSystem.bean.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StaffMapper {

    // 根据职员账号获取职员对象
    @Select("select * from staff where account = #{account}")
    Staff selectByAccount(String account) throws Exception;
}
