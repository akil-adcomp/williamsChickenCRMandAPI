/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model.DTO.Mapper;

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
public class FCWDetailsRowMapper implements RowMapper<FCW> {

    @Override
    public FCW mapRow(ResultSet rs, int i) throws SQLException {

        FCW data = new FCW();
        data.setAmount(rs.getInt("AMOUNT"));
        data.setInvoice(rs.getString("INVOICE"));
        data.setOfChickenPur(rs.getInt("OF_CHICKEN_PUR"));
        Vendor vendor = new Vendor();
        vendor.setVendorId(rs.getInt("VENDOR_ID"));
        vendor.setVendorName(rs.getString("VENDOR_NAME"));
        data.setVendor(vendor);
        return data;
    }
}
