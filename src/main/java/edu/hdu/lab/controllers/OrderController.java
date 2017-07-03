/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.controllers;

import edu.hdu.lab.model.Grocery;
import edu.hdu.lab.model.GroceryFeedback;
import edu.hdu.lab.model.Product;
import edu.hdu.lab.services.OrderService;
import edu.hdu.lab.utils.Constants;
import edu.hdu.lab.utils.JsonUtils;
import edu.hdu.lab.utils.WebUtils;
import java.io.File;
import java.util.Calendar;
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
 * 预订配送控制器
 * @author justin
 */
@Controller
public class OrderController {
    
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    private OrderService orderService;
    
    @Resource(name="configMap")
    private Map<String, String> configMap;
    
    @ResponseBody
    @RequestMapping(value="/order", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getAllOrders() {
        List<Grocery> list = orderService.getAllGroceries();
        
        return JsonUtils.createGson().toJson(list);
    }
    
    @ResponseBody
    @RequestMapping(value="/order/me", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getMyGroceryInfo(HttpServletRequest request) {
        String user = request.getSession().getAttribute("user").toString();
        List<Grocery> groceries = orderService.getGroceryInfoByName(user);
        Grocery g = null;
        
        if (!groceries.isEmpty())
            g = groceries.get(0);
        
        return JsonUtils.createGson().toJson(g);
    }
    
    @ResponseBody
    @RequestMapping(value="/order/products/me", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getMyProductsInfo(HttpServletRequest request) {
        String user = request.getSession().getAttribute("user").toString();
        List<Product> list = orderService.getProductsByGroceryName(user);
        
        return JsonUtils.createGson().toJson(list);
    }    
    
    @ResponseBody
    @RequestMapping(value="/order/show", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getOrdersByParams(HttpServletRequest request) {
        String type = request.getParameter("type");
        String distance = request.getParameter("distance");
        String myLocation = request.getParameter("myLocation");
        Map<String, Object> paramsMap = new HashMap<String, Object>(3);
        
        paramsMap.put(Constants.GROCERY_PARAM_DISTANCE, distance);
        paramsMap.put(Constants.GROCERY_PARAM_TYPE, type);
        paramsMap.put(Constants.GROCERY_PARAM_USER_LOCATION, myLocation);
        
        List<Grocery> list = orderService.getGroceriesByParams(paramsMap);
        
        return JsonUtils.createGson().toJson(list);
    }
    
    @ResponseBody
    @RequestMapping(value="/order/new", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String addOrder(@RequestParam("name") String name,
                           @RequestParam("type") Integer type,
                           @RequestParam("address") String address,
                           @RequestParam("intro") String intro,
                           @RequestParam("phone") String phone,
                           @RequestParam(value = "icon", required = false) CommonsMultipartFile iconFile,
                           @RequestParam(value = "callback", required = false) String callback,
                           HttpServletRequest request) {
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        String filesLocation = Constants.STATIC_FILES_PATH;
        Grocery grocery = new Grocery();
        
        grocery.setAddress(address);
        grocery.setTitle(name);
        grocery.setType((Integer)WebUtils.ifNull(type, 0));
        grocery.setDescription(intro);
        grocery.setPhone(phone);
        grocery.setCreator(userId);
        grocery.setLastEditor(userId);
        grocery.setLastEditTime(Calendar.getInstance().getTime());
        grocery.setRate(0);
        
        File dirFile = new File(filesLocation);
        if (!WebUtils.checkDirAvailabilityAndCreate(dirFile))
            return WebUtils.produceResult(callback, Constants.RESULT_CODE_DIR_CREATION_FAILED);
        
        String iconFileNewName = WebUtils.fileIsEmpty(iconFile) ? null : WebUtils.saveFile(iconFile, filesLocation, Constants.FILE_TYPE_IMAGE);
        
        grocery.setIcon(iconFileNewName);
        
        int resultCode = orderService.addGrocery(grocery);
        
        return WebUtils.produceResult(callback, resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/order/edit", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")    
    public String updateOrder(@RequestParam("id") Integer id,
                              @RequestParam("type") Integer type,
                              @RequestParam("address") String address,
                              @RequestParam("intro") String intro,
                              @RequestParam("phone") String phone,
                              @RequestParam(value = "icon", required = false) CommonsMultipartFile iconFile,
                              @RequestParam(value = "callback", required = false) String callback,
                              HttpServletRequest request) {
        logger.debug("iconFile: " + iconFile.getOriginalFilename());
        
        Grocery g = new Grocery();
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        String filesLocation = Constants.STATIC_FILES_PATH;
        
        g.setId(id);
        g.setType(type);
        g.setAddress(address);
        g.setDescription(intro);
        g.setPhone(phone);
        g.setLastEditTime(Calendar.getInstance().getTime());
        g.setLastEditor(userId);

        File dirFile = new File(filesLocation);
        if (!WebUtils.checkDirAvailabilityAndCreate(dirFile))
            return WebUtils.produceResult(callback, Constants.RESULT_CODE_DIR_CREATION_FAILED);
        
        String iconFileNewName = WebUtils.fileIsEmpty(iconFile) ? null : WebUtils.saveFile(iconFile, filesLocation, Constants.FILE_TYPE_IMAGE);
        
        g.setIcon(iconFileNewName);
        
        Integer resultCode = orderService.updateGrocery(g);
        
        return WebUtils.produceResult(callback, resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/order/products/show/{id}", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")    
    public String getProducts(@PathVariable int id) {
        List<Product> list = orderService.getAllProducts(id);
        
        return JsonUtils.createGson().toJson(list);
    }
    
    @ResponseBody
    @RequestMapping(value="/order/products/{id}", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")    
    public String getProductById(@PathVariable int id) {
        Product p = orderService.getProductById(id);
        
        return JsonUtils.createGson().toJson(p);
    }    

    @ResponseBody
    @RequestMapping(value="/order/destroy/{id}", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")    
    public String deleteGrocery(@PathVariable int id) {
        int resultCode = orderService.deleteGrocery(id);
        
        return WebUtils.generateResult(resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/order/products/new", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")    
    public String addProduct(@RequestParam("name") String name,
                             @RequestParam("price") Float price,
                             @RequestParam("detail") String detail,
                             @RequestParam("unit") String unit,
                             @RequestParam(value = "icon", required = false) CommonsMultipartFile iconFile,
                             @RequestParam(value = "callback", required = false) String callback,
                             HttpServletRequest request) {
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        String filesLocation = Constants.STATIC_FILES_PATH;
        Product product = new Product();
        
        product.setCreator(userId);
        product.setLastEditor(userId);
        product.setTitle(name);
        product.setPrice(price);
        product.setDescription(detail);
        product.setLastEditTime(Calendar.getInstance().getTime());
        product.setUnit(unit);
        
        File dirFile = new File(filesLocation);
        if (!WebUtils.checkDirAvailabilityAndCreate(dirFile))
            return WebUtils.produceResult(callback, Constants.RESULT_CODE_DIR_CREATION_FAILED);
        
        String iconFileNewName = WebUtils.fileIsEmpty(iconFile) ? null : WebUtils.saveFile(iconFile, filesLocation, Constants.FILE_TYPE_IMAGE);
        
        product.setIcon(iconFileNewName);
        
        int resultCode = orderService.addProduct(product);
        
        return WebUtils.produceResult(callback, resultCode);
    }

    @ResponseBody
    @RequestMapping(value="/order/products/edit", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")    
    public String updateProduct(@RequestParam("productId") Integer productId,
                                @RequestParam("name") String name,
                                @RequestParam("price") Float price,
                                @RequestParam("detail") String detail,
                                @RequestParam("unit") String unit,
                                @RequestParam(value = "icon", required = false) CommonsMultipartFile iconFile,
                                @RequestParam(value = "callback", required = false) String callback,
                                HttpServletRequest request) {
        logger.debug("iconFile: " + iconFile.getOriginalFilename());
        
        Product p = new Product();
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        String filesLocation = Constants.STATIC_FILES_PATH;
        
        p.setLastEditor(userId);
        p.setLastEditTime(Calendar.getInstance().getTime());
        p.setDescription(detail);
        p.setId(productId);
        p.setPrice(price);
        p.setUnit(unit);
        p.setTitle(name);
        
        File dirFile = new File(filesLocation);
        if (!WebUtils.checkDirAvailabilityAndCreate(dirFile))
            return WebUtils.produceResult(callback, Constants.RESULT_CODE_DIR_CREATION_FAILED);
        
        String iconFileNewName = WebUtils.fileIsEmpty(iconFile) ? null : WebUtils.saveFile(iconFile, filesLocation, Constants.FILE_TYPE_IMAGE);
        
        p.setIcon(iconFileNewName);
        
        Integer resultCode = orderService.editProduct(p);
        
        return WebUtils.produceResult(callback, resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/order/products/destroy/{id}", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")    
    public String deleteProduct(@PathVariable int id) {
        int resultCode = orderService.deleteProduct(id);
        
        return WebUtils.generateResult(resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/order/comment/new", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")    
    public String addComment(@RequestParam("text") String texts,
                             @RequestParam("rate") int rate,
                             @RequestParam("groceryId") int groceryId,
                             HttpServletRequest request) {
        Integer userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
        GroceryFeedback comment = new GroceryFeedback();
        
        comment.setCreator(userId);
        comment.setRate(rate);
        comment.setGrocery(groceryId);
        comment.setLastEditor(userId);
        comment.setLastEditTime(Calendar.getInstance().getTime());
        comment.setTexts(texts);
        
        int resultCode = orderService.addComment(comment);
        
        return WebUtils.generateResult(resultCode);
    }
    
    @ResponseBody
    @RequestMapping(value="/order/comment/show/{groceryId}", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")    
    public String getComments(@PathVariable int groceryId) {
        List<GroceryFeedback> list = orderService.getCommentsForGrocery(groceryId);
        
        return JsonUtils.createGson().toJson(list);
    }
}
