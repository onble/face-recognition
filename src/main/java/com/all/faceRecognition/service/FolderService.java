package com.all.faceRecognition.service;

import com.all.faceRecognition.common.R;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 文件的业务层
 */
@Service
public interface FolderService {
    // 根据职员id和页码获取文件列表
    R getFolderInfByStaffIdByPage(int staffId, int page, int items) throws Exception;

    // 根据id删除数据
    void deleteById(int id) throws Exception;

    // 根据id批量删除数据
    void batchDelete(int[] ids) throws Exception;

    // 插入数据
    void insert(String path, int staffId, Date uploadTime, String name, long size) throws Exception;
}
