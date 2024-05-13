package com.all.faceRecognition.service.Impl;

import com.all.faceRecognition.bean.Folder;
import com.all.faceRecognition.bean.FolderInf;
import com.all.faceRecognition.common.R;
import com.all.faceRecognition.mapper.FolderInfMapper;
import com.all.faceRecognition.mapper.FolderMapper;
import com.all.faceRecognition.mapper.StaffInfMapper;
import com.all.faceRecognition.service.FolderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FolderServiceImpl implements FolderService {
    @Autowired
    private FolderInfMapper folderInfMapper;
    @Autowired
    private FolderMapper folderMapper;
    @Autowired
    private StaffInfMapper staffInfMapper;

    @Override
    public R getFolderInfByStaffIdByPage(int staffId, int page, int items) throws Exception {
        // 初始化分页信息
        PageHelper.startPage(page, items);
        // 查询全部数据
        List<FolderInf> folderInfs = folderInfMapper.selectByStaffId(staffId);
        // 借助分页助手获取分页信息
        PageInfo<FolderInf> pageInfo = new PageInfo<>(folderInfs);
        return R.ok().setData("folder_inf", pageInfo.getList()).setData("pages", pageInfo.getPages());
    }

    @Override
    public void deleteById(int id) throws Exception {
        // 将删除的空间大小给剩余空间
        // 获取文件信息
        Folder folder = folderMapper.selectById(id);
        // 更新职员已使用空间
        staffInfMapper.updateOccupyFileSizeBySizeByStaffId(folder.getStaffId(), folder.getSize());
        folderMapper.deleteById(id);
    }

    @Override
    public void batchDelete(int[] ids) throws Exception {
        for (int id : ids) {
            // 将删除的空间大小给剩余空间
            // 获取文件信息
            Folder folder = folderMapper.selectById(id);
            // 更新职员已使用空间
            if (folder != null) {
                staffInfMapper.updateOccupyFileSizeBySizeByStaffId(folder.getStaffId(), folder.getSize());
            }
            // TODO:很奇怪,不存在时候居然不报错
            folderMapper.deleteById(id);
        }
    }

    @Override
    public void insert(String path, int staffId, Date uploadTime, String name, long size) throws Exception {
        folderMapper.insert(path, staffId, uploadTime, name, size);
    }
}
