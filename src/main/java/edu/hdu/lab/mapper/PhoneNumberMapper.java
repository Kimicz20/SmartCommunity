package edu.hdu.lab.mapper;

import edu.hdu.lab.model.PhoneNumber;
import edu.hdu.lab.model.PhoneNumberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhoneNumberMapper {
    int countByExample(PhoneNumberExample example);

    int deleteByExample(PhoneNumberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PhoneNumber record);

    int insertSelective(PhoneNumber record);

    List<PhoneNumber> selectByExample(PhoneNumberExample example);

    PhoneNumber selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PhoneNumber record, @Param("example") PhoneNumberExample example);

    int updateByExample(@Param("record") PhoneNumber record, @Param("example") PhoneNumberExample example);

    int updateByPrimaryKeySelective(PhoneNumber record);

    int updateByPrimaryKey(PhoneNumber record);
}