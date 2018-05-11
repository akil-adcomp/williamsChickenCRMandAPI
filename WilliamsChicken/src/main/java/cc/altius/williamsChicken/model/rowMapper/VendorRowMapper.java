/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model.rowMapper;

import cc.altius.williamsChicken.model.User;
import cc.altius.williamsChicken.model.Vendor;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author manish
 */
public class VendorRowMapper implements RowMapper<Vendor> {
    
    @Override
    public Vendor mapRow(ResultSet rs, int rowNum) throws SQLException {
        Vendor data = new Vendor();
        data.setVendorId(rs.getInt("VENDOR_ID"));
        data.setVendorName(rs.getString("VENDOR_NAME"));
        data.setCity(rs.getString("VENDOR_CITY"));
        data.setStateId(rs.getInt("VENDOR_STATE_ID"));
        data.setStateName(rs.getString("STATE_NAME"));
        data.setVendorAddress(rs.getString("VENDOR_ADDRESS"));
        data.setCreatedDate(rs.getDate("CREATED_DATE"));
        data.setCreatedBy(rs.getInt("CREATED_BY"));
        data.setLastModifiedDate(rs.getDate("LAST_MODIFIED_DATE"));
        data.setLastModifiedBy(rs.getInt("LAST_MODIFIED_BY"));
        data.setActive(rs.getBoolean("ACTIVE"));
        User u = new User();
        u.setUserId(rs.getInt("CREATED_BY"));
        u.setUsername(rs.getString("USERNAME"));
        data.setUser(u);
        return data;
    }
}
