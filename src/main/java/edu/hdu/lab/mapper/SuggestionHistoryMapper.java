package edu.hdu.lab.mapper;

import edu.hdu.lab.model.SuggestionHistory;
import edu.hdu.lab.model.SuggestionHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SuggestionHistoryMapper {
    int countByExample(SuggestionHistoryExample example);

    int deleteByExample(SuggestionHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SuggestionHistory record);

    int insertSelective(SuggestionHistory record);

    List<SuggestionHistory> selectByExample(SuggestionHistoryExample example);

    SuggestionHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SuggestionHistory record, @Param("example") SuggestionHistoryExample example);

    int updateByExample(@Param("record") SuggestionHistory record, @Param("example") SuggestionHistoryExample example);

    int updateByPrimaryKeySelective(SuggestionHistory record);

    int updateByPrimaryKey(SuggestionHistory record);
}