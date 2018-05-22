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

    public SalesReportDTO getSalesListReportByDate(String startDate);

    public boolean isExitRecord(String submitDate);
}
