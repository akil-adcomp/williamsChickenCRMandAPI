/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.service.impl;

import cc.altius.dao.PayrollDao;
import cc.altius.model.Payroll;
import cc.altius.service.PayrollService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author manish
 */
@Service
public class PayrollServiceImpl implements PayrollService {
    
    @Autowired
    private PayrollDao payrollDao;
    
    @Override
    public int addPayroll(List<Payroll> payroll, int userId) {
        return this.payrollDao.addPayroll(payroll, userId);
    }
    
    @Override
    public List<Payroll> getPayrollReportByDate(String startDate, String endDate) {
        return this.payrollDao.getPayrollReportByDate(startDate, endDate);
    }
}
