package com.all.officeSystem.controller;

import com.all.officeSystem.bean.StaffInf;
import com.all.officeSystem.common.R;
import com.all.officeSystem.service.StaffInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 员工管理控制器
 */
@RestController
public class StaffInfController {
//重新推送
    @Autowired
    private StaffInfService staffInfService;

    // 根据分页信息查询数据
    @PostMapping("/staffInf/getList")
    public R getListByPage(int page, int items){
        try {
            return staffInfService.getStaffInfByPage(page, items);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    // 根据id删除数据
    @PostMapping("/staffInf/delete")
    public R deleteById(int id) {
        try {
            staffInfService.deleteById(id);
            return R.ok().setData("info", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setData("info", "删除失败");
        }
    }

    // 根据id批量删除
    @DeleteMapping("/staffInf/deletes")
    public R batchDelete(int[] ids) {
        try {
            staffInfService.batchDelete(ids);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    // 添加
    @PostMapping("/staffInf/add")
    public R add(StaffInf th) {
        try {
            staffInfService.insert(th);
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }

}
