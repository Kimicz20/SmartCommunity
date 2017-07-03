/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.controllers;

import edu.hdu.lab.model.Life;
import edu.hdu.lab.services.LifeService;
import edu.hdu.lab.utils.Constants;
import edu.hdu.lab.utils.JsonUtils;
import edu.hdu.lab.utils.LocationUtils;
import edu.hdu.lab.utils.WebUtils;
import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 生活导航控制器
 * @author justin
 */
@Controller
public class LifeController {
    
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    private LifeService lifeService;
    
    @Resource(name="configMap")
    private Map<String, String> configMap;
    
    @ResponseBody
    @RequestMapping(value="/life", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getAllLifeInfos() {
        List<Life> list = lifeService.getAllLifeInfos();
        
        return JsonUtils.createGson().toJson(list);
    }
    
    @ResponseBody
    @RequestMapping(value="/life/show", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")    
    public String getLifeInfosByParams(@RequestParam(value = "type", required = false) Integer type,
                                       @RequestParam(value = "name" ,required = false) String name,
                                       @RequestParam(value = "distance", required = false) Double distance,
                                       @RequestParam(value = "myLocation", required = false) String myLocation) {
        Map<String, Object> paramsMap = new HashMap<String, Object>(4);
        
        paramsMap.put(Constants.LIFE_PARAM_TYPE, type);
        paramsMap.put(Constants.LIFE_PARAM_NAME, name);
        paramsMap.put(Constants.LIFE_PARAM_DISTANCE, distance);
        paramsMap.put(Constants.LIFE_PARAM_MYLOCATION, myLocation);
        
        List<Life> list = lifeService.getLifeInfosByParams(paramsMap);
        
        return JsonUtils.createGson().toJson(list);
    }
    
    @ResponseBody
    @RequestMapping(value="/life/me", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")    
    public String getLifeInfoByName(HttpServletRequest request) {
        String user = request.getSession().getAttribute("user").toString();
        Map<String, Object> paramsMap = new HashMap<String, Object>(1);
        Life life = null;
        
        paramsMap.put(Constants.LIFE_PARAM_NAME, user);
        
        List<Life> list = lifeService.getLifeInfosByParams(paramsMap);
        
        if (!list.isEmpty())
            life = list.get(0);
        
        return JsonUtils.createGson().toJson(life);
    }
    
    @ResponseBody
    @RequestMapping(value="/life/new", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")    
    public String addLifeInfo(@RequestParam("type") int type,
                              @RequestParam("title") String title,
                              @RequestParam("intro") String intro,
                              @RequestParam("icon") CommonsMultipartFile iconFile,
                              @RequestParam("picture") CommonsMultipartFile pictureFile,
                              @RequestParam("address") String address,
                              @RequestParam("tel") String tel,
                              @RequestParam(value = "discount", required = false) String discount,
                              @RequestParam("desc") String desc,
                              @RequestParam(value = "callback", required = false) String callback,
                              HttpServletRequest request) {
        logger.debug("iconFile: " + iconFile.getOriginalFilename());
        logger.debug("pictureFile: " + pictureFile.getOriginalFilename());
        
        Life life = new Life();
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        String filesLocation = Constants.STATIC_FILES_PATH;
        
        life.setAddress(address);
        life.setCreator(userId);
        life.setDescription(desc);
        life.setDiscount(discount);
        life.setIntro(intro);
        life.setLastEditor(userId);
        life.setLastEditTime(Calendar.getInstance().getTime());
        life.setPhone(tel);
        life.setTitle(title);
        life.setType(type);
        life.setLocation(LocationUtils.getGeoLocation(address, 
                                                      configMap.get(Constants.MAP_KEY_BAIDUMAP_SCHEME), 
                                                      configMap.get(Constants.MAP_KEY_BAIDUMAP_HOST), 
                                                      configMap.get(Constants.MAP_KEY_BAIDUMAP_PATH), 
                                                      configMap.get(Constants.MAP_KEY_BAIDUMAP_KEY))); 
        
        File dirFile = new File(filesLocation);
        if (!WebUtils.checkDirAvailabilityAndCreate(dirFile))
            return WebUtils.produceResult(callback, Constants.RESULT_CODE_DIR_CREATION_FAILED);
        
        String iconFileNewName = WebUtils.fileIsEmpty(iconFile) ? null : WebUtils.saveFile(iconFile, filesLocation, Constants.FILE_TYPE_IMAGE);
        String pictureFileNewName = WebUtils.fileIsEmpty(pictureFile) ? null : WebUtils.saveFile(pictureFile, filesLocation, Constants.FILE_TYPE_IMAGE);
        
        life.setIcon(iconFileNewName);
        life.setPicture(pictureFileNewName);
        
        int resultCode = lifeService.addLife(life);
        
        return WebUtils.produceResult(callback, resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/life/destroy/{id}", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")    
    public String deleteLifeInfo(@PathVariable int id) {
        int resultCode = lifeService.deleteLife(id);
        
        return WebUtils.generateResult(resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/life/edit", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")    
    public String updateLifeInfo(@RequestParam("id") int id,
                                 @RequestParam("type") int type,
                                 @RequestParam("intro") String intro,
                                 @RequestParam(value = "icon", required = false) CommonsMultipartFile iconFile,
                                 @RequestParam(value = "picture", required = false) CommonsMultipartFile pictureFile,
                                 @RequestParam("address") String address,
                                 @RequestParam("tel") String tel,
                                 @RequestParam(value = "discount", required = false) String discount,
                                 @RequestParam(value = "callback", required = false) String callback,
                                 @RequestParam("desc") String desc,
                                 HttpServletRequest request) {
        logger.debug("iconFile: " + iconFile.getOriginalFilename());
        logger.debug("pictureFile: " + pictureFile.getOriginalFilename());
        
        Life life = new Life();
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        String filesLocation = Constants.STATIC_FILES_PATH;
        
        life.setId(id);
        life.setAddress(address);
        life.setCreator(userId);
        life.setDescription(desc);
        life.setDiscount(discount);
        life.setIntro(intro);
        life.setLastEditor(userId);
        life.setLastEditTime(Calendar.getInstance().getTime());
        life.setPhone(tel);
        life.setType(type);
        life.setLocation(LocationUtils.getGeoLocation(address, 
                                                      configMap.get(Constants.MAP_KEY_BAIDUMAP_SCHEME), 
                                                      configMap.get(Constants.MAP_KEY_BAIDUMAP_HOST), 
                                                      configMap.get(Constants.MAP_KEY_BAIDUMAP_PATH), 
                                                      configMap.get(Constants.MAP_KEY_BAIDUMAP_KEY))); 
        
        File dirFile = new File(filesLocation);
        if (!WebUtils.checkDirAvailabilityAndCreate(dirFile))
            return WebUtils.produceResult(callback, Constants.RESULT_CODE_DIR_CREATION_FAILED);
        
        String iconFileNewName = WebUtils.fileIsEmpty(iconFile) ? null : WebUtils.saveFile(iconFile, filesLocation, Constants.FILE_TYPE_IMAGE);
        String pictureFileNewName = WebUtils.fileIsEmpty(pictureFile) ? null : WebUtils.saveFile(pictureFile, filesLocation, Constants.FILE_TYPE_IMAGE);
        
        life.setIcon(iconFileNewName);
        life.setPicture(pictureFileNewName);
        
        int resultCode = lifeService.updateLife(life);
        
        return WebUtils.produceResult(callback, resultCode);        
    }
    
    @ResponseBody
    @RequestMapping(value="/life/discount/edit", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")        
    public String updateDiscount(@RequestParam("id") int id,
                                 @RequestParam("texts") String texts) {
        
        int resultCode = lifeService.updateDiscount(id, texts);
        
        return WebUtils.generateResult(resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/life/discount/new", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")        
    public String addDiscount(@RequestParam("id") int id,
                              @RequestParam("texts") String texts) {
        
        int resultCode = lifeService.addDiscount(id, texts);
        
        return WebUtils.generateResult(resultCode);
    }
}
