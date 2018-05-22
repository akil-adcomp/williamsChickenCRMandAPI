/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model.rowMapper;

import cc.altius.williamsChicken.model.Sales;
import cc.altius.williamsChicken.model.Store;
import cc.altius.williamsChicken.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author altius
 */
public class SalesRowMapper implements RowMapper<Sales> {

    @Override
    public Sales mapRow(ResultSet rs, int i) throws SQLException {
        Sales data = new Sales();
        data.setCreatedBy(rs.getInt("CREATED_BY"));
        data.setCreatedDate(rs.getDate("CREATED_DATE"));
        data.setGrossSales(rs.getDouble("GROSS_SALES"));
        data.setLastModifiedBy(rs.getInt("LAST_MODIFIED_BY"));
        data.setLastModifiedDate(rs.getDate("LAST_MODIFIED_DATE"));
        data.setNetSales(rs.getDouble("NET_SALES"));
        return data;
    }
}
