/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.dao;

import cc.altius.model.Sales;
import java.util.List;

/**
 *
 * @author manish
 */
public interface SalesDao {

    public int addSales(Sales sales);

    public List<Sales> getSalesListReportByDate(String startDate, String endDate);
}
