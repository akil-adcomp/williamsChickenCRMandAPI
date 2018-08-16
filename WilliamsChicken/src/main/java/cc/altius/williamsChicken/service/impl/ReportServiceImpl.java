/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.service.impl;

import cc.altius.williamsChicken.dao.ReportDao;
import cc.altius.williamsChicken.model.DTO.AccessLogReportDTO;
import cc.altius.williamsChicken.model.DTO.BankRegisterDTO;
import cc.altius.williamsChicken.model.DTO.FCWReportDTO;
import cc.altius.williamsChicken.model.DTO.PayrollReportDTO;
import cc.altius.williamsChicken.model.DTO.SalesReportDTO;
import cc.altius.williamsChicken.model.FCW;
import cc.altius.williamsChicken.model.Payroll;
import cc.altius.williamsChicken.service.ReportService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author shrutika
 */
@Service("reportService")
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDao reportDao;

    @Override
    public List<AccessLogReportDTO> getAccessLogReport(String startDate, String stopDate, int userId, int success) {
        return this.reportDao.getAccessLogReport(startDate, stopDate, userId, success);
    }

    @Override
    public List<FCWReportDTO> getFCWReport(String startDate, String endDate) {
        return this.reportDao.getFCWReport(startDate, endDate);
    }

    @Override
    public List<PayrollReportDTO> getPayrollReport(String startDate, String endDate) {
        return this.reportDao.getPayrollReport(startDate, endDate);
    }

    @Override
    public List<FCW> getFCWList(String startDate, String endDate) {
        return this.reportDao.getFCWList(startDate, endDate);
    }

    @Override
    public List<SalesReportDTO> getSalesReport(String startDate, String endDate) {
        return this.reportDao.getSalesReport(startDate, endDate);
    }

    @Override
    public List<BankRegisterDTO> getBankRegisterReport(String startDate, String endDate) {
        return this.reportDao.getBankRegisterReport(startDate, endDate);
    }

}
