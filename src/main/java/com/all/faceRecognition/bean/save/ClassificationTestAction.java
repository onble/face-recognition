package com.all.faceRecognition.bean.save;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassificationTestAction {
    private Integer id;//操作对应的id
    private Integer action1 = 0;
    private Integer action2 = 0;
    private Integer action3 = 0;
    private Integer action4 = 0;
    private Integer action5 = 0;
    private Integer action6 = 0;
    private Integer action7 = 0;
    private Integer action8 = 0;
    private Integer action9 = 0;
    private Integer action10 = 0;

    public void save_action(Integer action) throws Exception {
        if (this.action1 == 0) {
            this.action1 = action;
        } else if (this.action2 == 0) {
            this.action2 = action;
        } else if (this.action3 == 0) {
            this.action3 = action;
        } else if (this.action4 == 0) {
            this.action4 = action;
        } else if (this.action5 == 0) {
            this.action5 = action;
        } else if (this.action6 == 0) {
            this.action6 = action;
        } else if (this.action7 == 0) {
            this.action7 = action;
        } else if (this.action8 == 0) {
            this.action8 = action;
        } else if (this.action9 == 0) {
            this.action9 = action;
        } else if (this.action10 == 0) {
            this.action10 = action;
        } else {
            throw new Exception("ClassificationTestAction 记录数据出错");
        }
    }
}
