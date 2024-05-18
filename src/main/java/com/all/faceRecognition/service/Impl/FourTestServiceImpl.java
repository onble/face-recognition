package com.all.faceRecognition.service.Impl;

import com.all.faceRecognition.bean.FourTest;
import com.all.faceRecognition.bean.FourTestActions;
import com.all.faceRecognition.bean.FourTestInfo;
import com.all.faceRecognition.bean.FourTestRecord;
import com.all.faceRecognition.common.R;
import com.all.faceRecognition.mapper.*;
import com.all.faceRecognition.service.FourTestService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.all.faceRecognition.util.TimeUtils.getCurrentChinaTime;


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
    @Autowired
    private FourTestActionMapper fourTestActionMapper;
    @Autowired
    private UserTestMapper userTestMapper;

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
    public void saveRecords(FourTestRecord testRequest, int user_id) throws Exception {
        List<Integer> action = testRequest.getAction();
        // 存储 action_to_save 的结果
        List<Integer> actionToSave = new ArrayList<>();
        List<FourTestRecord.Test> tests = testRequest.getTests();

        //  遍历action增加做题记录
        for (Integer testId : action) {
            if (testId > 0) {
                // 做对记录增加
                fourTestMapper.addRightTimes(testId);
            } else {
                fourTestMapper.addWrongTimes(Math.abs(testId));
            }
        }
        // 遍历 action 列表
        for (Integer act : action) {
            // 获取绝对值以匹配 tests 中的 id
            int idToFind = Math.abs(act);
            int indexToSave = 0;

            // 遍历 tests 列表以查找匹配的 id
            for (int i = 0; i < tests.size(); i++) {
                int testId = tests.get(i).getId();
                if (testId == idToFind) {
                    indexToSave = i;
                    break;
                }
            }

            // 如果 action 是负数，表示错误，存储负数索引
            if (act < 0) {
                actionToSave.add(-indexToSave);
            } else {
                actionToSave.add(indexToSave);
            }

            // 如果 action 列表中正数项表示正确选择，停止
            if (act > 0) {
                // 将剩下的值置为0
                while (actionToSave.size() <= 4) {
                    actionToSave.add(0);
                }
                break;
            }
        }
        Integer actions_id = fourTestActionMapper.selectActionIdByActions(actionToSave.get(0), actionToSave.get(1), actionToSave.get(2), actionToSave.get(3));
        FourTestActions fourTestActions = new FourTestActions();
        if (actions_id == null) {
            fourTestActions.setAction1(actionToSave.get(0));
            fourTestActions.setAction2(actionToSave.get(1));
            fourTestActions.setAction3(actionToSave.get(2));
            fourTestActions.setAction4(actionToSave.get(3));
            fourTestActionMapper.insertNewTestGroup(fourTestActions);
            actions_id = fourTestActions.getId();
        }
        // 现在有了操作的id
        // 接下来往数据库中存储
        userTestMapper.insert(testRequest.getTestGroupId(), getCurrentChinaTime(), 1, testRequest.getAnswerSeconds(), user_id, actions_id);

    }

}
