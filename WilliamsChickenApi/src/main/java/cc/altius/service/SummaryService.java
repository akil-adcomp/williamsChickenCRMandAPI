/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.service;

import cc.altius.model.BankRegister;
import cc.altius.model.DTO.SalesSummaryDTO;
import cc.altius.model.FCW;
import cc.altius.model.Payroll;
import java.util.List;

/**
 *
 * @author altius
 */
public interface SummaryService {
    
     public List<FCW> getFCWList(int storeId, int periodId);
     
     public List<BankRegister> getBankRegisterList(int storeId,int periodId);
     
     public List<Payroll> getPayrollList(int storeId, String startDate, String stopDate);
     
     public SalesSummaryDTO getSalesSummaryData(int storeId, int periodId);
}
