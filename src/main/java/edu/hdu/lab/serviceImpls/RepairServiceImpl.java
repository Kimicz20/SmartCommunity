/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.serviceImpls;

import edu.hdu.lab.mapper.RepairHistoryMapper;
import edu.hdu.lab.mapper.RepairMapper;
import edu.hdu.lab.model.Repair;
import edu.hdu.lab.model.RepairExample;
import edu.hdu.lab.model.RepairHistory;
import edu.hdu.lab.services.RepairService;
import edu.hdu.lab.utils.Constants;
import edu.hdu.lab.utils.WebUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author justin
 */
@Service("RepairService")
public class RepairServiceImpl 
    implements RepairService{

    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    private RepairMapper repairMapper;
    
    @Autowired
    private RepairHistoryMapper repairHistoryMapper;
    
    public List<Repair> getRepairListByParams(Map<String, Object> paramsMap) {
        RepairExample example = new RepairExample();
        RepairExample.Criteria cr = example.createCriteria();
        
        if (paramsMap.get(Constants.REPAIR_PARAM_START_TIME) != null) {
            cr.andLastEditTimeGreaterThanOrEqualTo((Date)paramsMap.get(Constants.REPAIR_PARAM_START_TIME));
        }
        else if (paramsMap.get(Constants.REPAIR_PARAM_END_TIME) != null) {
            cr.andLastEditTimeLessThanOrEqualTo((Date)paramsMap.get(Constants.REPAIR_PARAM_END_TIME));
        }
        
        example.setOrderByClause("last_edit_time desc");
        
        List<Repair> list =  repairMapper.selectByExample(example);
        
        // 对报修的list进行重新排序，按照已提交->处理中->已完成的顺序依次排列
        // 这里的算法比较简单，不是太好，有志之士应该完善一下
        List<Repair> submittedList = new ArrayList<Repair>();
        List<Repair> processingList = new ArrayList<Repair>();
        List<Repair> fixedList = new ArrayList<Repair>();
        List<Repair> orderedRepairList = new ArrayList<Repair>(list.size());
        
        for (Repair item : list) {
            switch(item.getIsFixed()){
                    case Constants.REPAIR_NOT_FIXED:
                        submittedList.add(item);
                        break;
                    case Constants.REPAIR_FIXED:
                        fixedList.add(item);
                        break;
                    case Constants.REPAIR_IN_PROCESS:
                        processingList.add(item);
                        break;
            }
        }
        
        orderedRepairList.addAll(submittedList);
        orderedRepairList.addAll(processingList);
        orderedRepairList.addAll(fixedList);        
        
        return orderedRepairList;
    }

    @Transactional
    public int addRepair(Repair repair) {
        Integer newRepairId = null;
        Integer repairResult = 0;
        Integer repairHistoryResult = 0;
        
        repairResult = repairMapper.insert(repair);
        newRepairId = repair.getId();
        
        RepairHistory history = new RepairHistory();
        history.setRepairId(newRepairId);
        history.setTime(Calendar.getInstance().getTime());
        history.setLastEditTime(history.getTime());
        history.setStatus(Constants.REPAIR_NOT_FIXED);
        history.setLastEditor(1); // 默认设置id为1的用户，一般为物业的admin，来自动回复给报修业主
        history.setComment(Constants.COMMENT_ON_SUBMIT_REPAIR_HISTORY);
        
        if (1 == repairResult)
            repairHistoryResult = repairHistoryMapper.insertSelective(history);
        else
            logger.error("Failed to add new repair object: " + repair.toString());
        
        return (2 == repairHistoryResult + repairResult) ? 1 : 0;
    }

    @Transactional
    public int addRepairHistory(RepairHistory repairHistory) {
        int historyAddResultCode = 0;
        int updateRepairStatusResultCode = 0;
        
        historyAddResultCode = repairHistoryMapper.insert(repairHistory);
        if (1 == historyAddResultCode)
            updateRepairStatusResultCode = updateRepairStatus(repairHistory.getRepairId(), repairHistory.getStatus());
        else 
            logger.error("Unable to add repairHistory: id = " + repairHistory.getId());
        
        return (2 == historyAddResultCode + updateRepairStatusResultCode) ? 1 : 0;
    }

    public List<RepairHistory> getRepairHistoryListByRepair(int repairId) {
        RepairHistory repairHistory = new RepairHistory();
        
        repairHistory.setRepairId(repairId);
        
        return repairHistoryMapper.getRepairHistoryList(repairHistory);
    }
    
    private int updateRepairStatus(int repairId, int status) {
        Repair r = new Repair();
        
        r.setId(repairId);
        r.setIsFixed(status);
        
        return repairMapper.updateByPrimaryKeySelective(r);
    }

    public int updateRepair(Repair repair) {
        int resultCode = repairMapper.updateByPrimaryKeySelective(repair);
        
        return resultCode;
    }

    public Repair extractRepairWorkerInfo(String comment) {
        Pattern p = WebUtils.compileRegExpPattern(Constants.REPAIR_WORKER_INFO_PATTERN);
        Matcher m = p.matcher(comment);
        Repair r = new Repair();
        
        if (m.find()) {
            r.setRepairWorkerName(m.group(1));
            r.setRepairWorkerPhone(m.group(2));
        }
        
        return r;
    }

    public List<Repair> getRepairRecords(Repair repair) {
        List<Repair> list = repairMapper.getRepairRecordsOrderByStatus(repair);
        
        return list;
    }
}
