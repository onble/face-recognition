package com.all.faceRecognition.service.Impl;

import com.all.faceRecognition.bean.TestBaseInfo;
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
        entry.put("image", "public/face/" + person1 + "/" + imagesIndex1.get(0) + ".jpg");
        entry.put("name", person1);
        data.put("target", entry);
        entry = new HashMap<>();
        List<HashMap<String, Object>> tests = new ArrayList<>();
        List<String> images = new ArrayList<>();
        // TODO:没有groupId
        for (int i = 0; i < 7; i++) {
            // 随机抽取一个新的人物
            String new_person = personList.get(random.nextInt(personList.size()));
            while (Objects.equals(new_person, person1)) {
                new_person = personList.get(random.nextInt(personList.size()));
            }
            // 生成图片地址
            String image_index = "public/face/" + new_person + "/" + (random.nextInt(characters.get(new_person)) + 1) + ".jpg";
            while (images.contains(image_index)) {
                image_index = "public/face/" + new_person + "/" + (random.nextInt(characters.get(new_person)) + 1) + ".jpg";
            }
            images.add(image_index);
            // 根据图片去获取题目id
            Integer testId = testBaseInfoMapper.selectTestIdByImageIndex(image_index);
            if (testId == null) {
                // 去增加一个题目
                testId = insertNewTest(person1Id, image_index);
            }
            entry.put("id", testId);
            entry.put("image", image_index);
            entry.put("name", new_person);
            entry.put("answer", false);
            tests.add(entry);
            entry = new HashMap<>();
        }
        tests.add(new HashMap<String, Object>() {{
            put("id", person1Id);
            put("image", "public/face/" + person1 + "/" + imagesIndex1.get(1) + ".jpg");
            put("name", person1);
            put("answer", true);
        }});
        Collections.shuffle(tests);
        data.put("tests", tests);

        return data;
    }

    @Override
    public void saveRecords(SaveFindTestInfo saveFindTestInfo, int user_id) throws Exception {
        // 将发来的数据中提取出来操作
        FindTestAction findTestAction = new FindTestAction();
        List<SaveFindTestInfo.TestWithAnswerChoose> tests = saveFindTestInfo.getTests();
        for (SaveFindTestInfo.TestWithAnswerChoose test : tests) {
            if (test.getChoose() == null || test.getChoose() <= 0) {
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
