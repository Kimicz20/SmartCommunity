package edu.hdu.lab.mapper;

import edu.hdu.lab.model.NotificationRead;
import edu.hdu.lab.model.NotificationReadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NotificationReadMapper {
    int countByExample(NotificationReadExample example);

    int deleteByExample(NotificationReadExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NotificationRead record);

    int insertSelective(NotificationRead record);

    List<NotificationRead> selectByExample(NotificationReadExample example);

    NotificationRead selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NotificationRead record, @Param("example") NotificationReadExample example);

    int updateByExample(@Param("record") NotificationRead record, @Param("example") NotificationReadExample example);

    int updateByPrimaryKeySelective(NotificationRead record);

    int updateByPrimaryKey(NotificationRead record);
}