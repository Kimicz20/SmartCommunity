package edu.hdu.lab.mapper;

import edu.hdu.lab.pojo.AdvertiseClick;
import edu.hdu.lab.pojo.AdvertiseClickExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdvertiseClickMapper {
    int countByExample(AdvertiseClickExample example);

    int deleteByExample(AdvertiseClickExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdvertiseClick record);

    int insertSelective(AdvertiseClick record);

    List<AdvertiseClick> selectByExample(AdvertiseClickExample example);

    AdvertiseClick selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdvertiseClick record, @Param("example") AdvertiseClickExample example);

    int updateByExample(@Param("record") AdvertiseClick record, @Param("example") AdvertiseClickExample example);

    int updateByPrimaryKeySelective(AdvertiseClick record);

    int updateByPrimaryKey(AdvertiseClick record);

    // user defined methods
    List<AdvertiseClick> getAdvertiseClicks(AdvertiseClick click);
}