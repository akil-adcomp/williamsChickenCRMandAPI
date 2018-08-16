/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model.rowMapper;

import cc.altius.williamsChicken.model.FCW;
import cc.altius.williamsChicken.model.Store;
import cc.altius.williamsChicken.model.User;
import cc.altius.williamsChicken.model.Vendor;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author altius
 */
public class FCWRowMapper implements RowMapper<FCW> {

    @Override
    public FCW mapRow(ResultSet rs, int i) throws SQLException {
        FCW data = new FCW();
        data.setAmount(rs.getInt("AMOUNT"));
//        data.setTotalAmount(rs.getDouble("TOTAL_AMOUNT"));
//        data.setFcwId(rs.getInt("FCW_ID"));
        data.setInvoice(rs.getString("INVOICE"));
//        data.setOfChickenPur(rs.getInt("OF_CHICKEN_PUR"));
//        data.setPaidOutAmount(rs.getDouble("PAID_OUT_AMOUNT"));


        Store store = new Store();
        store.setStoreId(rs.getInt("STORE_ID"));
        store.setStoreName(rs.getString("STORE_NAME"));
        data.setStore(store);

        User user = new User();
        user.setUserId(rs.getInt("CREATED_BY"));
        user.setUsername(rs.getString("USERNAME"));
        data.setUser(user);

        data.setSubmitDate(rs.getDate("SUBMIT_DATE"));

        Vendor vendor = new Vendor();
        vendor.setVendorId(rs.getInt("VENDOR_ID"));
        vendor.setVendorName(rs.getString("VENDOR_NAME"));
        data.setVendor(vendor);
        data.setCreatedBy(rs.getInt("CREATED_BY"));
        data.setCreatedDate(rs.getDate("date"));
//        data.setLastModifiedBy(rs.getInt("LAST_MODIFIED_BY"));
//        data.setLastModifiedDate(rs.getDate("LAST_MODIFIED_DATE"));
        return data;
    }
}
