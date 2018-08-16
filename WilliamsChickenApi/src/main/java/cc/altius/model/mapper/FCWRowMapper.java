/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model.mapper;

import cc.altius.model.FCW;
import cc.altius.model.Store;
import cc.altius.model.User;
import cc.altius.model.Vendor;
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
        data.setFcwId(rs.getInt("FCW_ID"));
        data.setInvoice(rs.getString("INVOICE"));
//        data.setOfChickenPur(rs.getInt("OF_CHICKEN_PUR"));
        data.setPaidOutAmount(rs.getInt("PAID_OUTS"));
        data.setNoOfStoreTranscation(rs.getInt("NO_OF_STORE_TRANSACTION"));

        Store store = new Store();
        store.setStoreId(rs.getInt("STORE_ID"));
        store.setStoreName(rs.getString("STORE_NAME"));
        data.setStore(store);

        data.setSubmitDate(rs.getString("SUBMIT_DATE"));

        Vendor vendor = new Vendor();
        vendor.setVendorId(rs.getInt("VENDOR_ID"));
        vendor.setVendorName(rs.getString("VENDOR_NAME"));
        data.setVendor(vendor);
        data.setCreatedBy(rs.getInt("CREATED_BY"));
        data.setCreatedDate(rs.getDate("CREATED_DATE"));
        data.setLastModifiedBy(rs.getInt("LAST_MODIFIED_BY"));
        data.setLastModifiedDate(rs.getDate("LAST_MODIFIED_DATE"));
        data.setDummyVendor(rs.getString("DUMMY_VENDOR"));
        return data;
    }
}
