package com.all.officeSystem.mapper;

import com.all.officeSystem.bean.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
