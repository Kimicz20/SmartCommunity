package edu.hdu.lab.mapper;

import edu.hdu.lab.pojo.Grocery;
import edu.hdu.lab.pojo.GroceryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroceryMapper {
    int countByExample(GroceryExample example);

    int deleteByExample(GroceryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Grocery record);

    int insertSelective(Grocery record);

    List<Grocery> selectByExample(GroceryExample example);

    Grocery selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Grocery record, @Param("example") GroceryExample example);

    int updateByExample(@Param("record") Grocery record, @Param("example") GroceryExample example);

    int updateByPrimaryKeySelective(Grocery record);

    int updateByPrimaryKey(Grocery record);
    
//    user defined methods
    Integer getGroceryIdByUserId(Integer userId);
}