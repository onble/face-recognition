package com.all.officeSystem.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 文件夹信息的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FolderInf {
    private int id; //文件id
    private String name; // 文件名
    private int staffId; // 所属职工id
    private Date uploadTime; //上传时间
    private double size; //文件大小
}
