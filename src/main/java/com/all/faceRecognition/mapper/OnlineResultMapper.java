package com.all.faceRecognition.mapper;

import com.all.faceRecognition.bean.OnlineResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OnlineResultMapper {
    // 查询在线信息结果
    @Select("SELECT staff_inf.`name` as `name`,department.`name` as departmentName,post.`name` as positionName,`online`.`status` as onlineStatus FROM staff_inf,`online`,department,post WHERE staff_id=`online`.id and staff_inf.department_id=department.id and staff_inf.position_id = post.id")
    List<OnlineResult> selectAll() throws Exception;

    // 根据名字模糊查询数据
    @Select("SELECT staff_inf.`name` as `name`,department.`name` as departmentName,post.`name` as positionName,`online`.`status` as onlineStatus FROM staff_inf,`online`,department,post WHERE staff_id=`online`.id and staff_inf.department_id=department.id and staff_inf.position_id = post.id and staff_inf.name like '%${name}%'")
    List<OnlineResult> selectByName(String name) throws Exception;
}
