/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.controllers;

import edu.hdu.lab.model.Family;
import edu.hdu.lab.model.FamilyComment;
import edu.hdu.lab.services.FamilyService;
import edu.hdu.lab.utils.Constants;
import edu.hdu.lab.utils.JsonUtils;
import edu.hdu.lab.utils.WebUtils;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
 * 小区大家庭控制器
 * @author justin
 */
@Controller
public class FamilyController {
    
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    private FamilyService familyService;
    
    @Resource(name="configMap")
    private Map<String, String> configMap;
    
    @ResponseBody
    @RequestMapping(value="/family", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")    
    public String getAllFamilies() {
        List<Family> list = familyService.getAllFamilies();
        
        return JsonUtils.createGson().toJson(list);
    }
    
    @ResponseBody
    @RequestMapping(value="/family/show", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")    
    public String getFamiliesByParams(@RequestParam(value = "type", required = false) Integer type,
                                      @RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "size", required = false) Integer size,
                                      @RequestParam(value = "startTime", required = false) String startTime,
                                      @RequestParam(value = "endTime", required = false) String endTime) {
        Map<String, Object> paramsMap = new HashMap<String, Object>(3);
        Date startDate = null;
        Date endDate = null;
        
        if (startTime != null) {
            try {
                startDate = new SimpleDateFormat(Constants.DATE_FORMAT).parse(startTime);
            } catch (ParseException ex) {
                logger.error("Time Format error! startDate: " + startTime);
                return WebUtils.generateResult(Constants.RESULT_CODE_DATEFORMAT_WRONG);
            }
        }
        
        if (endTime != null) {
            try {
                endDate = new SimpleDateFormat(Constants.DATE_FORMAT).parse(endTime);      
            } catch (ParseException ex) {
                logger.error("Time Format error!  endDate: " + endTime);
                return WebUtils.generateResult(Constants.RESULT_CODE_DATEFORMAT_WRONG);
            }
        }        
        
        if (page != null && size != null)
            paramsMap.put(Constants.QUERY_PARAM_OFFSET, WebUtils.computeDataOffset(page, size));
        
        paramsMap.put(Constants.FAMILY_QUERY_PARAM_TYPE, type);
        paramsMap.put(Constants.FAMILY_QUERY_PARAM_START_TIME, startDate);
        paramsMap.put(Constants.FAMILY_QUERY_PARAM_END_TIME, endDate);
        paramsMap.put(Constants.QUERY_PARAM_SIZE, size);
        
        List<Family> list = familyService.getFamiliesByParams(paramsMap);
        
        return JsonUtils.createGson().toJson(list);
    }
    
    @ResponseBody
    @RequestMapping(value="/family/new", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")    
    public String addNewFamilyThread(@RequestParam(value = "text", required = false) String texts,
                                     @RequestParam("type") Integer type,
                                     @RequestParam(value = "title", required = false) String title,
                                     @RequestParam(value = "pictureOne", required = false) CommonsMultipartFile pictureOneFile,
                                     @RequestParam(value = "pictureTwo", required = false) CommonsMultipartFile pictureTwoFile,
                                     @RequestParam(value = "pictureThree", required = false) CommonsMultipartFile pictureThreeFile,
                                     @RequestParam(value = "callback", required = false) String callback,
                                     HttpServletRequest request) {
        String filesLocation = Constants.STATIC_FILES_PATH;
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        Family family = new Family();
        
        logger.debug(texts);
        family.setTitle(title);
        family.setTexts(texts);
        family.setType(type);
        family.setCreator(userId);
        family.setLastEditTime(Calendar.getInstance().getTime());
        family.setLastEditor(userId);
        family.setPopularity(0);
        family.setTime(family.getLastEditTime());
        
        
        File dirFile = new File(filesLocation);
        if (!WebUtils.checkDirAvailabilityAndCreate(dirFile))
            return WebUtils.produceResult(callback, Constants.RESULT_CODE_DIR_CREATION_FAILED);
        
        String pictureOneFileName = WebUtils.fileIsEmpty(pictureOneFile) ? null : WebUtils.saveFile(pictureOneFile, filesLocation, Constants.FILE_TYPE_IMAGE);
        String pictureTwoFileName = WebUtils.fileIsEmpty(pictureTwoFile) ? null : WebUtils.saveFile(pictureTwoFile, filesLocation, Constants.FILE_TYPE_IMAGE);
        String pictureThreeFileName = WebUtils.fileIsEmpty(pictureThreeFile) ? null : WebUtils.saveFile(pictureThreeFile, filesLocation, Constants.FILE_TYPE_IMAGE);
        
        family.setPictureOne(pictureOneFileName);
        family.setPictureTwo(pictureTwoFileName);
        family.setPictureThree(pictureThreeFileName);
        
        int resultCode = familyService.addNewFamily(family);
        
        return WebUtils.produceResult(callback, resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/family/comment/new", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")     
    public String comment(@RequestParam("id") int id,
                          @RequestParam("text") String texts,
                          HttpServletRequest request) {
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        FamilyComment comment = new FamilyComment();
        
        comment.setCreator(userId);
        comment.setTexts(texts);
        comment.setThread(id);
        comment.setLastEditor(userId);
        comment.setLastEditTime(Calendar.getInstance().getTime());
        
        int resultCode = familyService.addFamilyComment(comment);
        
        return WebUtils.generateResult(resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/family/comment/show/{id}", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")     
    public String getCommentForThread(@PathVariable int id) {
        List<FamilyComment> list = familyService.getFamilyCommentByThread(id);
        
        return JsonUtils.createGson().toJson(list);
    }

    @ResponseBody
    @RequestMapping(value="/family/join/{id}", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")     
    public String addPopularity(@PathVariable int id,
                                HttpServletRequest request) {
        int resultCode = familyService.addPopularity(id);
        
        return WebUtils.generateResult(resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/family/destroy/{id}", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")     
    public String deleteThread(@PathVariable int id) {
        int resultCode = familyService.deleteFamily(id);
        
        return WebUtils.generateResult(resultCode);
    }
}
