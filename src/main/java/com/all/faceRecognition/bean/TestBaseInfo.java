package com.all.faceRecognition.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestBaseInfo {
    private int id; // 题目id
    private int people_id;// 人物id
    private String imageIndex;// 图片存储路径
    private int status;// 状态
    private int wrong_times;// 错误的次数
    private int right_times;// 做对的次数
}
