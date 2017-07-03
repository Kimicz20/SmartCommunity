/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.services.impl;

import edu.hdu.lab.datasource.DatasourceRouter;
import edu.hdu.lab.mapper.GroceryMapper;
import edu.hdu.lab.mapper.LifeMapper;
import edu.hdu.lab.mapper.UserMapper;
import edu.hdu.lab.model.Community;
import edu.hdu.lab.model.Grocery;
import edu.hdu.lab.model.GroceryExample;
import edu.hdu.lab.model.Life;
import edu.hdu.lab.model.LifeExample;
import edu.hdu.lab.model.User;
import edu.hdu.lab.model.UserExample;
import edu.hdu.lab.services.UserService;
import edu.hdu.lab.utils.Constants;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author justin
 */
@Service("UserService")
public class UserServiceImpl 
    implements UserService{

    private Logger logger = Logger.getLogger(getClass());
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private GroceryMapper groceryMapper;
    
    @Autowired
    private LifeMapper lifeMapper;
    
    @Autowired
    private DataSource dataSourceBootstrap; 
    
    @Autowired
    private DatasourceRouter dataSource;
    
    @Resource(name="dataSourceTemplate")
    private Map<String,String> dataSourceTemplate;    
    
    public List<User> getUser(String user, String pwd, int role) {
        String encryptedPwd = DigestUtils.sha256Hex(pwd);
        UserExample example = new UserExample();
        
        example.createCriteria().andNameEqualTo(user)
                                .andPasswdEqualTo(encryptedPwd)
                                .andRoleEqualTo(role);
        List<User> list = userMapper.selectByExample(example);
        
        return list;
    }

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

    public List<String> getAllDatabases() {
        return
                userMapper.getAllDatabases();
    }
    
    public List<Community> getAllCommunities() {
        return
                userMapper.getAllCommunities();
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

    public int configDatasources(String databaseName) {
        initDatasource();
        
        List<String> dbList = getAllDatabases();
        boolean isDBExists = false;
        for (String o : dbList) {
            if (o.equalsIgnoreCase(databaseName)) 
                isDBExists = true;
        }
        if (!isDBExists) 
            return Constants.RESULT_CODE_PLACE_NOT_EXISTS;
        
        // Update cachedDatasourceMap and set the current datasource for mybatis
        if (!Constants.cachedDatasourceMap.containsKey(databaseName)) {
            BasicDataSource myds = new BasicDataSource();
            myds.setDriverClassName(dataSourceTemplate.get("driverClassName"));
            myds.setUrl(dataSourceTemplate.get("url").replace(Constants.DATASOURCE_TEMPLATE_URL_PLACEHOLDER, databaseName));
            myds.setUsername(dataSourceTemplate.get("username"));
            myds.setPassword(dataSourceTemplate.get("password"));
            myds.setInitialSize(Integer.valueOf(dataSourceTemplate.get("initialSize")));
            myds.setMaxActive(Integer.valueOf(dataSourceTemplate.get("maxActive")));
            myds.setMaxIdle(Integer.valueOf(dataSourceTemplate.get("maxIdle")));
            myds.setMaxWait(Integer.valueOf(dataSourceTemplate.get("maxWait")));
            myds.setValidationQuery(dataSourceTemplate.get("validationQuery"));
            myds.setDefaultAutoCommit(Boolean.valueOf(dataSourceTemplate.get("defaultAutoCommit")));
            
            Constants.cachedDatasourceMap.put(databaseName, myds);
            dataSource.updateTargetRoutingDataSources(Constants.cachedDatasourceMap);
        }
        DatasourceRouter.setCurrentLookupKey(databaseName);
        
        logger.debug("Map size: " + Constants.cachedDatasourceMap.size());
        logger.debug("Map contents: " + Constants.cachedDatasourceMap.toString());
        
        return 1;
    }

    public void initDatasource() {
        if (Constants.cachedDatasourceMap.isEmpty())
            Constants.cachedDatasourceMap.put(Constants.DATASOURCE_BOOTSTRAP, dataSourceBootstrap);
        
        //设定初始化数据源
        DatasourceRouter.setCurrentLookupKey(Constants.DATASOURCE_BOOTSTRAP);        
    }
    
}
