package com.all.faceRecognition.mapper.findTest;

import com.all.faceRecognition.bean.get.findTest.FindTestInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FindTestInfoMapper {
    @Select("select id from find_test_info  where target_id =#{targetId} and test1_id = #{test1Id} and test2_id =#{test2Id} and test3_id = #{test3Id} and test4_id = #{test4Id} and test5_id = #{test5Id} and test6_id =#{test6Id} and test7_id = #{test7Id} and test8_id = #{test8Id}")
    Integer selectFindTestGroupIdByFindTestInfo(FindTestInfo findTestInfo) throws Exception;

    // 插入一个新的题组
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into find_test_info(target_id, test1_id, test2_id, test3_id, test4_id, test5_id, test6_id, test7_id, test8_id) value (#{targetId},#{test1Id},#{test2Id},#{test3Id},#{test4Id},#{test5Id},#{test6Id},#{test7Id},#{test8Id})")
    void insertNewTestGroup(FindTestInfo findTestInfo) throws Exception;

    // 根据id获取数据
    @Select("select * from find_test_info where id=#{id}")
    FindTestInfo selectById(int id);
}
