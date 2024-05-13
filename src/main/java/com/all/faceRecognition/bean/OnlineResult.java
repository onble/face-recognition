package com.all.faceRecognition.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 在线信息结果的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineResult {

    private String name; // 职员名称
    private String positionName; // 职务名称
    private String departmentName; // 所属部门名称
    private String onlineStatus; // 在线状态
}
