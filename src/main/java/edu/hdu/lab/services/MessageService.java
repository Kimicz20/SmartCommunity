/*
 *  所有权归603实验室所有
 */
package edu.hdu.lab.services;

import edu.hdu.lab.pojo.Message;
import java.util.List;

/**
 *
 * @author justin
 */
public interface MessageService {
    public int addNewMessage(Message message);
    
    public List<Message> getMessages(Message message);
    
    public int readMessage(int id);
    
    public int getUnreadMessageNumber(Message message);
}
