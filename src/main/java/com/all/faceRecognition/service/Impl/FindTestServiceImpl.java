package com.all.faceRecognition.service.Impl;

import com.all.faceRecognition.bean.ClassificationTestInfo;
import com.all.faceRecognition.bean.TestBaseInfo;
import com.all.faceRecognition.bean.get.findTest.FindTestInfo;
import com.all.faceRecognition.bean.save.FindTestAction;
import com.all.faceRecognition.bean.save.SaveFindTestInfo;
import com.all.faceRecognition.mapper.PeopleBaseInfoMapper;
import com.all.faceRecognition.mapper.TestBaseInfoMapper;
import com.all.faceRecognition.mapper.UserTestMapper;
import com.all.faceRecognition.mapper.findTest.FindTestActionMapper;
import com.all.faceRecognition.mapper.findTest.FindTestInfoMapper;
import com.all.faceRecognition.service.FindTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.all.faceRecognition.util.GenerateDataHelper.femaleCharacters;
import static com.all.faceRecognition.util.GenerateDataHelper.maleCharacters;
import static com.all.faceRecognition.util.TimeUtils.getCurrentChinaTime;

@Service
public class FindTestServiceImpl implements FindTestService {
    @Autowired
    private PeopleBaseInfoMapper peopleBaseInfoMapper;
    @Autowired
    private TestBaseInfoMapper testBaseInfoMapper;
    @Autowired
    private FindTestActionMapper findTestActionMapper;
    @Autowired
    private UserTestMapper userTestMapper;
    @Autowired
    private FindTestInfoMapper findTestInfoMapper;

    public int insertNewTest(int peopleId, String imageIndex) throws Exception {
        TestBaseInfo testBaseInfo = new TestBaseInfo();
        testBaseInfo.setPeople_id(peopleId);
        testBaseInfo.setImageIndex(imageIndex);
        testBaseInfoMapper.insertNewTest(testBaseInfo);
        return testBaseInfo.getId();
    }

    @Override
    public HashMap<String, Object> get_test() throws Exception {
        // 定义一个题组对象记录生成的题组
        FindTestInfo findTestInfo = new FindTestInfo();
        HashMap<String, Integer> characters = Math.random() < 0.5 ? femaleCharacters : maleCharacters;
        List<String> personList = new ArrayList<>(characters.keySet());
        Random random = new Random();
        String person1 = personList.get(random.nextInt(personList.size()));
        Integer person1Id = peopleBaseInfoMapper.selectIdByPeopleName(person1);
        int imagesNumber1 = characters.get(person1);
        List<Integer> imagesIndex1 = new ArrayList<>();
        for (int i = 1; i <= imagesNumber1; i++) {
            imagesIndex1.add(i);
        }
        Collections.shuffle(imagesIndex1);
        HashMap<String, Object> data = new HashMap<>();
        HashMap<String, Object> entry = new HashMap<>();
        entry.put("id", person1Id);
        String image_index = "public/face/" + person1 + "/" + imagesIndex1.get(0) + ".jpg";
        entry.put("image", image_index);
        entry.put("name", person1);
        data.put("target", entry);
        // 根据图片去获取题目id
        Integer testId = testBaseInfoMapper.selectTestIdByImageIndex(image_index);
        if (testId == null) {
            // 去增加一个题目
            testId = insertNewTest(person1Id, image_index);
        }
        findTestInfo.setTargetId(testId);
        entry = new HashMap<>();
        List<HashMap<String, Object>> tests = new ArrayList<>();
        List<String> images = new ArrayList<>();
        // 随机抽取一个位置
        int answer_index = random.nextInt(8);
        for (int i = 0; i < 8; i++) {
            entry = new HashMap<>();
            if (answer_index == i) {
                image_index = "public/face/" + person1 + "/" + imagesIndex1.get(1) + ".jpg";
                entry.put("id", person1Id);
                entry.put("image", image_index);
                entry.put("name", person1);
                entry.put("answer", true);
                tests.add(entry);
                // 根据图片去获取题目id
                testId = testBaseInfoMapper.selectTestIdByImageIndex(image_index);
                if (testId == null) {
                    // 去增加一个题目
                    testId = insertNewTest(person1Id, image_index);
                }
                findTestInfo.save_id(testId);
                continue;
            }
            // 随机抽取一个新的人物
            String new_person = personList.get(random.nextInt(personList.size()));
            while (Objects.equals(new_person, person1)) {
                new_person = personList.get(random.nextInt(personList.size()));
            }
            // 生成图片地址
            image_index = "public/face/" + new_person + "/" + (random.nextInt(characters.get(new_person)) + 1) + ".jpg";
            while (images.contains(image_index)) {
                image_index = "public/face/" + new_person + "/" + (random.nextInt(characters.get(new_person)) + 1) + ".jpg";
            }
            images.add(image_index);
            // 根据图片去获取题目id
            testId = testBaseInfoMapper.selectTestIdByImageIndex(image_index);
            if (testId == null) {
                // 去增加一个题目
                testId = insertNewTest(person1Id, image_index);
            }
            entry.put("id", testId);
            findTestInfo.save_id(testId);
            entry.put("image", image_index);
            entry.put("name", new_person);
            entry.put("answer", false);
            tests.add(entry);
        }
        // 去数据库中找是否存在这个题型
        Integer test_group_id = findTestInfoMapper.selectFindTestGroupIdByFindTestInfo(findTestInfo);
        if (test_group_id == null) {
            // 新增一个题组
            findTestInfoMapper.insertNewTestGroup(findTestInfo);
            test_group_id = findTestInfo.getId();
        }
        data.put("group_id", test_group_id);

        data.put("tests", tests);

        return data;
    }

    @Override
    public void saveRecords(SaveFindTestInfo saveFindTestInfo, int user_id) throws Exception {
        System.out.println("saveFindTestInfo = " + saveFindTestInfo);
        // 将发来的数据中提取出来操作
        FindTestAction findTestAction = new FindTestAction();
        List<SaveFindTestInfo.TestWithAnswerChoose> tests = saveFindTestInfo.getTests();
        System.out.println("tests = " + tests);
        for (SaveFindTestInfo.TestWithAnswerChoose test : tests) {
            if (test.getChoose() == null) {
                findTestAction.save_action(0);
            } else if (test.getChoose() <= 0) {
                findTestAction.save_action(-test.getId());
            } else if (test.getChoose() > 0) {
                findTestAction.save_action(test.getId());
            }
        }
        Integer actionId = findTestActionMapper.selectActionIdByActionClass(findTestAction);
        if (actionId == null) {
            findTestActionMapper.insertNewTestGroup(findTestAction);
            actionId = findTestAction.getId();
        }
        // 存储整体的进度
        userTestMapper.insert(saveFindTestInfo.getGroup_id(), getCurrentChinaTime(), 3, saveFindTestInfo.getAnswerSeconds(), user_id, actionId);

    }
}
