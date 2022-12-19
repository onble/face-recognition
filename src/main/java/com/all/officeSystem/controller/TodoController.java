package com.all.officeSystem.controller;

import com.all.officeSystem.bean.StaffInf;
import com.all.officeSystem.bean.Todo;
import com.all.officeSystem.common.R;
import com.all.officeSystem.service.TodoService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 待办事项的控制器
 */
@RestController
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping("/todo/getList")
    public R getListByPageByStaffId(int page, int items, int staffId) {
        try {
            return todoService.getTodoInfByPageByStaffId(page, items, staffId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    // 根据待办事项id删除数据
    @DeleteMapping("/todo/delete")
    public R deleteById(int id) {
        try {
            todoService.deleteById(id);
            return R.ok().setData("info", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setData("info", "删除失败");
        }
    }

    // 根据id批量删除
    @DeleteMapping("/todo/deletes")
    public R batchDelete(int[] ids) {
        try {
            todoService.batchDelete(ids);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    // 添加
    @PostMapping("/todo/add")
    public R add(int staffId, String title, String content, boolean status) {
        try {
            todoService.insert(staffId, title, content, status);
            return R.ok().setData("info", "插入成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setData("info", "插入失败");
        }
    }

    // 根据id获取信息
    @PostMapping("/todo/get")
    public R get(int id) {

        try {
            Todo todo = todoService.selectById(id);
            return R.ok().setData("todo_inf", todo);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }

    }
}
