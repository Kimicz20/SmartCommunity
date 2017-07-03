/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.controllers;

import edu.hdu.lab.model.Repair;
import edu.hdu.lab.model.RepairHistory;
import edu.hdu.lab.services.RepairService;
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
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 报修控制器
 * @author justin
 */
@Controller
public class RepairController {
    
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    private RepairService repairService;
    
    @ResponseBody
    @RequestMapping(value="/repair", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")    
    public String getAllRepairs() {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        List<Repair> list = repairService.getRepairListByParams(paramsMap);
        
        return JsonUtils.createGson().toJson(list);
    }
    
    @ResponseBody
    @RequestMapping(value="/repair/show", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getRepairsByParams(@RequestParam(value = "startTime", required = false) String startTime,
                                     @RequestParam(value = "endTime", required = false) String endTime,
                                     @RequestParam(value = "page", required = false) Integer page,
                                     @RequestParam(value = "size", required = false) Integer size) {
        Repair repair = new Repair();
        
        if (startTime != null) {
            try {
                Date startDate = new SimpleDateFormat(Constants.DATE_FORMAT).parse(startTime);
                repair.setStartTime(startDate);
            } catch (ParseException ex) {
                logger.error("Time Format error! startDate: " + startTime);
                return WebUtils.generateResult(Constants.RESULT_CODE_DATEFORMAT_WRONG);
            }
        }
        
        if (endTime != null) {
            try {
                Date endDate = new SimpleDateFormat(Constants.DATE_FORMAT).parse(endTime);
                repair.setEndTime(endDate);
            } catch (ParseException ex) {
                logger.error("Time Format error! endDate: " + endTime);
                return WebUtils.generateResult(Constants.RESULT_CODE_DATEFORMAT_WRONG);
            }
        }
        
        if (page != null && size != null) {
            repair.setOffset(WebUtils.computeDataOffset(page, size));
            repair.setSize(size);
        }
        
        List<Repair> list = repairService.getRepairRecords(repair);
        
        return JsonUtils.createGson().toJson(list);
    }
    
    @ResponseBody
    @RequestMapping(value="/repair/new", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String addNewRepair(@RequestParam(value = "text", required = false) String texts,
                               @RequestParam("room") String room,
                               @RequestParam("tel") String tel,
                               @RequestParam("contact") String contact,
                               @RequestParam(value = "type", required = false) Integer type,
                               @RequestParam(value = "timeOne", required = false) String timeOne,
                               @RequestParam(value = "timeTwo", required = false) String timeTwo,
                               @RequestParam(value = "timeThree", required = false) String timeThree,
                               @RequestParam(value = "pictureOne", required = false) CommonsMultipartFile pictureOneFile,
                               @RequestParam(value = "pictureTwo", required = false) CommonsMultipartFile pictureTwoFile,
                               @RequestParam(value = "pictureThree", required = false) CommonsMultipartFile pictureThreeFile,
                               @RequestParam(value = "voice", required = false) CommonsMultipartFile voiceFile,
                               @RequestParam(value = "callback", required = false) String callback,
                               HttpServletRequest request) {
        String filesLocation = Constants.STATIC_FILES_PATH;
        Repair repair = new Repair();
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        
        logger.debug("texts: " + texts);
        repair.setTexts(texts);
        repair.setCreator(userId);
        repair.setLastEditor(userId);
        repair.setRoom(room);
        repair.setType(type);
        repair.setContact(contact);
        repair.setPhone(tel);
        repair.setTimeOne(timeOne);
        repair.setTimeTwo(timeTwo);
        repair.setTimeThree(timeThree);
        repair.setIsFixed(Constants.REPAIR_NOT_FIXED);
        
        File dirFile = new File(filesLocation);
        if (!WebUtils.checkDirAvailabilityAndCreate(dirFile))
            return WebUtils.produceResult(callback, Constants.RESULT_CODE_DIR_CREATION_FAILED);
        
        String pictureOneFileName = WebUtils.fileIsEmpty(pictureOneFile) ? null : WebUtils.saveFile(pictureOneFile, filesLocation, Constants.FILE_TYPE_IMAGE);
        String pictureTwoFileName = WebUtils.fileIsEmpty(pictureTwoFile) ? null : WebUtils.saveFile(pictureTwoFile, filesLocation, Constants.FILE_TYPE_IMAGE);
        String pictureThreeFileName = WebUtils.fileIsEmpty(pictureThreeFile) ? null : WebUtils.saveFile(pictureThreeFile, filesLocation, Constants.FILE_TYPE_IMAGE);
        String voiceFileName = WebUtils.fileIsEmpty(voiceFile) ? null : WebUtils.saveFile(voiceFile, filesLocation, Constants.FILE_TYPE_AUDIO);        

        String resultFileName = voiceFileName + "." + Constants.DEFAULT_WEBBROWSER_AUDIO_FILE_FORMAT;
        int convertResult = WebUtils.MultimediaConverter(voiceFileName, resultFileName, filesLocation);
        
        repair.setPictureOne(pictureOneFileName);
        repair.setPictureTwo(pictureTwoFileName);
        repair.setPictureThree(pictureThreeFileName);
        repair.setVoice(convertResult == 0 ? resultFileName : voiceFileName);
        
        int resultCode = repairService.addRepair(repair);
        
        return WebUtils.generateResult(resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value = "/repair/history/new", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String addNewRepairHistory(@RequestParam(value = "repairId", required = true) Integer repairId,
                                      @RequestParam(value = "status", required = true) Integer status,
                                      @RequestParam(value = "comment", required = false) String comment,
                                      HttpServletRequest request) {
        RepairHistory repairHistory = new RepairHistory();
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        
        repairHistory.setComment(comment);
        repairHistory.setRepairId(repairId);
        repairHistory.setLastEditTime(Calendar.getInstance().getTime());
        repairHistory.setLastEditor(userId);
        repairHistory.setTime(repairHistory.getLastEditTime());
        repairHistory.setStatus(status);
        
        // 从comment里提取出维修工信息
        Repair repair = repairService.extractRepairWorkerInfo(repairHistory.getComment());
        // 只有存在维修工信息的时候才要进行Repair信息的更新
        if (StringUtils.isNoneEmpty(repair.getRepairWorkerName()) &&
            StringUtils.isNoneEmpty(repair.getRepairWorkerPhone())) {
            repair.setId(repairId);
            repairService.updateRepair(repair);
        }
        
        int resultCode = repairService.addRepairHistory(repairHistory);
        
        return WebUtils.generateResult(resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value = "/repair/historyList", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getRepairHistoryList(@RequestParam(value = "repairId", required = true) Integer repairId,
                                                        HttpServletRequest request) {
        List<RepairHistory> list = repairService.getRepairHistoryListByRepair(repairId);
        
        return JsonUtils.createGson().toJson(list);
    }
}
