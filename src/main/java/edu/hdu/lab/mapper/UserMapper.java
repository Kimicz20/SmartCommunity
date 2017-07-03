package edu.hdu.lab.mapper;

import edu.hdu.lab.model.Community;
import edu.hdu.lab.model.User;
import edu.hdu.lab.model.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //user defined
    List<String> getAllDatabases();
    
    List<Community> getAllCommunities();
    
    List<User> getUsersByParams(User u);
    
    List<User> getUsersByParamsInstantly(User u);        
}