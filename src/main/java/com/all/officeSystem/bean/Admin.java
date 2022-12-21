package com.all.officeSystem.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    private int id;//账号
    private String account;//用户名
    private String name; // 姓名
    private boolean gender; // 性别
    private int age; // 年龄
    private String password;//密码

}
