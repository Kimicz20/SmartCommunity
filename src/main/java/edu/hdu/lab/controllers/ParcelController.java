/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.controllers;

import edu.hdu.lab.datasource.DataSource;
import edu.hdu.lab.pojo.Parcel;
import edu.hdu.lab.pojo.User;
import edu.hdu.lab.services.ParcelService;
import edu.hdu.lab.services.UserService;
import edu.hdu.lab.utils.Constants;
import edu.hdu.lab.utils.JsonUtils;
import edu.hdu.lab.utils.WebUtils;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 邮包提醒控制器
 * @author justin
 */
@Controller
public class ParcelController {
    
    @Autowired
    private ParcelService parcelService;
    
    @Autowired
    private UserService userService;
    
    @ResponseBody
    @RequestMapping(value="/parcel", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")    
    public String getAllParcelForUser(HttpServletRequest request) {
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        Map<String, Object> paramsMap = new HashMap<String, Object>(1);
        
        paramsMap.put(Constants.PARCEL_PARAM_RECEIVER, userId);
        List<Parcel> list = parcelService.getAllParcelsByParams(paramsMap);
        
        return JsonUtils.createGson().toJson(list);
    }
    
    @ResponseBody
    @RequestMapping(value="/parcel/show/{timeInterval}", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")    
    public String getParcelByParams(@PathVariable String timeInterval,
                                    HttpServletRequest request) {
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        Map<String, Object> paramsMap = new HashMap<String, Object>(2);
        Calendar cal = Calendar.getInstance();
        
        paramsMap.put(Constants.PARCEL_PARAM_RECEIVER, userId);
        
        if (Constants.NOTIF_QUERY_PARAM_WITHIN_THREE_DAYS.equalsIgnoreCase(timeInterval.trim())) {
            cal.add(Calendar.DAY_OF_MONTH, -3);
        }
        else if (Constants.NOTIF_QUERY_PARAM_WINTIN_ONE_WEEK.equalsIgnoreCase(timeInterval.trim())) {
            cal.add(Calendar.WEEK_OF_MONTH, -1);
        }
        else if (Constants.NOTIF_QUERY_PARAM_WITHIN_ONE_MONTH.equalsIgnoreCase(timeInterval.trim())) {
            cal.add(Calendar.MONTH, -1);
        }
        
        paramsMap.put(Constants.PARCEL_PARAM_TIME, cal.getTime());
        List<Parcel> list = parcelService.getAllParcelsByParams(paramsMap);
        
        return JsonUtils.createGson().toJson(list);
    }
    
    @ResponseBody
    @RequestMapping(value="/parcel/new", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")    
    public String addParcel(@RequestParam("name") String name,
                            @RequestParam("title") String title,
                            HttpServletRequest request) {
        User u = new User();
        u.setName(name);
        u.setRole(Constants.USER_ROLE_RESIDENT);
        List<User> users = userService.getUsers(u);
        
        if (users == null || users.isEmpty()) 
            return WebUtils.generateResult(Constants.RESULT_CODE_USER_NOT_FOUND);
        
        Parcel parcel = new Parcel();
        parcel.setReceiver(users.get(0).getId());
        parcel.setTitle(title);
        parcel.setIsReceived(Constants.PARCEL_STATUS_NOT_RECEIVED);
        
        int resultCode = parcelService.addParcel(parcel);
        
        return WebUtils.generateResult(resultCode);
    } 
    
    @ResponseBody
    @RequestMapping(value="/parcel/edit", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")        
    public String editParcel(@RequestParam("receiver") int receiver,
                             @RequestParam("title") String title,
                             @RequestParam("id") int id) {
        Parcel parcel = new Parcel();
        parcel.setReceiver(receiver);
        parcel.setTitle(title);
        parcel.setId(id);
        
        int resultCode = parcelService.updateParcel(parcel);
        
        return WebUtils.generateResult(resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/parcel/destroy/{id}", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")        
    public String deleteParcel(@PathVariable int id) {
        int resultCode = parcelService.deleteParcel(id);
        
        return WebUtils.generateResult(resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/parcel/receive/{id}", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")      
    public String receiveParcel(@PathVariable int id,
                                HttpServletRequest request) {
        int resultCode = parcelService.receiveParcel(id);
        
        return WebUtils.generateResult(resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/parcel/number", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")      
    public String getUnreceivedNumber(HttpServletRequest request) {
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        int number = parcelService.getUnreadParcelNumber(userId);
        
        return WebUtils.generateResult(Constants.RESULT_NAME_UNRECEIVED, number);
    }
}
