package com.all.faceRecognition.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 文件的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Folder {
    private int id; //文件id
    private String path; // 文件存放路径
    private int staffId; // 所属职工id
    private Date uploadTime; //上传时间
    private String name; // 文件名
    private double size; //文件大小
}
