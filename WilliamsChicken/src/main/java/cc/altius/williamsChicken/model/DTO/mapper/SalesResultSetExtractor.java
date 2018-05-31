/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model.DTO.mapper;

import cc.altius.williamsChicken.model.DTO.SalesReportDTO;
import cc.altius.williamsChicken.model.SalesDetails;
import cc.altius.williamsChicken.model.Store;
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
public class SalesResultSetExtractor implements ResultSetExtractor<List<SalesReportDTO>> {
    
    @Override
    public List<SalesReportDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
        int oldStoreId = 0;
        int newStoreId = -1;
        String newCreated = null;
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String oldCreatedDate = sdf.format(d);
        List<SalesReportDTO> salesReportList = new LinkedList<>();
        SalesReportDTO sales = new SalesReportDTO();
        boolean isFirst = true;
        while (rs.next()) {
            newCreated = sdf.format(rs.getDate("date"));
            newStoreId = rs.getInt("STORE_ID");
            if (oldStoreId != newStoreId || !oldCreatedDate.equals(newCreated)) {
                if (!isFirst) {
                    salesReportList.add(sales);
                }
                isFirst = false;
                sales = new SalesReportDTO();
                Store s = new Store();
                s.setStoreId(rs.getInt("STORE_ID"));
                s.setStoreName(rs.getString("STORE_NAME"));
                sales.setStore(s);
                sales.setDate(rs.getDate("date"));
                sales.setBegningHeadCount(rs.getInt("BEGNING_HEAD_COUNT"));
                sales.setBirdsOnHand(rs.getInt("BIRDS_ON_HAND"));
                sales.setBirdsWasted(rs.getInt("BIRDS_WASTED"));
                
                sales.setChickenUsage(rs.getInt("CHICKEN_USAGE"));
                sales.setEndingEnventory(rs.getInt("ENDING_INVENTORY"));
                sales.setStoreTransfer(rs.getInt("STORE_TRANSFER"));
                sales.setPurchase(rs.getInt("PURCHASE"));
                sales.setVariance(rs.getInt("VARIANCE"));
                sales.setSubmitDate(rs.getTimestamp("SUBMIT_DATE"));
            }
            
            SalesDetails salesDetails = new SalesDetails();
            salesDetails.setAccountReceivable(rs.getDouble("ACCOUNT_RECEIVABLE"));
            salesDetails.setAmountPerBird(rs.getDouble("AMOUNT_PER_BIRD"));
            salesDetails.setGrossSales(rs.getDouble("GROSS_SALES"));
            salesDetails.setNetSales(rs.getDouble("NET_SALES"));
            salesDetails.setNonTaxSales(rs.getDouble("NON_TAX_SALES"));
            salesDetails.setUberAccount(rs.getDouble("UBER_ACCOUNT"));
            salesDetails.setSalesTax(rs.getDouble("SALES_TAX"));
            salesDetails.setTotalPaidOut(rs.getDouble("TOTAL_PAID_OUT"));
            salesDetails.setTotalSales(rs.getDouble("TOTAL_SALES"));
            salesDetails.setTotalDeposit(rs.getDouble("TOTAL_DEPOSIT"));
            salesDetails.setDoorDashAccount(rs.getDouble("DOOR_DASH_ACCOUNT"));
            salesDetails.setCash(rs.getDouble("CASH"));
            salesDetails.setCheckAverage(rs.getDouble("CHECK_AVERAGE"));
            salesDetails.setRefounds(rs.getDouble("REFOUNDS"));
            sales.getSalesDetails().add(salesDetails);
            oldStoreId = newStoreId;
            oldCreatedDate = newCreated;
        }
        if (!isFirst) {
            salesReportList.add(sales);
        }
        return salesReportList;
    }
}
