/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.services.impl;

import edu.hdu.lab.mapper.FamilyCommentMapper;
import edu.hdu.lab.mapper.FamilyMapper;
import edu.hdu.lab.pojo.Family;
import edu.hdu.lab.pojo.FamilyComment;
import edu.hdu.lab.pojo.Message;
import edu.hdu.lab.services.FamilyService;
import edu.hdu.lab.services.MessageService;
import edu.hdu.lab.utils.Constants;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author justin
 */
@Service("FamilyService")
public class FamilyServiceImpl 
    implements FamilyService{

    @Autowired
    private FamilyMapper familyMapper;
    
    @Autowired
    private FamilyCommentMapper commentMapper;
    
    @Autowired
    private MessageService messageService;
    
    public List<Family> getAllFamilies() {
        return
                familyMapper.getTopicsByParams(new HashMap<String, Object>(1));
    }

    public List<Family> getFamiliesByParams(Map<String, Object> paramsMap) {
        return
                familyMapper.getTopicsByParams(paramsMap);
    }

    public int addNewFamily(Family family) {
        return
                familyMapper.insert(family);
    }

    @Transactional
    public int addFamilyComment(FamilyComment comment) {
        //储存回复内容
        int result = commentMapper.insert(comment);
        
        //加入消息表中
        Message m = new Message();
        
        m.setCreatedTime(Calendar.getInstance().getTime());
        m.setParentObjId(comment.getThread());
        m.setThisObjId(comment.getId());
        m.setSender(comment.getCreator());
        m.setReceiver(getThreadOwnerById(comment.getThread()));
        m.setStatus(Constants.MESSAGE_STATUS_UNREAD);
        m.setType(Constants.MESSAGE_TYPE_FAMILY_THREAD_NEW_COMMENT);
        messageService.addNewMessage(m);
        
        return result;
    }

    public int addPopularity(int threadId) {
        return
                familyMapper.addPopularity(threadId);
    }

    public int updateFamily(Family family) {
        return
                familyMapper.updateByPrimaryKey(family);
    }

    public int deleteFamily(int id) {
        return
                familyMapper.deleteByPrimaryKey(id);
    }

    public List<FamilyComment> getFamilyCommentByThread(int threadId) {
        return
                commentMapper.getCommentsByThread(threadId);
    }

    public int getThreadOwnerById(int id) {
        return
                familyMapper.getOwnerOfaThread(id);
    }

    public Family getThreadById(int id) {
        return
                familyMapper.selectByPrimaryKey(id);
    }

    public FamilyComment getCommentById(int id) {
        return
                commentMapper.selectByPrimaryKey(id);
    }
    
}
