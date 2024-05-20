package com.all.faceRecognition.bean.get.findTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindTestInfo {
    private int id; // 题组id
    private int targetId;// 人物A
    private int test1Id;//题目1的id
    private int test2Id;//题目2的id
    private int test3Id;//题目3的id
    private int test4Id;//题目4的id
    private int test5Id;//题目5的id
    private int test6Id;//题目6的id
    private int test7Id;//题目7的id
    private int test8Id;//题目8的id
    private int status;//状态

    public void save_id(int id) {
        if (this.test1Id == 0) {
            this.test1Id = id;
        } else if (this.test2Id == 0) {
            this.test2Id = id;
        } else if (this.test3Id == 0) {
            this.test3Id = id;
        } else if (this.test4Id == 0) {
            this.test4Id = id;
        } else if (this.test5Id == 0) {
            this.test5Id = id;
        } else if (this.test6Id == 0) {
            this.test6Id = id;
        } else if (this.test7Id == 0) {
            this.test7Id = id;
        } else if (this.test8Id == 0) {
            this.test8Id = id;
        }
    }
}
