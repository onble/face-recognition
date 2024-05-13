package com.all.faceRecognition.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 会议信息实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingInf {
    private int meetingId; // 会议id
    private String title; // 会议名称
    private String address; // 会议地址
    private String content; // 会议内容
    private int staffId; // 创建人id
    private String name; // 创建人名称
    private Date startTime; // 开始时间
    private Date stopTime; // 结束时间
    private int status; // 会议状态
}
