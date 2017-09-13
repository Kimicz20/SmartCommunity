/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.services;

import edu.hdu.lab.pojo.PhoneNumber;
import java.util.List;

/**
 *
 * @author justin
 */
public interface TelephoneService {
    public List<PhoneNumber> getAllPhoneNumbers();
    
    public int addPhoneNumber(PhoneNumber phone);
    
    public int deletePhoneNumber(int id);
    
    public int updatePhoneNumber(PhoneNumber phone);
}
