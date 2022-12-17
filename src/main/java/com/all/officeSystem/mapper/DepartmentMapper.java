package com.all.officeSystem.mapper;

import com.all.officeSystem.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DepartmentMapper {
    // 根据部门id获取部门信息
    @Select("select * from department where id=#{id}")
    Department selectById(int id) throws Exception;
}
