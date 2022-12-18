package com.all.officeSystem.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 待办事项的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

    private int id; // 待办事项id
    private int staffId; // 职工id
    private String title; // 事项标题
    private String content; // 事项内容
    private boolean status; // 事项状态
}
