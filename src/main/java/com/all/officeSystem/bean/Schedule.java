package com.all.officeSystem.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 日程实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    private int scheduleId; // 日程id
    private String title; // 日程标题
    // 接收数据的格式注解
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date; // 预定日程日期
    private String content; // 日程内容
    private int staffId; //职工id
}
