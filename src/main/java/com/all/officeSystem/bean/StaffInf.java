package com.all.officeSystem.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 个人信息实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffInf {

    private int staffId; // 职工id
    private String name; // 职工姓名
    private int age; // 年龄
    private String phone; // 电话
    private boolean gender; // 性别 男1女0
    private boolean isLeader; // 是否为部门领导
    private int positionId; // 职务id
    private double monthlySalary; // 月工资
    private int departmentId; // 所属部门Id
    private Date birthday; // 生日
    private double folderSize; // 文件大小设置
    private double occupyFileSize; // 已使用文件大小
}
