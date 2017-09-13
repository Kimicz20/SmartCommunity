package edu.hdu.lab.mapper;

import edu.hdu.lab.pojo.RepairHistory;
import edu.hdu.lab.pojo.RepairHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepairHistoryMapper {
    int countByExample(RepairHistoryExample example);

    int deleteByExample(RepairHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RepairHistory record);

    int insertSelective(RepairHistory record);

    List<RepairHistory> selectByExample(RepairHistoryExample example);

    RepairHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RepairHistory record, @Param("example") RepairHistoryExample example);

    int updateByExample(@Param("record") RepairHistory record, @Param("example") RepairHistoryExample example);

    int updateByPrimaryKeySelective(RepairHistory record);

    int updateByPrimaryKey(RepairHistory record);
    
    // User defined methods
    List<RepairHistory> getRepairHistoryList(RepairHistory repairHistory);
}