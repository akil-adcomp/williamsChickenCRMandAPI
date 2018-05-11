/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.service.impl;

import cc.altius.dao.SalesDao;
import cc.altius.model.Sales;
import cc.altius.service.SalesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author altius
 */
@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    private SalesDao salesDao;

    @Override
    public int addSales(Sales sales) {
        return this.salesDao.addSales(sales);
    }

    @Override
    public List<Sales> getSalesListReportByDate(String startDate, String endDate) {
        return this.salesDao.getSalesListReportByDate(startDate, endDate);
    }
}
