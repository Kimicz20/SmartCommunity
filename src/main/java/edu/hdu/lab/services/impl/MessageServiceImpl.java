/*
 *  所有权归603实验室所有
 */
package edu.hdu.lab.services.impl;

import edu.hdu.lab.mapper.MessageMapper;
import edu.hdu.lab.model.Message;
import edu.hdu.lab.services.MessageService;
import edu.hdu.lab.utils.Constants;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author justin
 */
@Service("MessageService")
public class MessageServiceImpl 
    implements MessageService{

    @Autowired
    private MessageMapper messageMapper;
    
    public int addNewMessage(Message message) {
        return
            messageMapper.insert(message);
    }

    public List<Message> getMessages(Message message) {
        return
            messageMapper.getMessageList(message);
    }

    public int readMessage(int id) {
        Message m = new Message();
        
        m.setId(id);
        m.setStatus(Constants.MESSAGE_STATUS_READ);
        
        return messageMapper.updateByPrimaryKeySelective(m);
    }

    public int getUnreadMessageNumber(Message message) {
        return
                messageMapper.getUnreadMessagesNumber(message);
    }
    
}
