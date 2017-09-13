package edu.hdu.lab.mapper;

import edu.hdu.lab.pojo.Suggestion;
import edu.hdu.lab.pojo.SuggestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SuggestionMapper {
    int countByExample(SuggestionExample example);

    int deleteByExample(SuggestionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Suggestion record);

    int insertSelective(Suggestion record);

    List<Suggestion> selectByExample(SuggestionExample example);

    Suggestion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Suggestion record, @Param("example") SuggestionExample example);

    int updateByExample(@Param("record") Suggestion record, @Param("example") SuggestionExample example);

    int updateByPrimaryKeySelective(Suggestion record);

    int updateByPrimaryKey(Suggestion record);
    
    // user defined methods
    List<Suggestion> getSuggestions(Suggestion suggestion);      
}