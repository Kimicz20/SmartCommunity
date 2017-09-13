/*
 *  所有权归603实验室所有
 */
package edu.hdu.lab.controllers;

import edu.hdu.lab.pojo.Message;
import edu.hdu.lab.services.FamilyService;
import edu.hdu.lab.services.MessageService;
import edu.hdu.lab.utils.Constants;
import edu.hdu.lab.utils.JsonUtils;
import edu.hdu.lab.utils.WebUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 消息模块控制器
 * @author justin
 */
@Controller
public class MessageController {
    
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private FamilyService familyService;
    
    @ResponseBody
    @RequestMapping(value="/message/me", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")    
    public String getMyMessages(HttpServletRequest request) {
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        Message m = new Message();
        
        m.setReceiver(userId);
        m.setStatus(Constants.MESSAGE_STATUS_UNREAD);
        List<Message> list = messageService.getMessages(m);
        
        for(Message message : list) {
            switch (message.getType()) {
                case Constants.MESSAGE_TYPE_FAMILY_THREAD_NEW_COMMENT: {
                    message.setParentObject(familyService.getThreadById(message.getParentObjId()));
                    message.setThisObject(familyService.getCommentById(message.getThisObjId()));
                }
            }
        }
        
        return JsonUtils.createGson().toJson(list);
    }
    
    @ResponseBody
    @RequestMapping(value="/message/read/{id}", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")     
    public String readMessage(@PathVariable("id") int id) {
        int resultCode = messageService.readMessage(id);
        
        return WebUtils.produceResult(null, resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/message/number", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")    
    public String getUnreadMessageNumber(HttpServletRequest request) {
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        Message m = new Message();
        
        m.setReceiver(userId);        
        int unreadNumber = messageService.getUnreadMessageNumber(m);
        
        return WebUtils.generateResult(Constants.RESULT_NAME_UNREAD, unreadNumber);
    }
}
