package com.all.officeSystem.service;

import com.all.officeSystem.common.R;
import org.springframework.stereotype.Service;

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
}
