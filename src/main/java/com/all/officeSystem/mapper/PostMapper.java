package com.all.officeSystem.mapper;

import com.all.officeSystem.bean.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
    // 根据id获取职位信息
    @Select("select * from post where id=#{id}")
    Post selectById(int id) throws Exception;

     //获取全部职务信息
    @Select("select * from Post ")
    List<Post> selectAll() throws Exception;

    // 修改职务信息
    @Update("update * set Post ")
    void updateMoneyById(int id) throws Exception;

    //根据id删除数据
    @Delete("delete from post where id=#{id}")
    void deleteById(int id) throws Exception;

    // 插入数据
    @Insert("insert into post value (default,#{name},#{duty})")
    void insert( String name,String duty)throws Exception;

    // 修改数据
    @Update("update post set name=#{name},duty=#{duty}")
    void change(String name, String duty) throws Exception;

    // 获取数据数量
    @Select("select count(id) from post where id =#{id}")
    int getNum(int id) throws Exception;
}
