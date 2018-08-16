/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.dao;

import cc.altius.model.DTO.SalesReportDTO;
import cc.altius.model.Sales;
import java.util.List;

/**
 *
 * @author manish
 */
public interface SalesDao {

    public int addSales(Sales sales, int userId);

    public SalesReportDTO getSalesListReportByDate(String startDate, int storeId);

    public boolean isExitRecord(String submitDate, int storeId);

    public int getNetSalesLastWeekReportByDate(String submitDate, int storeId);

    public int getNetSalesLastYearReportByDate(String submitDate, int storeId);

    public int getSalesLastBegningHeadCountByDate(String submitDate, int storeId);
}
