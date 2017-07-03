package edu.hdu.lab.service.test;

import edu.hdu.lab.model.Repair;
import edu.hdu.lab.services.RepairService;
import edu.hdu.lab.services.UserService;
import edu.hdu.lab.utils.Constants;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

/**
 *
 * @author justin
 */
@ContextConfiguration(locations = { 
        "file:src/main/webapp/WEB-INF/spring.xml",
        "file:src/main/webapp/WEB-INF/spring-mvc.xml",
        })
@RunWith(SpringJUnit4ClassRunner.class)
public class RepairServiceTest {
    
    @Autowired
    RepairService repairService;
    
    @Autowired
    private UserService userService;    
    
    private final Logger logger = Logger.getLogger(this.getClass());
    
    public RepairServiceTest() {
    }
    
    @Before
    public void initDatasource() {
        userService.configDatasources("smart_community");
        try {
            Log4jConfigurer.initLogging("file:src/main/webapp/WEB-INF/log4j.properties");
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(RepairServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void getRepairHistoryListByRepairTest() {
        repairService.getRepairHistoryListByRepair(2);
    }
    
    @Test
    public void getRepairRecordsTest() {
        Repair r = new Repair();
        
        r.setOffset(0);
        r.setSize(20);
        try {
            r.setStartTime(new SimpleDateFormat(Constants.DATE_FORMAT).parse("2015-01-10"));
            r.setEndTime(new SimpleDateFormat(Constants.DATE_FORMAT).parse("2015-01-15"));
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(RepairServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        repairService.getRepairRecords(r);
    }
}
