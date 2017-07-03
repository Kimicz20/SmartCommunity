/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.services.impl;

import edu.hdu.lab.mapper.PhoneNumberMapper;
import edu.hdu.lab.model.PhoneNumber;
import edu.hdu.lab.model.PhoneNumberExample;
import edu.hdu.lab.services.TelephoneService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author justin
 */
@Service("TelephoneService")
public class TelephoneServiceImpl 
    implements TelephoneService{
    
    @Autowired
    private PhoneNumberMapper phoneMapper;
    
    public List<PhoneNumber> getAllPhoneNumbers() {
        PhoneNumberExample example = new PhoneNumberExample();
        return phoneMapper.selectByExample(example);
    }

    public int addPhoneNumber(PhoneNumber phone) {
        return 
                phoneMapper.insert(phone);
    }

    public int deletePhoneNumber(int id) {
        return 
                phoneMapper.deleteByPrimaryKey(id);
    }

    public int updatePhoneNumber(PhoneNumber phone) {
        return 
                phoneMapper.updateByPrimaryKey(phone);
    }
    
    
    
}
