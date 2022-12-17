package com.all.officeSystem.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {

    private int id; // 职工id
    private String account; // 账号
    private String password; // 密码
    private int status; // 账号状态
}
