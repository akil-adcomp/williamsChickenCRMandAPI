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
        data.setNetselsLastWeek(rs.getInt("NET_SALES_LAST_WEEK"));
        data.setNonTaxSales(rs.getInt("NON_TAX_SALES"));
        data.setSalesId(rs.getInt("SALES_ID"));
        data.setSalesTax(rs.getDouble("SALES_TAX"));
        data.setNetSalesLastYear(rs.getDouble("NET_SALES_LAST_YEAR"));

        Store store = new Store();
        store.setStoreId(rs.getInt("STORE_ID"));
        store.setStoreName(rs.getString("STORE_NAME"));
        data.setStore(store);

        data.setSubmitDate(rs.getDate("SUBMIT_DATE"));
        data.setTotalSales(rs.getInt("TOTAL_SALES"));

        User user = new User();
        user.setUserId(rs.getInt("CREATED_BY"));
        user.setUsername(rs.getString("USERNAME"));
        data.setUser(user);
        return data;
    }
}
