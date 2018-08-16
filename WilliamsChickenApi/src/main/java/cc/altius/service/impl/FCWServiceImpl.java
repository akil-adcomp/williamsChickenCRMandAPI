/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.service.impl;

import cc.altius.dao.FCWDao;
import cc.altius.model.FCW;
import cc.altius.service.FCWService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author altius
 */
@Service
public class FCWServiceImpl implements FCWService {

    @Autowired
    private FCWDao fCWDao;

    @Override
    public int addFCW(List<FCW> fcw, int userId) {
        return this.fCWDao.addFCW(fcw, userId);
    }

    @Override
    public List<FCW> getFCWReportByDate(String startDate, String endDate) {

        List<FCW> fcwList = this.fCWDao.getFCWReportByDate(startDate, endDate);

        return fcwList;
    }

    @Override
    public double getTotalPaidOutsByDateAndStoreId(String submitDate, int storeId) {
        return this.fCWDao.getTotalPaidOutsByDateAndStoreId(submitDate, storeId);
    }

    @Override
    public boolean isFCWRecordExit(String submitDate, int storeId) {
        return this.fCWDao.isFCWRecordExit(submitDate, storeId);
    }

}
