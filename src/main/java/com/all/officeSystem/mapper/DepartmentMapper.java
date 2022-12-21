package com.all.officeSystem.mapper;

import com.all.officeSystem.bean.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    // 根据部门id获取部门信息
    @Select("select * from department where id=#{id}")
    Department selectById(int id) throws Exception;

    // TODO: GJY 写获取全部信息并返回部门信息的列表，最好写上分页功能 参考OnlineResultMapper下面的selectAll
    @Select("select * from department")
    List<Department> selectAll() throws Exception;
    //   List<Department> selectAll() throws Exception;

    // GJY： 修改部门信息
//    @Update("update * set department ")
//    void updateMoneyById(int id) throws Exception;

    //根据id删除数据
    @Delete("delete from department where id=#{id}")
    void deleteById(int id) throws Exception;

    // 插入数据
    @Insert("insert into department value (default,#{name},#{homePage})")
    void insert( String name, String homePage);

//    // 根据id获取一条信息
//    @Select("select * from department where id=#{id}")
//    Department selectById(int id) throws Exception;

    // 修改数据
    @Update("update department set name=#{name},home_page=#{homePage}")
    void change(String name, String homePage) throws Exception;

    // 获取数据数量
    @Select("select count(id) from department where id =#{id}")
    int getNum(int id) throws Exception;

}


