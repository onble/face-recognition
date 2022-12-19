package com.all.officeSystem.mapper;

import com.all.officeSystem.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DepartmentMapper {
    // 根据部门id获取部门信息
    @Select("select * from department where id=#{id}")
    Department selectById(int id) throws Exception;

    // TODO: GJY 写获取全部信息并返回部门信息的列表，最好写上分页功能 参考OnlineResultMapper下面的selectAll

}
