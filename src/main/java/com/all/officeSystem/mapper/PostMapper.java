package com.all.officeSystem.mapper;

import com.all.officeSystem.bean.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PostMapper {
    // 根据id获取职位信息
    @Select("select * from post where id=#{id}")
    Post selectById(int id) throws Exception;
}
