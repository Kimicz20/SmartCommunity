package edu.hdu.lab.mapper;

import edu.hdu.lab.pojo.Notification;
import edu.hdu.lab.pojo.NotificationExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface NotificationMapper {
    int countByExample(NotificationExample example);

    int deleteByExample(NotificationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Notification record);

    int insertSelective(Notification record);

    List<Notification> selectByExample(NotificationExample example);

    Notification selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Notification record, @Param("example") NotificationExample example);

    int updateByExample(@Param("record") Notification record, @Param("example") NotificationExample example);

    int updateByPrimaryKeySelective(Notification record);

    int updateByPrimaryKey(Notification record);
    
    //user defined methods
    
    List<Notification> getNotificationForSpecifiedUser(Map<String, Object> paramsMap);
    
    int getUnreadForSpecifiedUser(Integer userId);    
}