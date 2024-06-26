package com.all.faceRecognition.mapper;

import com.all.faceRecognition.bean.Todo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 待办事项的实体类
 */
@Mapper
public interface TodoMapper {
    // 根据职员id获取全部信息
    @Select("select * from todo where staff_id=#{staffId}")
    List<Todo> selectByStaffId(int staffId) throws Exception;

    //根据待办事项id删除数据
    @Delete("delete from todo where id=#{id}")
    void deleteById(int id) throws Exception;

    // 插入数据
    @Insert("insert into todo value (default,#{staffId},#{title},#{content},#{status})")
    void insert(int staffId, String title, String content, boolean status);

    // 根据id获取一条待办事项的信息
    @Select("select * from todo where id=#{id}")
    Todo selectById(int id) throws Exception;

    // 修改数据
    @Update("update todo set title=#{title},content=#{content},status=#{status} where id=#{todoId}")
    void change(int staffId, String title, String content, boolean status, int todoId) throws Exception;

    // 获取数据数量
    @Select("select count(id) from todo where staff_id =#{staffId}")
    int getNum(int staffId) throws Exception;
}
