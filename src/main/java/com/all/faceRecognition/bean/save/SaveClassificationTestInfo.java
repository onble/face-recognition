package com.all.faceRecognition.bean.save;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveClassificationTestInfo {
    private int group_id;
    @JsonProperty("A")
    private Test A;
    @JsonProperty("B")
    private Test B;
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
        private boolean choose;
    }
}
