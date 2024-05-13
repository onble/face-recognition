package com.all.faceRecognition.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 在线信息的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Online {

    private int id; // 职员id
    private String status; // 在线状态
}
