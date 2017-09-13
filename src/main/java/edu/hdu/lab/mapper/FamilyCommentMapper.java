package edu.hdu.lab.mapper;

import edu.hdu.lab.pojo.FamilyComment;
import edu.hdu.lab.pojo.FamilyCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FamilyCommentMapper {
    int countByExample(FamilyCommentExample example);

    int deleteByExample(FamilyCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FamilyComment record);

    int insertSelective(FamilyComment record);

    List<FamilyComment> selectByExample(FamilyCommentExample example);

    FamilyComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FamilyComment record, @Param("example") FamilyCommentExample example);

    int updateByExample(@Param("record") FamilyComment record, @Param("example") FamilyCommentExample example);

    int updateByPrimaryKeySelective(FamilyComment record);

    int updateByPrimaryKey(FamilyComment record);
    
//    user defined methods
    List<FamilyComment> getCommentsByThread(int threadId);
}