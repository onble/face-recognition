package com.all.faceRecognition.service.Impl;

import com.all.faceRecognition.bean.FourTest;
import com.all.faceRecognition.bean.FourTestInfo;
import com.all.faceRecognition.bean.FourTestRecord;
import com.all.faceRecognition.common.R;
import com.all.faceRecognition.mapper.FourTestInfoMapper;
import com.all.faceRecognition.mapper.FourTestMapper;
import com.all.faceRecognition.mapper.PeopleBaseInfoMapper;
import com.all.faceRecognition.service.FourTestService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/*
 * 四选一业务层的实现
 * */
@Service
public class FourTestServiceImpl implements FourTestService {
    @Autowired
    private FourTestMapper fourTestMapper;
    @Autowired
    private PeopleBaseInfoMapper peopleBaseInfoMapper;
    @Autowired
    private FourTestInfoMapper fourTestInfoMapper;

    private static int ID = 0;

    private static final HashMap<String, Integer> femaleCharacters = new HashMap<String, Integer>() {{
        put("Angelagbaby", 8);
        put("迪丽热巴", 36);
        put("范冰冰", 9);
        put("杨幂", 46);
        put("赵丽颖", 9);
    }};

    private static final HashMap<String, Integer> maleCharacters = new HashMap<String, Integer>() {{
        put("李晨", 12);
        put("鹿晗", 16);
        put("郑恺", 20);
    }};

    private static int generateId() {
        ID++;
        return ID;
    }

    public int insertNewTest(int peopleId, String imageIndex) throws Exception {
        FourTest testBaseInfo = new FourTest();
        testBaseInfo.setPeople_id(peopleId);
        testBaseInfo.setImageIndex(imageIndex);
        fourTestMapper.insertNewTest(testBaseInfo);
        return testBaseInfo.getId();
    }

    public HashMap<String, Object> getData() throws Exception {
        HashMap<String, Object> data_wrap = new HashMap<>();
        HashMap<String, Integer> characters = Math.random() < 0.5 ? femaleCharacters : maleCharacters;

        List<String> personList = new ArrayList<>(characters.keySet());
        Random random = new Random();
        String person1 = personList.get(random.nextInt(personList.size()));
        String person2 = personList.get(random.nextInt(personList.size()));
        // 获取person1的id
        Integer person1Id = peopleBaseInfoMapper.selectIdByPeopleName(person1);
        Integer person2Id = peopleBaseInfoMapper.selectIdByPeopleName(person2);
        while (person2.equals(person1)) {
            person2 = personList.get(random.nextInt(personList.size()));
        }

        List<Map<String, Object>> data = new ArrayList<>();
        int imagesNumber = characters.get(person1);
        List<Integer> imagesIndex = new ArrayList<>();
        for (int i = 1; i <= imagesNumber; i++) {
            imagesIndex.add(i);
        }
        Collections.shuffle(imagesIndex);
        // 定义一个题组对象记录生成的题组
        FourTestInfo fourTestInfo = new FourTestInfo();
        for (int i = 0; i < 3; i++) {
            String imagePath = "public/face/" + person1 + "/" + imagesIndex.get(i) + ".jpg";
            HashMap<String, Object> entry = new HashMap<>();
            // 根据图片去获取题目id
            Integer testId = fourTestMapper.selectTestIdByImageIndex(imagePath);
            if (testId == null) {
                // 去增加一个题目
                testId = insertNewTest(person1Id, imagePath);
            }
            entry.put("id", testId);
            fourTestInfo.save_id(testId);
            entry.put("image", imagePath);
            entry.put("name", person1);
            entry.put("answer", false);
            data.add(entry);
        }

        imagesNumber = characters.get(person2);
        int imageIndex = imagesIndex.get(random.nextInt(imagesIndex.size()));
        String imagePath = "public/face/" + person2 + "/" + imageIndex + ".jpg";
        HashMap<String, Object> entry = new HashMap<>();
        // 根据图片去获取题目id
        Integer testId = fourTestMapper.selectTestIdByImageIndex(imagePath);
        if (testId == null) {
            // 去增加一个题目
            testId = insertNewTest(person2Id, imagePath);
        }
        entry.put("id", testId);
        fourTestInfo.save_id(testId);
        entry.put("image", imagePath);
        entry.put("name", person2);
        entry.put("answer", true);
        data.add(entry);


        // 存储题组数据
        // 判断是否存在该题组
        Integer test_group_id = fourTestInfoMapper.selectFourTestGroupIdByFourTestInfo(fourTestInfo);
        if (test_group_id == null) {
            // 新增一个题组
            fourTestInfoMapper.insertNewTestGroup(fourTestInfo);
            test_group_id = fourTestInfo.getId();
        }
        Collections.shuffle(data);
        data_wrap.put("tests", data);
        data_wrap.put("test_group_id", test_group_id);
        return data_wrap;
    }

    public List<HashMap<String, Object>> generateDatas(int num) throws Exception {
        List<HashMap<String, Object>> datas = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            datas.add(getData());
        }
        return datas;
    }

    // 获取题组数据
    public List<HashMap<String, Object>> get_test(int num) throws Exception {
        List<HashMap<String, Object>> data = generateDatas(num);
        return data;
    }

    public static HashMap<String, Integer> getRandomCharacters(HashMap<String, Integer> femaleCharacters, HashMap<String, Integer> maleCharacters) {
        Random random = new Random();
        if (random.nextBoolean()) {
            return femaleCharacters;
        } else {
            return maleCharacters;
        }
    }

    // 存储做题记录
    public void saveRecords(FourTestRecord testRequest) throws Exception {
        List<Integer> action = testRequest.getAction();
        //  遍历action增加做题记录
        for (Integer testId : action) {
            if (testId > 0) {
                // 做对记录增加
                fourTestMapper.addRightTimes(testId);
            } else {
                fourTestMapper.addWrongTimes(Math.abs(testId));
            }
        }
    }

}
