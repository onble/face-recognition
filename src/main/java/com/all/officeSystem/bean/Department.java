package com.all.officeSystem.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门信息实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private int id; // 部门id
    private String name; // 部门名称
    private String homePage; // 部门主页


}
