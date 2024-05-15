package com.all.faceRecognition.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FourTestInfo {
    private int id; // 题组id
    private int test1_id;//题目1的id
    private int test2_id;//题目2的id
    private int test3_id;//题目3的id
    private int test4_id;//题目4的id
    private int status;//状态

    public void save_id(int id) {
        if (this.test1_id == 0) {
            this.test1_id = id;
        } else if (this.test2_id == 0) {
            this.test2_id = id;
        } else if (this.test3_id == 0) {
            this.test3_id = id;
        } else if (this.test4_id == 0) {
            this.test4_id = id;
        }
    }
}
