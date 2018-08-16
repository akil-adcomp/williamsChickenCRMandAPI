/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.service;

import cc.altius.model.FCW;
import java.util.List;

/**
 *
 * @author altius
 */
public interface FCWService {

    public int addFCW(List<FCW> fcw, int userId);

    public List<FCW> getFCWReportByDate(String startDate, String endDate);

    public double getTotalPaidOutsByDateAndStoreId(String submitDate, int storeId);

    public boolean isFCWRecordExit(String submitDate, int storeId);
}
