/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.service.impl;

import cc.altius.dao.SummaryDao;
import cc.altius.model.BankRegister;
import cc.altius.model.DTO.SalesReportDTO;
import cc.altius.model.DTO.SalesSummaryDTO;
import cc.altius.model.FCW;
import cc.altius.model.Payroll;
import cc.altius.model.Sales;
import cc.altius.service.SalesService;
import cc.altius.service.SummaryService;
import cc.altius.utils.DateUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author altius
 */
@Service
public class SummaryServiceImpl implements SummaryService {

    @Autowired
    private SummaryDao summaryDao;
    @Autowired
    private SalesService salesService;

    @Override
    public List<FCW> getFCWList(int storeId, int periodId) {
        return this.summaryDao.getFCWList(storeId, periodId);
    }

    @Override
    public List<BankRegister> getBankRegisterList(int storeId, int periodId) {
        return this.summaryDao.getBankRegisterList(storeId, periodId);
    }

    @Override
    public List<Payroll> getPayrollList(int storeId, String startDate, String stopDate) {
        return this.summaryDao.getPayrollList(storeId, startDate, stopDate);
    }

    @Override
    public SalesSummaryDTO getSalesSummaryData(int storeId, int periodId) {
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD);
        String startDate = null;
        String stopDate = null;
        int netSalesLastWeek = 0;
        int netSalesLastYear = 0;
        SalesSummaryDTO summaryDTO = new SalesSummaryDTO();
        Sales sales = summaryDao.getSalesData(storeId, periodId);
        if (periodId == 1) {
            //Current 
            startDate = curDate;
            netSalesLastWeek = this.salesService.getNetSalesLastWeekReportByDate(startDate, storeId);
            netSalesLastYear = this.salesService.getNetSalesLastYearReportByDate(startDate, storeId);
        } else if (periodId == 2) {
            //Yesterday
            startDate = DateUtils.getOffsetFromCurrentDateString(DateUtils.IST, DateUtils.YMD, -1);
            netSalesLastWeek = this.salesService.getNetSalesLastWeekReportByDate(startDate, storeId);
            netSalesLastYear = this.salesService.getNetSalesLastYearReportByDate(startDate, storeId);
        } else if (periodId == 3) {
            //2 day ago
            startDate = DateUtils.getOffsetFromCurrentDateString(DateUtils.IST, DateUtils.YMD, -2);
            netSalesLastWeek = this.salesService.getNetSalesLastWeekReportByDate(startDate, storeId);
            netSalesLastYear = this.salesService.getNetSalesLastYearReportByDate(startDate, storeId);
        }
        SalesReportDTO salesReportDTO = new SalesReportDTO();
        if (sales != null) {
            salesReportDTO.setNetSalesLastWeek(netSalesLastWeek);
            salesReportDTO.setNetSalesLastYear(netSalesLastYear);
            summaryDTO.setSales(sales);
            summaryDTO.setSalesReportDTO(salesReportDTO);
        }
        

        return summaryDTO;
    }
}
