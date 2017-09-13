/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.services;

import edu.hdu.lab.pojo.Parcel;
import java.util.List;
import java.util.Map;

/**
 *
 * @author justin
 */
public interface ParcelService {
    public List<Parcel> getAllParcelsByParams(Map<String, Object> paramsMap);
    
    public int addParcel(Parcel parcel);
    
    public int deleteParcel(int id);
    
    public int updateParcel(Parcel parcel);
    
    public int receiveParcel(int id);
    
    public int getUnreadParcelNumber(int userId);
}
