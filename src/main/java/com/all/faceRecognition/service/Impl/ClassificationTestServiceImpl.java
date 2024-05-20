package com.all.faceRecognition.service.Impl;

import com.all.faceRecognition.bean.ClassificationTestInfo;
import com.all.faceRecognition.bean.TestBaseInfo;
import com.all.faceRecognition.bean.save.ClassificationTestAction;
import com.all.faceRecognition.bean.save.SaveClassificationTestInfo;
import com.all.faceRecognition.mapper.*;
import com.all.faceRecognition.service.ClassificationTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.all.faceRecognition.util.GenerateDataHelper.femaleCharacters;
import static com.all.faceRecognition.util.GenerateDataHelper.maleCharacters;
import static com.all.faceRecognition.util.TimeUtils.getCurrentChinaTime;

@Service
public class ClassificationTestServiceImpl implements ClassificationTestService {
    @Autowired
    private PeopleBaseInfoMapper peopleBaseInfoMapper;
    @Autowired
    private TestBaseInfoMapper testBaseInfoMapper;
    @Autowired
    private ClassificationTestInfoMapper classificationTestInfoMapper;
    @Autowired
    private ClassificationTestActionMapper classificationTestActionMapper;
    @Autowired
    private UserTestMapper userTestMapper;

    public int insertNewTest(int peopleId, String imageIndex) throws Exception {
        TestBaseInfo testBaseInfo = new TestBaseInfo();
        testBaseInfo.setPeople_id(peopleId);
        testBaseInfo.setImageIndex(imageIndex);
        testBaseInfoMapper.insertNewTest(testBaseInfo);
        return testBaseInfo.getId();
    }

    @Override
    public HashMap<String, Object> get_test() throws Exception {
        HashMap<String, Integer> characters = Math.random() < 0.5 ? femaleCharacters : maleCharacters;
        List<String> personList = new ArrayList<>(characters.keySet());
        Random random = new Random();
        String personA = personList.get(random.nextInt(personList.size()));
        String personB = personList.get(random.nextInt(personList.size()));
        while (personA.equals(personB)) {
            personB = personList.get(random.nextInt(personList.size()));
        }
        Integer personAId = peopleBaseInfoMapper.selectIdByPeopleName(personA);
        Integer personBId = peopleBaseInfoMapper.selectIdByPeopleName(personB);

        int AimagesNumber = characters.get(personA);
        List<Integer> AimagesIndex = new ArrayList<>();
        for (int i = 1; i <= AimagesNumber; i++) {
            AimagesIndex.add(i);
        }
        Collections.shuffle(AimagesIndex);

        int BimagesNumber = characters.get(personB);
        List<Integer> BimagesIndex = new ArrayList<>();
        for (int i = 1; i <= BimagesNumber; i++) {
            BimagesIndex.add(i);
        }
        Collections.shuffle(BimagesIndex);

        List<Integer> testAnswer = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 2, 2, 2, 2, 2));
        Collections.shuffle(testAnswer);

        HashMap<String, Object> data = new HashMap<>();

        // 定义一个题组对象记录生成的题组
        ClassificationTestInfo classificationTestInfo = new ClassificationTestInfo();

        // 根据图片去获取题目id
        String A_imagePath = "public/face/" + personA + "/" + AimagesIndex.get(0) + ".jpg";
        Integer A_image_id = testBaseInfoMapper.selectTestIdByImageIndex(A_imagePath);
        if (A_image_id == null) {
            // 去新增一个题目
            A_image_id = insertNewTest(personAId, A_imagePath);
        }
        classificationTestInfo.setAId(A_image_id);
        HashMap<String, Object> entry = new HashMap<>();
        entry.put("id", A_image_id);
        entry.put("image", A_imagePath);
        entry.put("name", personA);
        data.put("A", entry);
        entry = new HashMap<>();
        String B_imagePath = "public/face/" + personB + "/" + BimagesIndex.get(0) + ".jpg";
        Integer B_image_id = testBaseInfoMapper.selectTestIdByImageIndex(B_imagePath);
        if (B_image_id == null) {
            // 去新增一个题目
            B_image_id = insertNewTest(personBId, B_imagePath);
        }
        classificationTestInfo.setBId(B_image_id);
        entry.put("id", B_image_id);
        entry.put("image", B_imagePath);
        entry.put("name", personB);
        data.put("B", entry);

        int A_image_index = 1;
        int B_image_index = 1;

        List<HashMap<String, Object>> test = new ArrayList<>();


        for (Integer choose : testAnswer) {
            entry = new HashMap<>();
            if (choose == 1) {
                A_imagePath = "public/face/" + personA + "/" + AimagesIndex.get(A_image_index) + ".jpg";
                A_image_index++;
                A_image_id = testBaseInfoMapper.selectTestIdByImageIndex(A_imagePath);
                if (A_image_id == null) {
                    // 去新增一个题目
                    A_image_id = insertNewTest(personAId, A_imagePath);
                }
                classificationTestInfo.save_id(A_image_id);
                entry.put("id", A_image_id);
                entry.put("image", A_imagePath);
                entry.put("name", personA);
                entry.put("answer", "A");
                test.add(entry);
            } else if (choose == 2) {
                B_imagePath = "public/face/" + personB + "/" + BimagesIndex.get(B_image_index) + ".jpg";
                B_image_index++;
                B_image_id = testBaseInfoMapper.selectTestIdByImageIndex(B_imagePath);
                if (B_image_id == null) {
                    // 去新增一个题目
                    B_image_id = insertNewTest(personBId, B_imagePath);
                }
                classificationTestInfo.save_id(B_image_id);
                entry.put("id", B_image_id);
                entry.put("image", B_imagePath);
                entry.put("name", personB);
                entry.put("answer", "B");
                test.add(entry);
            }
        }
        data.put("tests", test);
        // 去数据库中找是否存在这个题型
        Integer test_group_id = classificationTestInfoMapper.selectClassificationTestGroupIdByClassificationTestInfo(classificationTestInfo);
        if (test_group_id == null) {
            // 新增一个题组
            classificationTestInfoMapper.insertNewTestGroup(classificationTestInfo);
            test_group_id = classificationTestInfo.getId();
        }
        data.put("group_id", test_group_id);

        return data;
    }

    @Override
    public void saveRecords(SaveClassificationTestInfo saveClassificationTestInfo, int user_id) throws Exception {
        // 将发来的数据中提取出来操作
        ClassificationTestAction classificationTestAction = new ClassificationTestAction();
        List<SaveClassificationTestInfo.TestWithAnswerChoose> tests = saveClassificationTestInfo.getTests();
        for (SaveClassificationTestInfo.TestWithAnswerChoose test : tests) {
            if (test.isChoose()) {
                classificationTestAction.save_action(test.getId());
            } else {
                classificationTestAction.save_action(-test.getId());
            }
        }
        Integer actionId = classificationTestActionMapper.selectActionIdByActionClass(classificationTestAction);
        if (actionId == null) {
            classificationTestActionMapper.insertNewTestGroup(classificationTestAction);
            actionId = classificationTestAction.getId();
        }
        // 存储整体的进度
        userTestMapper.insert(saveClassificationTestInfo.getGroup_id(), getCurrentChinaTime(), 2, saveClassificationTestInfo.getAnswerSeconds(), user_id, actionId);

    }
}
