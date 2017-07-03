/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.controllers;

import edu.hdu.lab.model.Notification;
import edu.hdu.lab.model.NotificationRead;
import edu.hdu.lab.services.NotificationService;
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
 * 物业通知模块控制器
 * @author justin
 */
@Controller
public class NotificationController {
    
    @Autowired
    private NotificationService notificationService;
    
    @ResponseBody
    @RequestMapping(value="/notification", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")    
    public String getAllNotifications(HttpServletRequest request) {
        Map<String,Object> paramsMap = new HashMap<String, Object>();
        
        paramsMap.put(Constants.QUERY_PARAM_USERID, request.getSession().getAttribute("userId"));
        List<Notification> list = notificationService.getNotificationByParams(paramsMap);
        
        return JsonUtils.createGson().toJson(list);
    }
    
    @ResponseBody
    @RequestMapping(value="/notification/show/{timeInterval}", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")        
    public String getNotificationsByParams(@PathVariable String timeInterval,
                                           HttpServletRequest request) {
        Calendar cal = Calendar.getInstance();
        Map<String,Object> paramsMap = new HashMap<String, Object>();
        
        if (Constants.NOTIF_QUERY_PARAM_WITHIN_THREE_DAYS.equalsIgnoreCase(timeInterval.trim())) {
            cal.add(Calendar.DAY_OF_MONTH, -3);
        }
        else if (Constants.NOTIF_QUERY_PARAM_WINTIN_ONE_WEEK.equalsIgnoreCase(timeInterval.trim())) {
            cal.add(Calendar.WEEK_OF_MONTH, -1);
        }
        else if (Constants.NOTIF_QUERY_PARAM_WITHIN_ONE_MONTH.equalsIgnoreCase(timeInterval.trim())) {
            cal.add(Calendar.MONTH, -1);
        }
        
        paramsMap.put(Constants.NOTIF_PARAM_EARLIST_TIME, cal.getTime());
        paramsMap.put(Constants.QUERY_PARAM_USERID, request.getSession().getAttribute("userId"));
        
        List<Notification> list = notificationService.getNotificationByParams(paramsMap);
        
        return JsonUtils.createGson().toJson(list);
    }
    
    @ResponseBody
    @RequestMapping(value="/notification/new", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")      
    public String addNotification(@RequestParam("title") String title,
                                  @RequestParam("text") String text,
                                  @RequestParam("type") Integer type,
                                  HttpServletRequest request) {
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        Notification notification = new Notification();
        
        notification.setTitle(title);
        notification.setDetail(text);
        notification.setCreator(userId);
        notification.setLastEditor(userId);
        notification.setLastEditTime(Calendar.getInstance().getTime());
        notification.setTime(notification.getLastEditTime());
        notification.setType(type);
        
        int resultCode = notificationService.addNotification(notification);
        
        return WebUtils.generateResult(resultCode);
    }

    @ResponseBody
    @RequestMapping(value="/notification/destroy/{id}", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")   
    public String deleteNotification(@PathVariable int id) {
        int resultCode = notificationService.deleteNotification(id);
        
        return WebUtils.generateResult(resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/notification/read/{id}", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")   
    public String readNotification(@PathVariable int id,
                                   HttpServletRequest request) {
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        NotificationRead notificationRead = new NotificationRead();
        
        notificationRead.setNotification(id);
        notificationRead.setUser(userId);
        
        if (notificationService.ifAlreadyRead(notificationRead))
            return WebUtils.generateResult(Constants.RESULT_CODE_NOTIFICATION_ALREADY_READ);
        
        int resultCode = notificationService.readNotification(notificationRead);
        
        return WebUtils.generateResult(resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/notification/number", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")       
    public String getUnreadNotificationNumber(HttpServletRequest request) {
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        int unreadNumber = notificationService.getUnreadNotifications(userId);
        
        return WebUtils.generateResult(Constants.RESULT_NAME_UNREAD,unreadNumber);
    }
}
