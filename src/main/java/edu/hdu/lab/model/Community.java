/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.model;

/**
 *
 * @author justin
 */
public class Community {
    private String communityName;
    
    private String dbName;
    
    private int id;

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
