package com.all.faceRecognition.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 职务信息实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private int id; // 职务id
    private String name; // 职务名称
    private String duty; // 职务职责
}
