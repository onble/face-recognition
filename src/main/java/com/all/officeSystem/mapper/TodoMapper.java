package com.all.officeSystem.mapper;

import com.all.officeSystem.bean.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 待办事项的实体类
 */
@Mapper
public interface TodoMapper {
    // 根据id获取全部信息
    @Select("select * from todo where staff_id=#{staffId}")
    Todo selectByStaffId(int staffId) throws Exception;
}
