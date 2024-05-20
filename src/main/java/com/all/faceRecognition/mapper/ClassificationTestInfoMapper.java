package com.all.faceRecognition.mapper;

import com.all.faceRecognition.bean.ClassificationTestInfo;
import com.all.faceRecognition.bean.FourTestInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClassificationTestInfoMapper {
    // 根据四个题目的id来判断是否存在该题组
    @Select("select id from classification_test_info where A_id =#{AId} and B_id = #{BId} and test1_id = #{test1Id} and test2_id =#{test2Id} and test3_id = #{test3Id} and test4_id = #{test4Id} and test5_id = #{test5Id} and test6_id =#{test6Id} and test7_id = #{test7Id} and test8_id = #{test8Id} and test9_id = #{test9Id} and test10_id = #{test10Id}")
    Integer selectClassificationTestGroupIdByTestsIds(int AId, int BId, int test1Id, int test2Id, int test3Id, int test4Id, int test5Id, int test6Id, int test7Id, int test8Id, int test9Id, int test10Id) throws Exception;

    @Select("select id from classification_test_info  where A_id =#{AId} and B_id = #{BId} and test1_id = #{test1Id} and test2_id =#{test2Id} and test3_id = #{test3Id} and test4_id = #{test4Id} and test5_id = #{test5Id} and test6_id =#{test6Id} and test7_id = #{test7Id} and test8_id = #{test8Id} and test9_id = #{test9Id} and test10_id = #{test10Id}")
    Integer selectClassificationTestGroupIdByClassificationTestInfo(ClassificationTestInfo classificationTestInfo) throws Exception;

    // 插入一个新的题组
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into classification_test_info( A_id, B_id, test1_id, test2_id, test3_id, test4_id, test5_id, test6_id, test7_id, test8_id, test9_id, test10_id) value (#{AId},#{BId},#{test1Id},#{test2Id},#{test3Id},#{test4Id},#{test5Id},#{test6Id},#{test7Id},#{test8Id},#{test9Id},#{test10Id})")
    void insertNewTestGroup(ClassificationTestInfo classificationTestInfo) throws Exception;
}
