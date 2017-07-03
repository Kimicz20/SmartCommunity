/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.controllers;

import edu.hdu.lab.model.Community;
import edu.hdu.lab.model.User;
import edu.hdu.lab.services.UserService;
import edu.hdu.lab.utils.Constants;
import edu.hdu.lab.utils.JsonUtils;
import edu.hdu.lab.utils.WebUtils;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
 * 用户管理控制器
 * @author justin
 */
@Controller
public class UserController {
    
    private Logger logger = Logger.getLogger(getClass());
    
    @Autowired
    private UserService userService;
    
    @Resource(name="configMap")
    private Map<String, String> configMap;    
    
    @ResponseBody
    @RequestMapping(value="/user/login", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String authorize(@RequestParam("user") String user,
                            @RequestParam("pwd") String pwd,
                            @RequestParam("place") String place,
                            @RequestParam("role") int role,
                            HttpServletRequest request) {
        user = user.trim();
        pwd = pwd.trim();
        place = place.trim();
        
        int configResult = userService.configDatasources(place);
        
        if (configResult == Constants.RESULT_CODE_PLACE_NOT_EXISTS)
            return WebUtils.generateResult(configResult);
        
        //开始验证用户名、密码、角色
        List<User> list = userService.getUser(user, pwd, role);
        int resultCode = list == null ? 0 : list.size();
        Map<String, Object> resultMap = new HashMap<String, Object>(2);
        int businessType = 0;
        
        if (resultCode > 0) {
            //商家用户验证
            if (role == Constants.USER_ROLE_BUSINESS) 
                businessType = userService.validateBusinessUser(user);
            
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("place", place);
            request.getSession().setAttribute("pwd", pwd);
            request.getSession().setAttribute("role", role);
            request.getSession().setAttribute("userId", list.get(0).getId());
        }
        
        resultMap.put("resultCode", resultCode);
        resultMap.put("businessType", businessType);
            
        return JsonUtils.createGson().toJson(resultMap);
    }
    
    @ResponseBody
    @RequestMapping(value="/user/me", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")    
    public String aboutMe(HttpServletRequest request) {
        String user = request.getSession().getAttribute("user").toString();
        String pwd = request.getSession().getAttribute("pwd").toString();
        Integer role = (Integer)request.getSession().getAttribute("role");

        List<User> list = userService.getUser(user, pwd, role);
        String resultMessage = "";
        int resultCode = list == null ? 0 : list.size();
        
        if (resultCode > 0) 
            resultMessage = JsonUtils.createGson().toJson(list.get(0));
        else
            resultMessage = WebUtils.generateResult(resultCode);
        
        return resultMessage;
    }
    
    @ResponseBody
    @RequestMapping(value="/user/new", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")      
    public String addUser(@RequestParam("user") String user,
                          @RequestParam(value = "sex", required = false) String sex,
                          @RequestParam("room") String room,
                          @RequestParam("role") Integer role,
                          @RequestParam(value = "age", required = false) Integer age,
                          @RequestParam(value = "job", required = false) String job,
                          @RequestParam(value = "businessType", required = false) Integer businessType,
                          @RequestParam("place") String place,
                          @RequestParam("pwd") String pwd) {
        
        place = place.trim();
        int configResult = userService.configDatasources(place);
        
        if (configResult == Constants.RESULT_CODE_PLACE_NOT_EXISTS)
            return WebUtils.generateResult(configResult);        
        
        User u = new User();
        u.setName(user);
        
        List<User> users = userService.getUsers(u);
        if (users.size() > 0)
            return WebUtils.generateResult(Constants.RESULT_CODE_USER_ALREADY_EXISTS);
        
        u.setSex(sex);
        u.setRoom(room);
        u.setRole(role);
        u.setAge(age);
        u.setJob(job);
        u.setPasswd(pwd);
        
        int resultCode = userService.addUser(u, businessType);
        
        return WebUtils.generateResult(resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/user/worker/add", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String addWorker(@RequestParam("name") String name,
                            @RequestParam("sex") String sex,
                            @RequestParam("phone") String phone,
                            HttpServletRequest request) {
        // 只有管理员才能新增维修工
        if (!request.getSession().getAttribute("role").toString().equals("" + Constants.USER_ROLE_ADMIN))
            return WebUtils.generateResult(Constants.RESULT_CODE_INSUFFICIENT_PRIVILEGE);
        
        User u = new User();
        
        u.setName(name);
        u.setSex(sex);
        u.setPhone(phone);
        u.setRole(Constants.USER_ROLE_WORKER);
        
        int resCode = userService.addUser(u);
        
        return WebUtils.generateResult(resCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/user/destroy/{id}", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")      
    public String delUser(@PathVariable int id) {
        int resultCode = userService.deleteUser(id);
        
        return WebUtils.generateResult(resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/user/edit", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")       
    public String updateUser(@RequestParam("userId") Integer id,
                             @RequestParam(value = "sex", required = false) String sex,
                             @RequestParam(value = "room", required = false) String room,
                             @RequestParam(value = "role", required = false) Integer role,
                             @RequestParam(value = "age", required = false) Integer age,
                             @RequestParam(value = "job", required = false) String job,
                             @RequestParam(value = "pwd", required = false) String pwd,
                             @RequestParam(value = "phone", required = false) String phone,
                             @RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "icon", required = false) CommonsMultipartFile iconFile,
                             @RequestParam(value = "callback", required = false) String callback,
                             HttpServletRequest request) {
        User u = new User();
        u.setSex(sex);
        u.setRoom(room);
        u.setRole(role);
        u.setAge(age);
        u.setJob(job);
        u.setPasswd(pwd);
        u.setId(id);
        u.setPhone(phone);
        u.setName(name);

        String filesLocation = Constants.STATIC_FILES_PATH;
        File dirFile = new File(filesLocation);
        if (!WebUtils.checkDirAvailabilityAndCreate(dirFile))
            return WebUtils.produceResult(callback, Constants.RESULT_CODE_DIR_CREATION_FAILED);

        String iconFileNewName = WebUtils.fileIsEmpty(iconFile) ? null : WebUtils.saveFile(iconFile, filesLocation, Constants.FILE_TYPE_IMAGE);
        
        u.setIcon(iconFileNewName);
        
        int resultCode = userService.updateUser(u);
        
        return WebUtils.produceResult(callback, resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/user/logout", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")       
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        session.setAttribute("user", null);
        
        return WebUtils.generateResult(1);
    }
    
    @ResponseBody
    @RequestMapping(value="/user/community", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")       
    public String getAllCommunities() {
        userService.initDatasource();
        
        List<Community> communities = userService.getAllCommunities();
        
        return JsonUtils.createGson().toJson(communities);
    }
    
    @ResponseBody
    @RequestMapping(value="/user/search", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")     
    public String getUsersInstantly(@RequestParam(value = "role", required = false) Integer role,
                                    @RequestParam(value = "name", required = true) String name) {
        User u = new User();
        
        u.setRole(role);
        u.setName(name + Constants.SEARCH_TOKEN);
        
        List<User> list = userService.getUsersInstantly(u);
        
        //为了节省传输空间，把这些字段置为null后，返回给客户端的结果里就不会出现这些字段了
        for (User user : list) {
            user.setAge(null);
            user.setJob(null);
            user.setPasswd(null);
            user.setRoom(null);
            user.setSex(null);
            user.setRole(null);
            user.setId(null);
        }
        
        return JsonUtils.createGson().toJson(list);
    }
    
    @ResponseBody
    @RequestMapping(value="/users", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getUsersByParams(@RequestParam(value = "role", required = false) Integer role,
                                   @RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "room", required = false) String room) {
        User u = new User();
        
        u.setName(name);
        u.setRoom(room);
        u.setRole(role);

        List<User> users = userService.getUsers(u);

        for (User user : users) {
            user.setPasswd(null); //不能把密码也一并发过去
        }
        
        return JsonUtils.createGson().toJson(users);
    }
}
