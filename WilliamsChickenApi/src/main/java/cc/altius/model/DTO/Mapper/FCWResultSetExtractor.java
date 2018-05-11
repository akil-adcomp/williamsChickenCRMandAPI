/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model.DTO.Mapper;

import cc.altius.model.DTO.FCWReportDTO;
import cc.altius.model.FCWStoreDetails;
import cc.altius.model.Store;
import cc.altius.model.User;
import cc.altius.model.Vendor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 *
 * @author altius
 */
public class FCWResultSetExtractor implements ResultSetExtractor<List<FCWReportDTO>> {

    @Override
    public List<FCWReportDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
        int oldStoreId = 0;
        int newStoreId = -1;
        List<FCWReportDTO> fcwReportList = new LinkedList<>();
        FCWReportDTO fcw = new FCWReportDTO();
        boolean isFirst = true;
        while (rs.next()) {
            newStoreId = rs.getInt("STORE_ID");
            if (oldStoreId != newStoreId) {
                if (!isFirst) {
                    fcwReportList.add(fcw);
                }
                isFirst = false;
                fcw = new FCWReportDTO();
                fcw.setDate(rs.getDate("CREATED_DATE"));
                Store s = new Store();
                s.setStoreId(rs.getInt("STORE_ID"));
                s.setStoreName(rs.getString("STORE_NAME"));
                fcw.setStore(s);
                fcw.setSubmitDate(rs.getTimestamp("SUBMIT_DATE"));
                User u = new User();
                u.setUserId(rs.getInt("CREATED_BY"));
                u.setUsername(rs.getString("USERNAME"));
                fcw.setUser(u);
            }
            FCWStoreDetails fcwStore = new FCWStoreDetails();
            fcwStore.setInvoiceNo(rs.getString("INVOICE"));
            Vendor v = new Vendor();
            v.setVendorId(rs.getInt("VENDOR_ID"));
            v.setVendorName(rs.getString("VENDOR_NAME"));
            fcwStore.setVendor(v);
            fcwStore.setAmount(rs.getDouble("AMOUNT"));
            fcwStore.setChickenNo(rs.getInt("OF_CHICKEN_PUR"));
            fcw.getStoreDetails().add(fcwStore);
            fcw.addChickenTotal(fcwStore.getChickenNo());
            fcw.addDailyTotal(fcwStore.getAmount());
            oldStoreId = newStoreId;
        }
        if (!isFirst) {
            fcwReportList.add(fcw);
        }
        return fcwReportList;
    }
}
