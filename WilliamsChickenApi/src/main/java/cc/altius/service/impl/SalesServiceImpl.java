/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.service.impl;

import cc.altius.dao.SalesDao;
import cc.altius.model.DTO.SalesReportDTO;
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
    public int addSales(Sales sales, int userId) {
        return this.salesDao.addSales(sales, userId);
    }

    @Override
    public SalesReportDTO getSalesListReportByDate(String startDate) {
        return this.salesDao.getSalesListReportByDate(startDate);
    }

    @Override
    public boolean isExitRecord(String submitDate, int storeId) {
        return this.salesDao.isExitRecord(submitDate, storeId);
    }
}
