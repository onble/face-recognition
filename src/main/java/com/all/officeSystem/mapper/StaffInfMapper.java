package com.all.officeSystem.mapper;

import com.all.officeSystem.bean.StaffInf;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StaffInfMapper {
    // 注册新的职员信息
    @Insert("insert into staff_inf(staff_id) value (#{staffId})")
    void insertNewStaffInf(int staffId) throws Exception;

    // 查询全部数据
    @Select("select * from staff_inf")
    List<StaffInf> selectAll() throws Exception;

    // 根据名字模糊查询全部数据
    @Select("select * from staff_inf where name like '%${name}%'")
    List<StaffInf> selectByName(String name) throws Exception;
}
