/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.controllers;

import edu.hdu.lab.model.Suggestion;
import edu.hdu.lab.services.SuggestionService;
import edu.hdu.lab.utils.Constants;
import edu.hdu.lab.utils.JsonUtils;
import edu.hdu.lab.utils.WebUtils;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
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
 * 投诉建议控制器
 * @author justin
 */
@Controller
public class SuggestionController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private SuggestionService suggestionService;

    @Resource(name="configMap")
    private Map<String, String> configMap;

    @ResponseBody
    @RequestMapping(value="/suggestion", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getAllSuggestions() {
        List<Suggestion> list = suggestionService.getSuggestions(new Suggestion());

        return JsonUtils.createGson().toJson(list);
    }

    @ResponseBody
    @RequestMapping(value="/suggestion/show", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getSuggestionsByParams(@RequestParam(value = "startTime", required = false) String startTime,
                                         @RequestParam(value = "endTime", required = false) String endTime,
                                         @RequestParam(value = "page", required = false) Integer page,
                                         @RequestParam(value = "size", required = false) Integer size) {
        Date startDate, endDate;
        Suggestion suggestion = new Suggestion();

        if (startTime != null) {
            try {
                startDate = new SimpleDateFormat(Constants.DATE_FORMAT).parse(startTime);
                suggestion.setStartTime(startDate);
            } catch (ParseException ex) {
                logger.error("Time Format error! startDate: " + endTime);
                return WebUtils.generateResult(Constants.RESULT_CODE_DATEFORMAT_WRONG);
            }
        }

        if (endTime != null) {
            try {
                endDate = new SimpleDateFormat(Constants.DATE_FORMAT).parse(endTime);
                suggestion.setEndTime(endDate);
            } catch (ParseException ex) {
                logger.error("Time Format error! endDate: " + endTime);
                return WebUtils.generateResult(Constants.RESULT_CODE_DATEFORMAT_WRONG);
            }
        }

        if (page != null && size != null) {
            suggestion.setOffset(WebUtils.computeDataOffset(page, size));
            suggestion.setSize(size);
        }

        List<Suggestion> list = suggestionService.getSuggestions(suggestion);

        return JsonUtils.createGson().toJson(list);
    }

    @ResponseBody
    @RequestMapping(value="/suggestion/new", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String createSuggestion(@RequestParam(value = "text", required = false) String texts,
                                   @RequestParam(value = "callback", required = false) String callback,
                                   @RequestParam(value = "type", required = true) Integer type,
                                   @RequestParam(value = "pictureOne", required = false) CommonsMultipartFile pictureOneFile,
                                   @RequestParam(value = "pictureTwo", required = false) CommonsMultipartFile pictureTwoFile,
                                   @RequestParam(value = "pictureThree", required = false) CommonsMultipartFile pictureThreeFile,
                                   @RequestParam(value = "voice", required = false) CommonsMultipartFile voiceFile,
                                   HttpServletRequest request){

        String filesLocation = Constants.STATIC_FILES_PATH;
        Suggestion suggestion = new Suggestion();
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());

        logger.debug("texts: " + texts);
        texts = StringUtils.isEmpty(texts) ? Constants.DEFAULT_TEXTS : texts;

        suggestion.setTexts(texts);
        suggestion.setCreator(userId);
        suggestion.setLastEditor(userId);
        suggestion.setStatus(Constants.SUGGESTION_STATUS_IN_PROCESS);
        suggestion.setType(type);

        File dirFile = new File(filesLocation);
        if (!WebUtils.checkDirAvailabilityAndCreate(dirFile))
            return WebUtils.produceResult(callback, Constants.RESULT_CODE_DIR_CREATION_FAILED);

        String pictureOneFileName = WebUtils.fileIsEmpty(pictureOneFile) ? null : WebUtils.saveFile(pictureOneFile, filesLocation, Constants.FILE_TYPE_IMAGE);
        String pictureTwoFileName = WebUtils.fileIsEmpty(pictureTwoFile) ? null : WebUtils.saveFile(pictureTwoFile, filesLocation, Constants.FILE_TYPE_IMAGE);
        String pictureThreeFileName = WebUtils.fileIsEmpty(pictureThreeFile) ? null : WebUtils.saveFile(pictureThreeFile, filesLocation, Constants.FILE_TYPE_IMAGE);
        String voiceFileName = WebUtils.fileIsEmpty(voiceFile) ? null : WebUtils.saveFile(voiceFile, filesLocation, Constants.FILE_TYPE_AUDIO);

        String resultFileName = voiceFileName + "." + Constants.DEFAULT_WEBBROWSER_AUDIO_FILE_FORMAT;
        int convertResult = WebUtils.MultimediaConverter(voiceFileName, resultFileName, filesLocation);

        suggestion.setPictureOne(pictureOneFileName);
        suggestion.setPictureTwo(pictureTwoFileName);
        suggestion.setPictureThree(pictureThreeFileName);
        suggestion.setVoice(convertResult == 0 ? resultFileName : voiceFileName);

        int resultCode = suggestionService.addSuggestion(suggestion);

        return WebUtils.generateResult(resultCode);
    }

    @ResponseBody
    @RequestMapping(value="/suggestion/update", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String updateSuggestion(@RequestParam(value = "id", required = true) Integer id,
                                   @RequestParam(value = "comment", required = false) String comment,
                                   @RequestParam(value = "status", required = false) Integer status) {

        if (status != null &&
                !Constants.SUGGESSTION_STATUS_COMPLETED.equals(status) &&
                !Constants.SUGGESTION_STATUS_IN_PROCESS.equals(status)) {
            logger.error("Illegal status for suggestion: " + id);
            return WebUtils.generateResult(Constants.RESULT_CODE_ILLEGAL_PARAM);
        }

        Suggestion suggestion = new Suggestion();

        suggestion.setId(id);
        suggestion.setComment(comment);
        suggestion.setStatus(status);

        int resultCode = suggestionService.updateSuggestion(suggestion);

        return WebUtils.generateResult(resultCode);
    }

    @ResponseBody
    @RequestMapping(value="/suggestion/{id}", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getSuggestionById(@PathVariable Integer id) {
        Suggestion s = suggestionService.getSuggestionById(id);

        return JsonUtils.createGson().toJson(s);
    }
}
