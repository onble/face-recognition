package com.all.officeSystem.mapper;

import com.all.officeSystem.bean.FolderInf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FolderInfMapper {
    // 根据职员id获取文件列表
    @Select("SELECT folder.id,folder.`name`,folder.staff_id,folder.upload_time,folder.size FROM folder where folder.staff_id= #{id}")
    List<FolderInf> selectByStaffId(int id) throws Exception;
}
