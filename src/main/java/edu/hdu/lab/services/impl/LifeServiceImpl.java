/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.services.impl;

import edu.hdu.lab.mapper.LifeMapper;
import edu.hdu.lab.pojo.Life;
import edu.hdu.lab.pojo.LifeExample;
import edu.hdu.lab.services.LifeService;
import edu.hdu.lab.utils.Constants;
import edu.hdu.lab.utils.LocationUtils;
import edu.hdu.lab.utils.WebUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author justin
 */
@Service("LifeService")
public class LifeServiceImpl 
    implements LifeService{

    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    private LifeMapper lifeMapper;
    
    public List<Life> getAllLifeInfos() {
        return
                lifeMapper.selectByExample(new LifeExample());
    }

    public List<Life> getLifeInfosByParams(Map<String, Object> paramsMap) {
        List<Life> lifeList = lifeMapper.getLifeInfoByParams(paramsMap);
        String distance = WebUtils.ifNull(paramsMap.get(Constants.LIFE_PARAM_DISTANCE), "").toString();
        String myLocation = WebUtils.ifNull(paramsMap.get(Constants.LIFE_PARAM_MYLOCATION), "").toString();
        List<Life> resultList = null;
        
        if (distance.equals("") || myLocation.equals("")) 
            resultList = lifeList;
        else {
            resultList = new ArrayList<Life>();
            try {
                //按照所要求的距离限制进行筛选
                for (Life e : lifeList) {
                    Double myLongitude = Double.valueOf(myLocation.split(Constants.LOCATION_SEPERATOR)[0]);
                    Double myLatitude = Double.valueOf(myLocation.split(Constants.LOCATION_SEPERATOR)[1]);
                    Double eLongitude = Double.valueOf(e.getLocation().split(Constants.LOCATION_SEPERATOR)[0]);
                    Double eLatitude = Double.valueOf(e.getLocation().split(Constants.LOCATION_SEPERATOR)[1]);

                    Double realDistance = LocationUtils.getDistanceBetweenPlaces(myLongitude, myLatitude, eLongitude, eLatitude);
                    Double requestedDistance = Double.valueOf(distance);

                    logger.debug("realDistance=" + realDistance);
                    
                    if (realDistance <= requestedDistance) {
                        e.setDistance(realDistance);
                        resultList.add(e);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("Distance Calculation Failed! distance=" + distance + " myLocation=" + myLocation);
            }
        }
        
        return resultList;
                  
    }

    public int addLife(Life life) {
        return
                lifeMapper.insert(life);
    }

    public int deleteLife(int id) {
        return
                lifeMapper.deleteByPrimaryKey(id);
    }

    public int updateLife(Life life) {
        return
                lifeMapper.updateByPrimaryKeySelective(life);
    }

    public int addDiscount(int id, String discountTexts) {
        Life life = new Life();
        
        life.setDiscount(discountTexts);
        life.setId(id);
        
        return updateLife(life);
    }

    public int updateDiscount(int id, String discountTexts) {
        return
                addDiscount(id, discountTexts);
    }

    public int deleteDiscount(int id) {
        return
                updateDiscount(id, "");
    }
    
}
