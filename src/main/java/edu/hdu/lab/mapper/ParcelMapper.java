package edu.hdu.lab.mapper;

import edu.hdu.lab.pojo.Parcel;
import edu.hdu.lab.pojo.ParcelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParcelMapper {
    int countByExample(ParcelExample example);

    int deleteByExample(ParcelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Parcel record);

    int insertSelective(Parcel record);

    List<Parcel> selectByExample(ParcelExample example);

    Parcel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Parcel record, @Param("example") ParcelExample example);

    int updateByExample(@Param("record") Parcel record, @Param("example") ParcelExample example);

    int updateByPrimaryKeySelective(Parcel record);

    int updateByPrimaryKey(Parcel record);
    
    //user defined methods
    int countUnreceived(int userId);
}