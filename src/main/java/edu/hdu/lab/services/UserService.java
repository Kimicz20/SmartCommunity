/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.services;

import edu.hdu.lab.model.Community;
import edu.hdu.lab.model.User;
import java.util.List;

/**
 *
 * @author justin
 */
public interface UserService {
    
    public List<User> getUser(String user, String pwd, int role);
    
    public int addUser(User user);
    
    public int deleteUser(int id);
    
    public int updateUser(User user);
    
    public List<String> getAllDatabases();
    
    public List<Community> getAllCommunities();
    
    public List<User> getUsers(User u);
    
    public List<User> getUsersInstantly(User u);

    /**
     * 验证商户用户，返回其角色类别
     * @param name 商户用户名
     * @return 商户角色类别（具体见Constants类）
     */    
    public int validateBusinessUser(String name);
    
    public int addUser(User user, Integer businessType);
    
    public int configDatasources(String databaseName);
    
    public void initDatasource();
}
