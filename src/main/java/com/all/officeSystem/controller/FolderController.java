package com.all.officeSystem.controller;

import com.all.officeSystem.common.R;
import com.all.officeSystem.service.FolderService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件的控制器
 */
@RestController
public class FolderController {
    @Autowired
    private FolderService folderService;

    @PostMapping("/folder/getList")
    public R getListByStaffIdByPage(int staffId, int page, int items) {
        try {
            return folderService.getFolderInfByStaffIdByPage(staffId, page, items);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    // 根据id删除数据
    @PostMapping("/folder/delete")
    public R deleteById(int id) {
        try {
            folderService.deleteById(id);
            return R.ok().setData("info", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setData("info", "删除失败");
        }
    }

    // 根据id批量删除
    @DeleteMapping("/folder/deletes")
    public R batchDelete(int[] ids) {
        try {
            folderService.batchDelete(ids);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
