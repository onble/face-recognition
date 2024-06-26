package com.all.faceRecognition.controller;

import com.all.faceRecognition.bean.Department;
import com.all.faceRecognition.common.R;
import com.all.faceRecognition.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门的控制器
 */
@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    // 根据分页信息查询数据

    @PostMapping("/department/getList")
    public R getListByPage(int page, int items) {
        try {
            return departmentService.getDepartmentInfByPage(page, items);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    // 根据待办事项id删除数据
    @DeleteMapping("/department/delete")
    public R deleteById(int id) {
        try {
            departmentService.deleteById(id);
            return R.ok().setData("info", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setData("info", "删除失败");
        }
    }

    // 根据id批量删除
    @DeleteMapping("/department/deletes")
    public R batchDelete(int[] ids) {
        try {
            departmentService.batchDelete(ids);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    // 添加
    @PostMapping("/department/add")
    public R add(String name, String homePage) {
        try {
            departmentService.insert(name, homePage);
            return R.ok().setData("info", "插入成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setData("info", "插入失败");
        }
    }

    // 根据id获取信息
    @PostMapping("/department/get")
    public R get(int id) {

        try {
            Department department = departmentService.selectById(id);
            return R.ok().setData("department_inf", department);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }

    }

    // 修改数据
    @PostMapping("/department/change")
    public R change(int AdminId, int id, String name, String homePage) {
        try {
            departmentService.change(id, name, homePage);
            return R.ok().setData("info", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setData("info", "修改失败");
        }
    }

    /**
     * 根据页码和名字模糊查询
     * @param page 页码
     * @param items 数量
     * @param name 名字
     * @return R
     */
    @PostMapping("/department_list/getListWithName")
    public R getDepartmentListWithName(int page, int items,String name) {
        try {
            return departmentService.getDepartmentListByPageByName(page, items,name);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }


    // 获取数据数量
//    @PostMapping("/department/num")
//    public R getNum(int id) {
//        try {
//            int num = departmentService.getNum(id);
//            return R.ok().setData("department_inf", num);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return R.error();
//        }
//    }
}
