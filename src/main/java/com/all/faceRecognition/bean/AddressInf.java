package com.all.faceRecognition.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通讯录信息实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressInf {
    private int id; // 职工id
    private String name; // 姓名
    private String post; // 职务名称
    private String department; // 部门名称
    private String phone; // 电话号码
    private boolean gender; // 性别
}
