package com.all.officeSystem.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StaffInfMapper {
    // 注册新的职员信息
    @Insert("insert into staff_inf(staff_id) value (#{staffId})")
    void insertNewStaffInf(int staffId) throws Exception;
}
