package com.all.officeSystem.controller;

import com.all.officeSystem.bean.StaffInf;
import com.all.officeSystem.common.R;
import com.all.officeSystem.service.StaffInfService;
import com.all.officeSystem.util.QiniuUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * 员工管理控制器
 */
@Slf4j
@RestController
public class StaffInfController {
    //重新推送
    @Autowired
    private StaffInfService staffInfService;

    // 根据分页信息查询数据
    @PostMapping("/staffInf/getList")
    public R getListByPage(int page, int items) {
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

    // 根据id获取一条信息
    @PostMapping("/staffInf/get")
    public R get(int staffId) {
        try {
            StaffInf staffInf = staffInfService.selectById(staffId);
            return R.ok().setData("staff_inf", staffInf);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    // 职员修改个人信息
    @PostMapping("/staff_inf/change")
    public R changeByStaff(int staffId, String name, int age, String phone, boolean gender) {
        try {
            staffInfService.changeByStaff(staffId, name, age, phone, gender);
            return R.ok().setData("info", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setData("info", "修改失败");
        }
    }

    // 上传头像
    @PostMapping("/staff_inf/headerFile")
    public R changeHeaderFile(int staffId, MultipartFile headerFile) {
        try {
            String filename = QiniuUtils.upload(headerFile);
            // 将文件名字存入数据库
            filename = "http://" + filename;
            log.debug("上传后的文件名:filename = " + filename);
            staffInfService.changeHeaderFile(staffId, filename);
            return R.ok().setData("image_path", filename);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    // 在职工index页面获取登录所需信息
    @PostMapping("/staff_inf/index_inf")
    public R indexInf(int staffId) {
        try {
            StaffInf staffInf = staffInfService.selectById(staffId);
            return R.ok().setData("staff_inf", staffInf);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
