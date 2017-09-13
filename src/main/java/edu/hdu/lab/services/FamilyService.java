/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.services;

import edu.hdu.lab.pojo.Family;
import edu.hdu.lab.pojo.FamilyComment;
import java.util.List;
import java.util.Map;

/**
 *
 * @author justin
 */
public interface FamilyService {
    
    public List<Family> getAllFamilies();
    
    public List<Family> getFamiliesByParams(Map<String,Object> paramsMap);
    
    public int addNewFamily(Family family);
    
    public int addFamilyComment(FamilyComment comment);
    
    public List<FamilyComment> getFamilyCommentByThread(int threadId);
    
    public int addPopularity(int threadId);
    
    public int updateFamily(Family family);
    
    public int deleteFamily(int id);
    
    public int getThreadOwnerById(int id);
    
    public Family getThreadById(int id);
    
    public FamilyComment getCommentById(int id);
}
