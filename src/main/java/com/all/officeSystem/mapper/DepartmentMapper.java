package com.all.officeSystem.mapper;

import com.all.officeSystem.bean.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    // 根据名字模糊查询全部数据
    @Select("select * from department where name like '%${name}%'")
    List<Department> selectByName(String name) throws Exception;

//    // 职员修改个人信息
//    @Update("update staff_inf set name=#{name},age=#{age},phone=#{phone},gender=#{gender} where staff_id=#{staffId}")
//    void changeByStaff(int staffId, String name, int age, String phone, boolean gender) throws Exception;
//
//    // 根据id修改头像文件路径
//    @Update("update staff_inf set header_file=#{filename} where staff_id=#{staffId}")
//    void changeHeaderFile(int staffId, String filename) throws Exception;


    // 根据部门id获取部门信息
    @Select("select * from department where id=#{id}")
    Department selectById(int id) throws Exception;

    //获取全部信息
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
    void insert(String name, String homePage);

//    // 根据id获取一条信息
//    @Select("select * from department where id=#{id}")
//    Department selectById(int id) throws Exception;

    // 修改数据
    @Update("update department set name=#{name},home_page=#{homePage} where id=#{id}")
    void change(int id, String name, String homePage) throws Exception;

    // 获取数据数量
    @Select("select count(id) from department where id =#{id}")
    int getNum(int id) throws Exception;

}


