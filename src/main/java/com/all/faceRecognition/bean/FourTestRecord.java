package com.all.faceRecognition.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FourTestRecord {
    private List<Integer> action;
    private int testGroupId;
    private List<Test> tests;

    // Inner class for Test
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Test {
        private String image;
        private boolean answer;
        private String name;
        private int id;
    }
}
