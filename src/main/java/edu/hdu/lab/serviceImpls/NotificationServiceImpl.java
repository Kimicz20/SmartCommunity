/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.serviceImpls;

import edu.hdu.lab.mapper.NotificationMapper;
import edu.hdu.lab.mapper.NotificationReadMapper;
import edu.hdu.lab.model.Notification;
import edu.hdu.lab.model.NotificationRead;
import edu.hdu.lab.model.NotificationReadExample;
import edu.hdu.lab.services.NotificationService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author justin
 */
@Service("NotificationService")
public class NotificationServiceImpl 
    implements NotificationService{

    @Autowired
    private NotificationMapper notificationMapper;
    
    @Autowired
    private NotificationReadMapper notificationReadMapper;
    
    public int addNotification(Notification notification) {
        return
                notificationMapper.insert(notification);
    }

    public int deleteNotification(int id) {
        return 
                notificationMapper.deleteByPrimaryKey(id);
    }

    public int updateNotification(Notification notification) {
        return 
                notificationMapper.updateByPrimaryKey(notification);
    }

    public List<Notification> getNotificationByParams(Map<String, Object> paramsMap) {
        return 
                notificationMapper.getNotificationForSpecifiedUser(paramsMap);
    }
    
    public int readNotification(NotificationRead notificationRead) {
        return
                notificationReadMapper.insert(notificationRead);
    }

    public int getUnreadNotifications(int userId) {
        return
                notificationMapper.getUnreadForSpecifiedUser(userId);
    }

    public boolean ifAlreadyRead(NotificationRead read) {
        NotificationReadExample example = new NotificationReadExample();
        
        example.createCriteria().andNotificationEqualTo(read.getNotification())
                                .andUserEqualTo(read.getUser());
        
        int readCount = notificationReadMapper.countByExample(example);
        
        return readCount != 0;
    }
    
}
