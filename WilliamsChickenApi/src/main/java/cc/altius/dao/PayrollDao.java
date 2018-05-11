/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.dao;

import cc.altius.model.Payroll;
import java.util.List;

/**
 *
 * @author manish
 */
public interface PayrollDao {

    public int addPayroll(List<Payroll> payroll, int userId);

    public List<Payroll> getPayrollReportByDate(String startDate, String endDate);
}
