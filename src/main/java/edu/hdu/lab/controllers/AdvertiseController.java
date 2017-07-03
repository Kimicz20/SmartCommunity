package edu.hdu.lab.controllers;

import edu.hdu.lab.model.Advertise;
import edu.hdu.lab.model.AdvertiseClick;
import edu.hdu.lab.services.AdvertiseService;
import edu.hdu.lab.utils.Constants;
import edu.hdu.lab.utils.JsonUtils;
import edu.hdu.lab.utils.WebUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 广告业务控制器
 * Created by justin on 15-7-1.
 */
@Controller
public class AdvertiseController {
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private AdvertiseService advertiseService;

    @ResponseBody
    @RequestMapping(value="/advertise/show", method= RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getAdvertises(@RequestParam(value = "page", required = false) Integer page,
                                @RequestParam(value = "size", required = false) Integer size) {
        Advertise ad = new Advertise();

        if (page != null && size != null) {
            ad.setOffset(WebUtils.computeDataOffset(page, size));
            ad.setSize(size);
        }

        List<Advertise> advertises = advertiseService.getAdvertises(ad);

        return JsonUtils.createGson().toJson(advertises);
    }

    @ResponseBody
    @RequestMapping(value="/advertise/detail/{id}", method= RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getAdvertise(@PathVariable(value = "id") Integer id) {
        Advertise ad = advertiseService.getAdvertiseById(id);

        return JsonUtils.createGson().toJson(ad);

    }

    @ResponseBody
    @RequestMapping(value="/advertise/new", method= RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String addNewAdvertise(@RequestParam(value = "texts", required = false) String texts,
                                  @RequestParam(value = "type", required = false) Integer type,
                                  @RequestParam(value = "startTime", required = false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date startTime,
                                  @RequestParam(value = "dueTime", required = false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date dueTime,
                                  @RequestParam(value = "responsiblePerson", required = false) Integer responsiblePerson,
                                  @RequestParam(value = "picture", required = false) CommonsMultipartFile pictureFile,
                                  @RequestParam(value = "hypelink", required = false) String hypelink,
                                  @RequestParam(value = "callback", required = false) String callback,
                                  HttpServletRequest request) {
        String filesLocation = Constants.STATIC_FILES_PATH;
        Advertise ad = new Advertise();

        ad.setTexts(texts);
        ad.setType(type);
        ad.setResponsiblePerson(responsiblePerson);
        ad.setStartTime(startTime);
        ad.setDueTime(dueTime);
        ad.setHypelink(hypelink);

        File dirFile = new File(filesLocation);
        if (!WebUtils.checkDirAvailabilityAndCreate(dirFile))
            return WebUtils.produceResult(callback, Constants.RESULT_CODE_DIR_CREATION_FAILED);

        String pictureFileName = WebUtils.fileIsEmpty(pictureFile)
                                ? null
                                : WebUtils.saveFile(pictureFile, filesLocation, Constants.FILE_TYPE_IMAGE);
        ad.setPicture(pictureFileName);

        int resultCode = advertiseService.addNewAdvertise(ad);

        return WebUtils.produceResult(callback, resultCode);
    }


    @ResponseBody
    @RequestMapping(value="/advertise/edit", method=RequestMethod.POST, produces="text/html;charset=UTF-8")
    public String updateAdvertise(@RequestParam(value = "startTime",required = false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date startTime,
                                  @RequestParam(value = "dueTime",required = false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date dueTime,
                                  @RequestParam(value = "type",required = false) Integer type,
                                  @RequestParam(value = "picture",required = false) CommonsMultipartFile pictureFile,
                                  @RequestParam(value = "texts",required = false) String texts,
                                  @RequestParam(value = "responsiblePerson",required = false) Integer responsiblePerson,
                                  @RequestParam(value = "callback", required = false) String callback,
                                  @RequestParam(value = "hypelink", required = false) String hypelink,
                                  @RequestParam(value = "id", required = true) Integer id,
                                  HttpServletRequest request){
        String fileslocation = Constants.STATIC_FILES_PATH;
        Advertise ad = new Advertise();

        ad.setStartTime(startTime);
        ad.setDueTime(dueTime);
        ad.setType(type);
        ad.setId(id);
        ad.setTexts(texts);
        ad.setResponsiblePerson(responsiblePerson);
        ad.setHypelink(hypelink);

        File dirFile = new File(fileslocation);
        if (!WebUtils.checkDirAvailabilityAndCreate(dirFile)){
            return WebUtils.produceResult(callback, Constants.RESULT_CODE_DIR_CREATION_FAILED);
        }
        String pictureFileName = WebUtils.fileIsEmpty(pictureFile)
                               ? null
                               : WebUtils.saveFile(pictureFile,fileslocation,Constants.FILE_TYPE_IMAGE);
        ad.setPicture(pictureFileName);
        int resultCode = advertiseService.updateAdvertise(ad);

        return WebUtils.produceResult(callback,resultCode);
    }

    @ResponseBody
    @RequestMapping(value="/advertise/click/show", method= RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getAdvertiseClicks(@RequestParam(value = "page", required = false) Integer page,
                                     @RequestParam(value = "size", required = false) Integer size,
                                     @RequestParam(value = "adId", required = false) Integer adId) {

        AdvertiseClick click = new AdvertiseClick();

        if (page != null && size != null) {
            click.setOffset(WebUtils.computeDataOffset(page, size));
            click.setSize(size);
        }
        click.setAdId(adId);

        List<AdvertiseClick> clicks = advertiseService.getAdvertiseClicks(click);
        return JsonUtils.createGson().toJson(clicks);
    }

    @ResponseBody
    @RequestMapping(value="/advertise/click/new", method=RequestMethod.POST, produces="text/html;charset=UTF-8")
    public String clickAdvertise(@RequestParam(value = "adId") Integer adId,
                                 HttpServletRequest request) {
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        AdvertiseClick click = new AdvertiseClick();

        click.setAdId(adId);
        click.setClickTime(new Date());
        click.setUserId(userId);

        int resultCode = advertiseService.addNewAdvertiseClick(click);

        return WebUtils.generateResult(resultCode);
    }
}
