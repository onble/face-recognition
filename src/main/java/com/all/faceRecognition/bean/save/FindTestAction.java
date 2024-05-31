package com.all.faceRecognition.bean.save;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindTestAction {
    private Integer id;//操作对应的id
    private Integer action1 = null;
    private Integer action2 = null;
    private Integer action3 = null;
    private Integer action4 = null;
    private Integer action5 = null;
    private Integer action6 = null;
    private Integer action7 = null;
    private Integer action8 = null;

    public void save_action(Integer action) throws Exception {
        if (this.action1 == null) {
            this.action1 = action;
        } else if (this.action2 == null) {
            this.action2 = action;
        } else if (this.action3 == null) {
            this.action3 = action;
        } else if (this.action4 == null) {
            this.action4 = action;
        } else if (this.action5 == null) {
            this.action5 = action;
        } else if (this.action6 == null) {
            this.action6 = action;
        } else if (this.action7 == null) {
            this.action7 = action;
        } else if (this.action8 == null) {
            this.action8 = action;
        } else {
            throw new Exception("FindTestAction 记录数据出错");
        }
    }
}
