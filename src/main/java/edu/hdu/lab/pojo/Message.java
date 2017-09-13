package edu.hdu.lab.pojo;

import java.util.Date;

public class Message {
    private Integer id;

    private Integer receiver;

    private Integer sender;

    private Integer type;

    private Date createdTime;

    private Integer status;

    private Integer parentObjId;

    private Integer thisObjId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getParentObjId() {
        return parentObjId;
    }

    public void setParentObjId(Integer parentObjId) {
        this.parentObjId = parentObjId;
    }

    public Integer getThisObjId() {
        return thisObjId;
    }

    public void setThisObjId(Integer thisObjId) {
        this.thisObjId = thisObjId;
    }
    
    
//    user defined fields and methods
    private String receiverName;
    
    private String receiverIcon;
    
    private String senderName;
    
    private String senderIcon;
    
    private Object parentObject;
    
    private Object thisObject;

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverIcon() {
        return receiverIcon;
    }

    public void setReceiverIcon(String receiverIcon) {
        this.receiverIcon = receiverIcon;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderIcon() {
        return senderIcon;
    }

    public void setSenderIcon(String senderIcon) {
        this.senderIcon = senderIcon;
    }

    public Object getParentObject() {
        return parentObject;
    }

    public void setParentObject(Object parentObject) {
        this.parentObject = parentObject;
    }

    public Object getThisObject() {
        return thisObject;
    }

    public void setThisObject(Object thisObject) {
        this.thisObject = thisObject;
    }
}