package edu.hdu.lab.mapper;

import edu.hdu.lab.model.GroceryFeedback;
import edu.hdu.lab.model.GroceryFeedbackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroceryFeedbackMapper {
    int countByExample(GroceryFeedbackExample example);

    int deleteByExample(GroceryFeedbackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GroceryFeedback record);

    int insertSelective(GroceryFeedback record);

    List<GroceryFeedback> selectByExample(GroceryFeedbackExample example);

    GroceryFeedback selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GroceryFeedback record, @Param("example") GroceryFeedbackExample example);

    int updateByExample(@Param("record") GroceryFeedback record, @Param("example") GroceryFeedbackExample example);

    int updateByPrimaryKeySelective(GroceryFeedback record);

    int updateByPrimaryKey(GroceryFeedback record);
    
    
//    user defined methods
    List<GroceryFeedback> getAllFeebacksForOneGrocery(Integer groceryId);
}