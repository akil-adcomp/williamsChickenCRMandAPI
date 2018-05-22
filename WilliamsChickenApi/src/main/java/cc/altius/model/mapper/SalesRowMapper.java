/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model.mapper;

import cc.altius.model.Sales;
import cc.altius.model.Store;
import cc.altius.model.User;
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
        data.setAcounntReceivable(rs.getDouble("ACCOUNT_RECEIVABLE"));
        data.setAmountPerBird(rs.getDouble("AMOUNT_PER_BIRD"));
        data.setBegningHeadCount(rs.getInt("BEGNING_HEAD_COUNT"));
        data.setBirdsOnHand(rs.getInt("BIRDS_ON_HAND"));
        data.setBirdsWasted(rs.getInt("BIRDS_WASTED"));
        data.setChickenUsage(rs.getInt("CHICKEN_USAGE"));
        data.setCreatedBy(rs.getInt("CREATED_BY"));
        data.setCreatedDate(rs.getDate("CREATED_DATE"));
        data.setEndingEnventory(rs.getInt("ENDING_INVENTORY"));
        data.setGrossSales(rs.getDouble("GROSS_SALES"));
        data.setLastModifiedBy(rs.getInt("LAST_MODIFIED_DATE"));
        data.setLastModifiedDate(rs.getDate("LAST_MODIFIED_DATE"));
        data.setNetSales(rs.getDouble("NET_SALES"));
        data.setNonTaxSales(rs.getDouble("NON_TAX_SALES"));
        data.setOfficerDiscount(rs.getDouble("OFFICER_DISCOUNT"));
        data.setPurchase(rs.getInt("PURCHASE"));
        data.setSalesId(rs.getInt("SALE_ID"));
        data.setSalesTax(rs.getDouble("SALES_TAX"));
        data.setStoreTransfer(rs.getInt("STORE_TRANSFER"));
        data.setSubmitDate(rs.getString("SUBMIT_DATE"));
        data.setTotalPaidOut(rs.getDouble("TOTAL_PAID_OUT"));
        data.setTotalSales(rs.getDouble("TOTAL_SALES"));
        data.setVariance(rs.getInt("VARIANCE"));
        Store s = new Store();
        s.setStoreId(rs.getInt("STORE_ID"));
        s.setStoreName(rs.getString("STORE_NAME"));
        data.setStore(s);
        return data;
    }
}
