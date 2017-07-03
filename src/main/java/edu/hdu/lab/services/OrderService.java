/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.services;

import edu.hdu.lab.model.Grocery;
import edu.hdu.lab.model.GroceryFeedback;
import edu.hdu.lab.model.Product;
import java.util.List;
import java.util.Map;

/**
 *
 * @author justin
 */
public interface OrderService {
    
    public List<Grocery> getAllGroceries();
    
    public List<Grocery> getGroceriesByParams(Map<String, Object> paramsMap);
    
    public int addGrocery(Grocery grocery);
    
    public List<Product> getAllProducts(int groceryId);
    
    public int deleteGrocery(int id);
    
    public int addProduct(Product product);
    
    public int editProduct(Product product);
    
    public int deleteProduct(int id);
    
    public int addComment(GroceryFeedback feedback);
    
    public List<GroceryFeedback> getCommentsForGrocery(int groceryId);
    
    public List<Grocery> getGroceryInfoByName(String groceryName);
    
    public int updateGrocery(Grocery g);
    
    public List<Product> getProductsByGroceryName(String groceryName);
    
    public Product getProductById(Integer id);
}
