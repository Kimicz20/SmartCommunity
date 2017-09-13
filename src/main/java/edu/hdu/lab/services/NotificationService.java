/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.services;

import edu.hdu.lab.pojo.Notification;
import edu.hdu.lab.pojo.NotificationRead;
import java.util.List;
import java.util.Map;

/**
 *
 * @author justin
 */
public interface NotificationService {
    
    public List<Notification> getNotificationByParams(Map<String, Object> paramsMap);
    
    public int addNotification(Notification notification);
    
    public int deleteNotification(int id);
    
    public int updateNotification(Notification notification);
    
    public int readNotification(NotificationRead notificationRead);
    
    public int getUnreadNotifications(int userId);
    
    public boolean ifAlreadyRead(NotificationRead read);
}
