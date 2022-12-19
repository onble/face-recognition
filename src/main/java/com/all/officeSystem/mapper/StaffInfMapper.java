package com.all.officeSystem.mapper;

import com.all.officeSystem.bean.StaffInf;
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
    @Insert("insert into staffInf values (default, #{name}, #{age}, #{phone}, #{gender}, #{isLeader}, #{positionId}, #{monthlySalary}, #{departmentId}, #{birthday}, #{folderSize}, #{occupyFileSize} )")
    void insert(StaffInf th) throws Exception;

    // 根据id删除数据
    @Delete("delete from staffinf where id = #{id}")
    void deleteById(int id) throws Exception;


}
