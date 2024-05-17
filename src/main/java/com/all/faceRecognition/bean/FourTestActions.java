package com.all.faceRecognition.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FourTestActions {
    private Integer id;//操作对应的id
    private Integer action1;
    private Integer action2;
    private Integer action3;
    private Integer action4;
}
