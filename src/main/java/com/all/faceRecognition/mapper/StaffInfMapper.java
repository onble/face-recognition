package com.all.faceRecognition.mapper;

import com.all.faceRecognition.bean.StaffInf;
import org.apache.ibatis.annotations.*;

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

    // 根据职员id来减少已使用文件大小
    @Update("UPDATE staff_inf set staff_inf.occupy_file_size = staff_inf.occupy_file_size - #{size} where staff_id=#{staffId}")
    void updateOccupyFileSizeBySizeByStaffId(int staffId, double size) throws Exception;

    //插入信息
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into staff_inf values (default, #{name}, #{age}, #{phone}, #{gender}, #{isLeader}, #{positionId}, #{monthlySalary}, #{departmentId}, #{birthday}, #{folderSize}, #{occupyFileSize},default )")
    void insert(StaffInf th) throws Exception;

    // 根据id删除数据
    @Delete("delete from staff_inf where staff_id = #{id}")
    void deleteById(int id) throws Exception;

    @Select("select * from staff_inf where staff_id=#{id}")
    StaffInf selectById(int id) throws Exception;

    // 职员修改个人信息
    @Update("update staff_inf set name=#{name},age=#{age},phone=#{phone},gender=#{gender} where staff_id=#{staffId}")
    void changeByStaff(int staffId, String name, int age, String phone, boolean gender) throws Exception;

    // 根据id修改头像文件路径
    @Update("update staff_inf set header_file=#{filename} where staff_id=#{staffId}")
    void changeHeaderFile(int staffId, String filename) throws Exception;
}
