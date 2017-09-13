/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.services.impl;

import edu.hdu.lab.datasource.DataSource;
import edu.hdu.lab.mapper.GroceryMapper;
import edu.hdu.lab.mapper.LifeMapper;
import edu.hdu.lab.mapper.UserMapper;
import edu.hdu.lab.pojo.*;
import edu.hdu.lab.services.UserService;
import edu.hdu.lab.utils.Constants;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

//import javax.sql.DataSource;

/**
 *
 * @author justin
 */
@Service("UserService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private GroceryMapper groceryMapper;
    
    @Autowired
    private LifeMapper lifeMapper;

    @DataSource
    public List<User> getUser(String user, String pwd, int role) {
        String encryptedPwd = DigestUtils.sha256Hex(pwd);
        UserExample example = new UserExample();
        
        example.createCriteria().andNameEqualTo(user)
                                .andPasswdEqualTo(encryptedPwd)
                                .andRoleEqualTo(role);
        List<User> list = userMapper.selectByExample(example);
        
        return list;
    }
    @DataSource
    public int addUser(User user) {
        if (user.getPasswd() != null)
            user.setPasswd(DigestUtils.sha256Hex(user.getPasswd()));
                
        return userMapper.insert(user);
    }

    public int deleteUser(int id) {
        return 
                userMapper.deleteByPrimaryKey(id);
    }

    public int updateUser(User user) {
        if (user.getPasswd() != null)
            user.setPasswd(DigestUtils.sha256Hex(user.getPasswd()));
        
        return userMapper.updateByPrimaryKeySelective(user);
    }
    
    public List<User> getUsers(User u) {
        return 
                userMapper.getUsersByParams(u);
    }

    public List<User> getUsersInstantly(User u) {
        return 
                userMapper.getUsersByParamsInstantly(u);
    }


    public int validateBusinessUser(String name) {
        GroceryExample groceryExample = new GroceryExample();
        groceryExample.createCriteria().andTitleEqualTo(name);
        List<Grocery> groceries = groceryMapper.selectByExample(groceryExample);
        
        if (!groceries.isEmpty())
            return Constants.USER_ROLE_GROCERY;
        
        LifeExample lifeExample = new LifeExample();
        lifeExample.createCriteria().andTitleEqualTo(name);
        List<Life> lives = lifeMapper.selectByExample(lifeExample);
        
        if (!lives.isEmpty())
            return Constants.USER_ROLE_LIFE;
        
        return 0;
    }

    @Transactional
    public int addUser(User user, Integer businessType) {
        if (businessType == null)
            return addUser(user);
        
        if (businessType.equals(Constants.USER_ROLE_GROCERY)) {
            Grocery g = new Grocery();
            
            g.setTitle(user.getName());
            g.setLastEditTime(Calendar.getInstance().getTime());
            
            groceryMapper.insert(g);
        }
        else if (businessType.equals(Constants.USER_ROLE_LIFE)) {
            Life e = new Life();
            
            e.setTitle(user.getName());
            e.setLastEditTime(Calendar.getInstance().getTime());
            
            lifeMapper.insert(e);
        }
        
        return addUser(user);
    }

}
