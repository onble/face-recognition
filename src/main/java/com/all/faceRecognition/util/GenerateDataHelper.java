package com.all.faceRecognition.util;

import java.util.HashMap;
import java.util.Random;

public class GenerateDataHelper {
    public static final HashMap<String, Integer> femaleCharacters = new HashMap<String, Integer>() {{
        put("Angelagbaby", 164);
//        put("蔡文静",103);
        put("迪丽热巴", 104);
        put("范冰冰", 102);
        put("古力娜扎", 107);
        put("杨幂", 196);
        put("张子枫", 103);
        put("赵今麦", 103);
        put("赵丽颖", 100);
    }};

    public static final HashMap<String, Integer> maleCharacters = new HashMap<String, Integer>() {{
//        put("蔡徐坤",95);
        put("陈立农", 101);
        put("李晨", 119);
//        put("鹿晗", 210);
        put("王俊凯", 100);
        put("王一博", 100);
//        put("肖战", 70);
        put("许光汉", 84);
        put("薛之谦", 104);
        put("张艺兴", 100);
        put("郑恺", 54);
        put("朱一龙", 100);
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
