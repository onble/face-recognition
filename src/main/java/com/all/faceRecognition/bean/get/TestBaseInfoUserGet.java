package com.all.faceRecognition.bean.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestBaseInfoUserGet {
    private int id; // 题目id
    private int people_id;// 人物id
    private String imageIndex;// 图片存储路径
}
