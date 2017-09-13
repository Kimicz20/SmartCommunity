/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.services;

import edu.hdu.lab.pojo.Life;
import java.util.List;
import java.util.Map;

/**
 *
 * @author justin
 */
public interface LifeService {
    
    public List<Life> getAllLifeInfos();
    
    public List<Life> getLifeInfosByParams(Map<String, Object> paramsMap);
    
    public int addLife(Life life);
    
    public int deleteLife(int id);
    
    public int updateLife(Life life);
    
    public int addDiscount(int id, String discountTexts);
    
    public int updateDiscount(int id, String discountTexts);
    
    public int deleteDiscount(int id);
}
