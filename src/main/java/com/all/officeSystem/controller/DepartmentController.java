package com.all.officeSystem.controller;

import com.all.officeSystem.common.R;
import com.all.officeSystem.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 部门的控制器
 */
@RestController
public class DepartmentController {
    // TODO: GJY 参考 MeetingController
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/department/getList")
    public R getListByPage(int page, int items){
        try {
            return departmentService.getDepartmentInfByPage(page, items);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
