package edu.hdu.lab.mapper;

import edu.hdu.lab.pojo.Advertise;
import edu.hdu.lab.pojo.AdvertiseExample;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface AdvertiseMapper {
    int countByExample(AdvertiseExample example);

    int deleteByExample(AdvertiseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Advertise record);

    int insertSelective(Advertise record);

    List<Advertise> selectByExample(AdvertiseExample example);

    Advertise selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Advertise record, @Param("example") AdvertiseExample example);

    int updateByExample(@Param("record") Advertise record, @Param("example") AdvertiseExample example);

    int updateByPrimaryKeySelective(Advertise record);

    int updateByPrimaryKey(Advertise record);

    // User defined methods
    List<Advertise> getAdvertises(Advertise ad);
}