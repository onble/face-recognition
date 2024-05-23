package com.all.faceRecognition.mapper;

import com.all.faceRecognition.bean.FourTestInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/*
 * 四选一测试的测试题组的持久层
 * */
@Mapper
public interface FourTestInfoMapper {
    // 根据四个题目的id来判断是否存在该题组
    @Select("select id from four_test_info where test1_id = #{test1_id} and test2_id =#{test2_id} and test3_id = #{test3_id} and test4_id = #{test4_id}")
    Integer selectFourTestGroupIdByTestsIds(int test1_id, int test2_id, int test3_id, int test4_id) throws Exception;

    @Select("select id from four_test_info where test1_id = #{test1Id} and test2_id =#{test2Id} and test3_id = #{test3Id} and test4_id = #{test4Id}")
    Integer selectFourTestGroupIdByFourTestInfo(FourTestInfo fourTestInfo) throws Exception;

    // 插入一个新的题组
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into four_test_info( test1_id, test2_id, test3_id, test4_id) value (#{test1Id},#{test2Id},#{test3Id},#{test4Id})")
    void insertNewTestGroup(FourTestInfo fourTestInfo) throws Exception;

    // 根据id获取数据
    @Select("select * from four_test_info where id=#{id}")
    FourTestInfo selectById(int id);
}
