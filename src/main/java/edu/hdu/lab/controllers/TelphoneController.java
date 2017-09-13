/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.controllers;

import edu.hdu.lab.datasource.DataSource;
import edu.hdu.lab.pojo.PhoneNumber;
import edu.hdu.lab.services.TelephoneService;
import edu.hdu.lab.utils.JsonUtils;
import edu.hdu.lab.utils.WebUtils;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 常用电话模块的控制器
 * @author justin
 */
@Controller
public class TelphoneController {
    
    @Autowired
    private TelephoneService phoneService;
    
    @ResponseBody
    @RequestMapping(value="/telephone", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getAllTelphone() {
        List<PhoneNumber> list = phoneService.getAllPhoneNumbers();
        return JsonUtils.createGson().toJson(list);
    }
    
    @ResponseBody
    @RequestMapping(value="/telephone/new", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String addTelphone(@RequestParam("title") String title,
                              @RequestParam("number") String number,
                              HttpServletRequest request) {
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        PhoneNumber phone = new PhoneNumber();
        
        phone.setPhone(number);
        phone.setDescription(title);
        phone.setCreator(userId);
        phone.setLastEditor(userId);
        
        int resultCode = phoneService.addPhoneNumber(phone);
        
        return WebUtils.generateResult(resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/telephone/destroy/{id}", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")    
    public String deleteTelphone(@PathVariable int id) {
        int resultCode = phoneService.deletePhoneNumber(id);
        
        return WebUtils.generateResult(resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/telephone/edit", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")      
    public String editTelphone(@RequestParam("id") int id,
                               @RequestParam("title") String title,
                               @RequestParam("number") String number,
                               HttpServletRequest request) {
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        PhoneNumber phone = new PhoneNumber();
        
        phone.setId(id);
        phone.setPhone(number);
        phone.setDescription(title);
        phone.setLastEditor(userId);
        phone.setLastEditTime(Calendar.getInstance().getTime());
        
        int resultCode = phoneService.updatePhoneNumber(phone);
        
        return WebUtils.generateResult(resultCode);
    }
}
