package edu.hdu.lab.mapper;

import edu.hdu.lab.model.Family;
import edu.hdu.lab.model.FamilyExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface FamilyMapper {
    int countByExample(FamilyExample example);

    int deleteByExample(FamilyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Family record);

    int insertSelective(Family record);

    List<Family> selectByExample(FamilyExample example);

    Family selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Family record, @Param("example") FamilyExample example);

    int updateByExample(@Param("record") Family record, @Param("example") FamilyExample example);

    int updateByPrimaryKeySelective(Family record);

    int updateByPrimaryKey(Family record);
    
//    user defined methods
    int addPopularity(int id);
    
    List<Family> getTopicsByParams(Map<String, Object> paramsMap);
    
    Integer getOwnerOfaThread(int id);
}