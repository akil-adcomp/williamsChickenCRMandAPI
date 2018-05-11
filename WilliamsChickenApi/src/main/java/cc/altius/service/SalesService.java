/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.service;

import cc.altius.model.Sales;
import java.util.List;

/**
 *
 * @author altius
 */
public interface SalesService {

    public int addSales(Sales sales);

    public List<Sales> getSalesListReportByDate(String startDate, String endDate);
}
