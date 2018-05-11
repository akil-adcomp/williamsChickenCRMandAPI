/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model.DTO.mapper;

import cc.altius.williamsChicken.model.DTO.FCWReportDTO;
import cc.altius.williamsChicken.model.FCWStoreDetails;
import cc.altius.williamsChicken.model.Store;
import cc.altius.williamsChicken.model.User;
import cc.altius.williamsChicken.model.Vendor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        String newCreated = null;
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String oldCreatedDate = sdf.format(d);
        List<FCWReportDTO> fcwReportList = new LinkedList<>();
        FCWReportDTO fcw = new FCWReportDTO();
        boolean isFirst = true;
        while (rs.next()) {
            newStoreId = rs.getInt("STORE_ID");
            newCreated = sdf.format(rs.getDate("date"));
            if (oldStoreId != newStoreId || !oldCreatedDate.equals(newCreated)) {
                if (!isFirst) {
                    fcwReportList.add(fcw);
                }
                isFirst = false;
                fcw = new FCWReportDTO();
                fcw.setDate(rs.getDate("date"));
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
            fcwStore.setPaidAmount(rs.getDouble("PAID_OUT_AMOUNT"));
            fcw.getStoreDetails().add(fcwStore);
            fcw.addChickenTotal(fcwStore.getChickenNo());
            fcw.addTotalAmount(fcwStore.getAmount());
            fcw.addTotalPaidAmout(fcwStore.getPaidAmount());
            oldStoreId = newStoreId;
            oldCreatedDate = newCreated;
        }
        if (!isFirst) {
            fcwReportList.add(fcw);
        }
        return fcwReportList;
    }
}
