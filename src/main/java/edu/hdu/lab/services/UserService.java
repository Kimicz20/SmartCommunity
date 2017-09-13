/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.services;

import edu.hdu.lab.pojo.User;
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

    public List<User> getUsers(User u);
    
    public List<User> getUsersInstantly(User u);

    /**
     * 验证商户用户，返回其角色类别
     * @param name 商户用户名
     * @return 商户角色类别（具体见Constants类）
     */    
    public int validateBusinessUser(String name);
    
    public int addUser(User user, Integer businessType);
}
