package edu.hdu.lab.mapper;

import edu.hdu.lab.pojo.Life;
import edu.hdu.lab.pojo.LifeExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface LifeMapper {
    int countByExample(LifeExample example);

    int deleteByExample(LifeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Life record);

    int insertSelective(Life record);

    List<Life> selectByExample(LifeExample example);

    Life selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Life record, @Param("example") LifeExample example);

    int updateByExample(@Param("record") Life record, @Param("example") LifeExample example);

    int updateByPrimaryKeySelective(Life record);

    int updateByPrimaryKey(Life record);
    
//    user defined methods
    List<Life> getLifeInfoByParams(Map<String, Object> paramsMap);
}