/*
 */

package edu.hdu.lab.datasource;

import java.util.Map;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *
 * @author justin
 */
public class DatasourceRouter extends AbstractRoutingDataSource {
    
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
  
    /** 
     *  
     * @author geloin 
     * @date 2012-5-18 4:06:44 
     * @return the currentLookupKey 
     */  
    public static String getCurrentLookupKey() {  
        return (String) contextHolder.get();  
    }  
  
    /** 
     *  
     * @author geloin 
     * @date 2012-5-18 4:06:44 
     * @param currentLookupKey 
     *            the currentLookupKey to set 
     */  
    public static void setCurrentLookupKey(String currentLookupKey) {  
        contextHolder.set(currentLookupKey);  
    }  

    /**
     * Update targetDataSources for this instance of AbstractRoutingDataSource
     * @param dsMap dataSources Map
     */
    public void updateTargetRoutingDataSources(Map<Object, Object> dsMap) {
        setTargetDataSources(dsMap);
        afterPropertiesSet();
    }
    
    /* 
     * (non-Javadoc) 
     *  
     * @see 
     * org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource# 
     * determineCurrentLookupKey() 
     */  
    @Override  
    protected Object determineCurrentLookupKey() {  
        return getCurrentLookupKey();  
    }    
}
