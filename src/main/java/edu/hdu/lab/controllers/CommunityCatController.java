package edu.hdu.lab.controllers;

import edu.hdu.lab.services.CommunityCatService;
import edu.hdu.lab.utils.JsonUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by geek on 2017/7/4.
 */
@Controller
public class CommunityCatController {
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private CommunityCatService communityCatService;

    @ResponseBody
    @RequestMapping(value = "/user/community", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getAllCommunities() {
        return JsonUtils.createGson().toJson(communityCatService.getAllCommunities());
    }

}
