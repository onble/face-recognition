package com.all.faceRecognition.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTest {
    Integer id;
    Integer testGroupId;
    LocalDateTime doneTime;
    int groupKind;
    int timeSpendSeconds;
    Integer userId;
    Integer testActionId;
}
