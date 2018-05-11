/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model.mapper;

import cc.altius.model.Vendor;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author manish
 */
public class VendorRowMapper implements RowMapper<Vendor> {

    @Override
    public Vendor mapRow(ResultSet rs, int i) throws SQLException {
        Vendor data = new Vendor();
        data.setVendorId(rs.getInt("VENDOR_ID"));
        data.setVendorName(rs.getString("VENDOR_NAME"));
        return data;
    }
}
