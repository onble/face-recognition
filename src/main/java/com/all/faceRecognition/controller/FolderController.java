package com.all.faceRecognition.controller;

import com.all.faceRecognition.common.R;
import com.all.faceRecognition.service.FolderService;
import com.all.faceRecognition.util.QiniuUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 文件的控制器
 */
@Slf4j
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

    // 上传文件
    @PostMapping("/folder/add")
    public R uploadFile(int staffId, MultipartFile file) {
        try {
            long size = file.getSize();
            String originalFilename = file.getOriginalFilename();
            log.debug("上传的文件大小 size = " + size);
            String filename = QiniuUtils.upload(file);
            filename = "http://" + filename;
            log.debug("上传的文件 filename = " + filename);
            // 获取当前时间
            Date date = new Date();
            folderService.insert(filename, staffId, date, originalFilename, size);
            return R.ok().setData("info", "插入成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().setData("info", "插入失败");
        }
    }
}
