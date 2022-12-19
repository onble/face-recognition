package com.all.officeSystem.mapper;

import com.all.officeSystem.bean.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PostMapper {
    // 根据id获取职位信息
    @Select("select * from post where id=#{id}")
    Post selectById(int id) throws Exception;

    // TODO: GJY 写获取全部职务信息，最好写上分页功能
    @Select("select * from Post ")
    List<Post> selectAll() throws Exception;

    // GJY： 修改职务信息
    @Update("update * set Post ")
    void updateMoneyById(int id) throws Exception;

}
