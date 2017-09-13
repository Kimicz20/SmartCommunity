/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.services.impl;

import edu.hdu.lab.mapper.GroceryFeedbackMapper;
import edu.hdu.lab.mapper.GroceryMapper;
import edu.hdu.lab.mapper.ProductMapper;
import edu.hdu.lab.pojo.Grocery;
import edu.hdu.lab.pojo.GroceryExample;
import edu.hdu.lab.pojo.GroceryFeedback;
import edu.hdu.lab.pojo.GroceryFeedbackExample;
import edu.hdu.lab.pojo.Product;
import edu.hdu.lab.pojo.ProductExample;
import edu.hdu.lab.services.OrderService;
import edu.hdu.lab.utils.Constants;
import edu.hdu.lab.utils.LocationUtils;
import edu.hdu.lab.utils.WebUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author justin
 */
@Service("OrderService")
public class OrderServiceImpl 
    implements OrderService{
    
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private GroceryMapper groceryMapper;
    
    @Autowired
    private GroceryFeedbackMapper feedbackMapper;
    
    @Autowired
    private ProductMapper productMapper;
    
    @Resource(name="configMap")
    private Map<String, String> configMap;
    
    public List<Grocery> getAllGroceries() {
        return
                groceryMapper.selectByExample(new GroceryExample());
    }

    public List<Grocery> getGroceriesByParams(Map<String, Object> paramsMap) {
        String myLocation = WebUtils.ifNull(paramsMap.get(Constants.GROCERY_PARAM_USER_LOCATION), "").toString();
        String distance = WebUtils.ifNull(paramsMap.get(Constants.GROCERY_PARAM_DISTANCE), "").toString();
        String type = WebUtils.ifNull(paramsMap.get(Constants.GROCERY_PARAM_TYPE), "").toString();
        List<Grocery> resultList = new ArrayList<Grocery>();
        
        if (!"".equals(myLocation.trim()) && !"".equals(distance)) {
            //开启距离和类型查询
            String myLongitude = myLocation.split(Constants.LOCATION_SEPERATOR)[0];
            String myLatitude = myLocation.split(Constants.LOCATION_SEPERATOR)[1];
            double myLong = Double.valueOf(myLongitude);
            double myLat = Double.valueOf(myLatitude);
            double dist = Double.valueOf(distance);

            List<Grocery> list = getAllGroceries();
            for (Grocery g : list) {
                try {
                    double gLong = Double.valueOf(g.getLocation().split(Constants.LOCATION_SEPERATOR)[0]);
                    double gLat = Double.valueOf(g.getLocation().split(Constants.LOCATION_SEPERATOR)[1]);
                    double d = LocationUtils.getDistanceBetweenPlaces(myLong, myLat, gLong, gLat);
                    int gType = g.getType();
                    
                    logger.debug("Requested distance: " + distance + " Real distance: " + d);

                    //只往resultList中放入距离小于要求的，并且类型匹配的（或者就是所有类型）的Grocery对象
                    if (d <= dist && (type.equals("" + gType) || "".equals(type))) {
                        resultList.add(g);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else if (!"".equals(type)) {
            //只按类型查询
            GroceryExample example = new GroceryExample();
            
            example.createCriteria().andTypeEqualTo(Integer.valueOf(type));
            resultList = groceryMapper.selectByExample(example);
        }
        else if ("".equals(type)) {
            //查询所有结果
            GroceryExample example = new GroceryExample();
            resultList = groceryMapper.selectByExample(example);
        }
        
        return resultList;
    }

    public int addGrocery(Grocery grocery) {
        String geoLocation = LocationUtils.getGeoLocation(grocery.getAddress(), 
                                                          configMap.get(Constants.MAP_KEY_BAIDUMAP_SCHEME), 
                                                          configMap.get(Constants.MAP_KEY_BAIDUMAP_HOST), 
                                                          configMap.get(Constants.MAP_KEY_BAIDUMAP_PATH), 
                                                          configMap.get(Constants.MAP_KEY_BAIDUMAP_KEY));
        grocery.setLocation(geoLocation);
        
        return groceryMapper.insert(grocery);
    }

    public int deleteGrocery(int id) {
        return
                groceryMapper.deleteByPrimaryKey(id);
    }

    public int addProduct(Product product) {
        Integer groceryId = groceryMapper.getGroceryIdByUserId(product.getCreator());
        
        product.setGrocery(groceryId);
        
        return productMapper.insert(product);
    }

    public int editProduct(Product product) {
        return
                productMapper.updateByPrimaryKeySelective(product);
    }

    @Transactional
    public int addComment(GroceryFeedback feedback) {
        Grocery g = groceryMapper.selectByPrimaryKey(feedback.getGrocery());
        GroceryFeedbackExample example = new GroceryFeedbackExample();
        
        //更新店铺评分
        example.createCriteria().andGroceryEqualTo(feedback.getGrocery());
        int commentsNumber = feedbackMapper.countByExample(example) + 1;
        int oldAvgRate = g.getRate();
        //计算平均值的递推公式
        int newAvgRate = (int) ((float)oldAvgRate + (float)(feedback.getRate() - oldAvgRate) / (float)commentsNumber);
        
        logger.debug("Old Rate: " + oldAvgRate + " New Rate: " + newAvgRate);
        
        g.setRate(newAvgRate);
        groceryMapper.updateByPrimaryKeySelective(g);
        
        return feedbackMapper.insert(feedback);
    }

    public List<Product> getAllProducts(int groceryId) {
        ProductExample example = new ProductExample();
        
        example.createCriteria().andGroceryEqualTo(groceryId);
        
        return productMapper.selectByExample(example);
    }

    public int deleteProduct(int id) {
        return
                productMapper.deleteByPrimaryKey(id);
    }

    public List<GroceryFeedback> getCommentsForGrocery(int groceryId) {
        return 
                feedbackMapper.getAllFeebacksForOneGrocery(groceryId);
    }

    public List<Grocery> getGroceryInfoByName(String groceryName) {
        GroceryExample example = new GroceryExample();
        
        example.createCriteria().andTitleEqualTo(groceryName);
        
        return groceryMapper.selectByExample(example);
    }

    public int updateGrocery(Grocery g) {
        String geoLocation = LocationUtils.getGeoLocation(g.getAddress(), 
                                                          configMap.get(Constants.MAP_KEY_BAIDUMAP_SCHEME), 
                                                          configMap.get(Constants.MAP_KEY_BAIDUMAP_HOST), 
                                                          configMap.get(Constants.MAP_KEY_BAIDUMAP_PATH), 
                                                          configMap.get(Constants.MAP_KEY_BAIDUMAP_KEY));
        g.setLocation(geoLocation);
        
        return groceryMapper.updateByPrimaryKeySelective(g);
    }

    public List<Product> getProductsByGroceryName(String groceryName) {
        return
                productMapper.getProductsByGroceryName(groceryName);
    }

    public Product getProductById(Integer id) {
        return
                productMapper.selectByPrimaryKey(id);
    }

}
