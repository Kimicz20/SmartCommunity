/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.serviceImpls;

import edu.hdu.lab.mapper.ParcelMapper;
import edu.hdu.lab.model.Parcel;
import edu.hdu.lab.model.ParcelExample;
import edu.hdu.lab.services.ParcelService;
import edu.hdu.lab.utils.Constants;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author justin
 */
@Service("ParcelService")
public class ParcelServiceImpl 
    implements ParcelService{

    @Autowired
    private ParcelMapper parcelMapper;
    
    public List<Parcel> getAllParcelsByParams(Map<String, Object> paramsMap) {
        ParcelExample example = new ParcelExample();
        ParcelExample.Criteria cr = example.createCriteria();
        
        if (paramsMap.get(Constants.PARCEL_PARAM_TIME) != null)
            cr.andReceiveTimeGreaterThanOrEqualTo((Date)paramsMap.get(Constants.PARCEL_PARAM_TIME));
        if (paramsMap.get(Constants.PARCEL_PARAM_RECEIVER) != null)
            cr.andReceiverEqualTo((Integer)paramsMap.get(Constants.PARCEL_PARAM_RECEIVER));
        
        example.setOrderByClause("time desc");
        
        List<Parcel> list = parcelMapper.selectByExample(example);
        
        return list;
    }

    public int addParcel(Parcel parcel) {
        return
                parcelMapper.insert(parcel);
    }

    public int deleteParcel(int id) {
        return
                parcelMapper.deleteByPrimaryKey(id);
    }

    public int updateParcel(Parcel parcel) {
        return
                parcelMapper.updateByPrimaryKey(parcel);
    }

    public int receiveParcel(int id) {
        Parcel parcel = new Parcel();
        
        parcel.setId(id);
        parcel.setReceiveTime(Calendar.getInstance().getTime());
        parcel.setIsReceived(Constants.PARCEL_STATUS_RECEIVED);
        
        return parcelMapper.updateByPrimaryKeySelective(parcel);
    }
    
    public int getUnreadParcelNumber(int userId) {
        
        return parcelMapper.countUnreceived(userId);
    }
}
