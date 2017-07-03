package edu.hdu.lab.model;

import java.util.Date;

public class Repair {
    private Integer id;

    private String texts;

    private String room;

    private String phone;

    private String contact;

    private Integer creator;

    private Integer lastEditor;

    private Date lastEditTime;

    private String pictureOne;

    private String pictureTwo;

    private String pictureThree;

    private String timeOne;

    private String timeTwo;

    private String timeThree;

    private String voice;

    private Integer isFixed;

    private Integer type;

    private String repairWorkerName;

    private String repairWorkerPhone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTexts() {
        return texts;
    }

    public void setTexts(String texts) {
        this.texts = texts == null ? null : texts.trim();
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room == null ? null : room.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getLastEditor() {
        return lastEditor;
    }

    public void setLastEditor(Integer lastEditor) {
        this.lastEditor = lastEditor;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public String getPictureOne() {
        return pictureOne;
    }

    public void setPictureOne(String pictureOne) {
        this.pictureOne = pictureOne == null ? null : pictureOne.trim();
    }

    public String getPictureTwo() {
        return pictureTwo;
    }

    public void setPictureTwo(String pictureTwo) {
        this.pictureTwo = pictureTwo == null ? null : pictureTwo.trim();
    }

    public String getPictureThree() {
        return pictureThree;
    }

    public void setPictureThree(String pictureThree) {
        this.pictureThree = pictureThree == null ? null : pictureThree.trim();
    }

    public String getTimeOne() {
        return timeOne;
    }

    public void setTimeOne(String timeOne) {
        this.timeOne = timeOne == null ? null : timeOne.trim();
    }

    public String getTimeTwo() {
        return timeTwo;
    }

    public void setTimeTwo(String timeTwo) {
        this.timeTwo = timeTwo == null ? null : timeTwo.trim();
    }

    public String getTimeThree() {
        return timeThree;
    }

    public void setTimeThree(String timeThree) {
        this.timeThree = timeThree == null ? null : timeThree.trim();
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice == null ? null : voice.trim();
    }

    public Integer getIsFixed() {
        return isFixed;
    }

    public void setIsFixed(Integer isFixed) {
        this.isFixed = isFixed;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRepairWorkerName() {
        return repairWorkerName;
    }

    public void setRepairWorkerName(String repairWorkerName) {
        this.repairWorkerName = repairWorkerName == null ? null : repairWorkerName.trim();
    }

    public String getRepairWorkerPhone() {
        return repairWorkerPhone;
    }

    public void setRepairWorkerPhone(String repairWorkerPhone) {
        this.repairWorkerPhone = repairWorkerPhone == null ? null : repairWorkerPhone.trim();
    }
    
    // User defined fields and methods
    private Date startTime;
    
    private Date endTime;
    
    private Integer offset;
    
    private Integer size;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}