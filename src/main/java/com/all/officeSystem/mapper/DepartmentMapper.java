package com.all.officeSystem.mapper;

import com.all.officeSystem.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    // 根据部门id获取部门信息
    @Select("select * from department where id=#{id}")
    Department selectById(int id) throws Exception;

    // TODO: GJY 写获取全部信息并返回部门信息的列表，最好写上分页功能 参考OnlineResultMapper下面的selectAll
    @Select("select * from department ")
    List<Department> selectAll() throws Exception;
    //   List<Department> selectAll() throws Exception;

    // GJY： 修改部门信息
    @Update("update * set department ")
    void updateMoneyById(int id) throws Exception;

}


