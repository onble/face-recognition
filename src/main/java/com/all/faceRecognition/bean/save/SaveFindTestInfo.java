package com.all.faceRecognition.bean.save;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveFindTestInfo {
    private int group_id;
    private Test target;
    private List<TestWithAnswerChoose> tests;
    private int answerSeconds;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Test {
        private String image;
        private String name;
        private int id;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TestWithAnswerChoose {
        private String image;
        private String name;
        private int id;
        private String answer;
        private Integer choose = null;
    }
}
