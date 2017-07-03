/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.services;

import edu.hdu.lab.model.Repair;
import edu.hdu.lab.model.RepairHistory;
import java.util.List;
import java.util.Map;

/**
 *
 * @author justin
 */
public interface RepairService {
   
    public List<Repair> getRepairListByParams(Map<String, Object> paramsMap);
    
    public int addRepair(Repair repair);
    
    public int addRepairHistory(RepairHistory repairHistory);
    
    public List<RepairHistory> getRepairHistoryListByRepair(int repairId);
    
    public int updateRepair(Repair repair);
    
    public Repair extractRepairWorkerInfo(String comment);
    
    public List<Repair> getRepairRecords(Repair repair);
}
