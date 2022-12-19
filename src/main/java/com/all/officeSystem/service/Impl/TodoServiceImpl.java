package com.all.officeSystem.service.Impl;

import com.all.officeSystem.bean.Todo;
import com.all.officeSystem.common.R;
import com.all.officeSystem.mapper.TodoMapper;
import com.all.officeSystem.service.TodoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoMapper todoMapper;

    @Override
    public R getTodoInfByPageByStaffId(int page, int items, int staffId) throws Exception {
        // 初始化分页信息
        PageHelper.startPage(page, items);
        // 查询全部数据
        List<Todo> todos = todoMapper.selectByStaffId(staffId);
        // 借助分页助手获取分页信息
        PageInfo<Todo> pageInfo = new PageInfo<>(todos);
        return R.ok().setData("todo_inf", pageInfo.getList()).setData("pages", pageInfo.getPages());
    }

    @Override
    public R deleteById(int id) {
        try {
            todoMapper.deleteById(id);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    @Override
    public void batchDelete(int[] ids) throws Exception {
        for (int id : ids) {
            todoMapper.deleteById(id);
        }
    }

    @Override
    public void insert(int staffId, String title, String content, boolean status) throws Exception {
        todoMapper.insert(staffId, title, content, status);
    }

    @Override
    public Todo selectById(int id) throws Exception {

        return todoMapper.selectById(id);

    }
}
