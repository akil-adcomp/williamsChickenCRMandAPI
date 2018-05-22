/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.dao;

import cc.altius.williamsChicken.model.DTO.AccessLogReportDTO;
import cc.altius.williamsChicken.model.DTO.FCWReportDTO;
import cc.altius.williamsChicken.model.DTO.PayrollReportDTO;
import cc.altius.williamsChicken.model.DTO.SalesReportDTO;
import cc.altius.williamsChicken.model.FCW;
import cc.altius.williamsChicken.model.Payroll;
import java.util.List;

/**
 *
 * @author manish
 */
public interface ReportDao {

    public List<AccessLogReportDTO> getAccessLogReport(String startDate, String stopDate, int userId, int success);

    public List<FCWReportDTO> getFCWReport(String startDate, String endDate);

    public List<PayrollReportDTO> getPayrollReport(String startDate, String stopDate);

    public List<FCW> getFCWList(String startDate, String endDate);

    public List<SalesReportDTO> getSalesReport(String startDate, String endDate);
}
