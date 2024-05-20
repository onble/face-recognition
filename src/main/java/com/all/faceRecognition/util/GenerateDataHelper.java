package com.all.faceRecognition.util;

import java.util.HashMap;
import java.util.Random;

public class GenerateDataHelper {
    public static final HashMap<String, Integer> femaleCharacters = new HashMap<String, Integer>() {{
        put("Angelagbaby", 8);
        put("迪丽热巴", 36);
        put("范冰冰", 9);
        put("杨幂", 46);
        put("赵丽颖", 9);
    }};

    public static final HashMap<String, Integer> maleCharacters = new HashMap<String, Integer>() {{
        put("李晨", 12);
        put("鹿晗", 16);
        put("郑恺", 20);
    }};

    public static HashMap<String, Integer> getRandomCharacters(HashMap<String, Integer> femaleCharacters, HashMap<String, Integer> maleCharacters) {
        Random random = new Random();
        if (random.nextBoolean()) {
            return femaleCharacters;
        } else {
            return maleCharacters;
        }
    }
}
