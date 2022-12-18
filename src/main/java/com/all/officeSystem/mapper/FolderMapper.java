package com.all.officeSystem.mapper;

import com.all.officeSystem.bean.Folder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 文件的持久层
 */
@Mapper
public interface FolderMapper {

    // 根据id删除数据
    @Delete("delete from folder where id=#{id}")
    void deleteById(int id) throws Exception;
    // 根据文件id获取文件信息
    @Select("select * from folder where id=#{id}")
    Folder selectById(int id) throws Exception;
}
