/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.dao.impl;

import cc.altius.dao.SalesDao;
import cc.altius.model.DTO.Mapper.SalesReportDTOMapper;
import cc.altius.model.DTO.SalesReportDTO;
import cc.altius.model.Sales;
import cc.altius.utils.DateUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

/**
 *
 * @author altius
 */
@Repository
public class SalesDaoImpl implements SalesDao {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public int addSales(Sales sales, int userId) {
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMDHMS);
        SimpleJdbcInsert salesInsert = new SimpleJdbcInsert(this.dataSource).withTableName("sales").usingGeneratedKeyColumns("SALES_ID");
        Map<String, Object> params = new HashMap<>();
        Date cdate = null;
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        try {
            cdate = (Date) dateFormat.parse(sales.getSubmitDate());
        } catch (ParseException ex) {
            Logger.getLogger(SalesDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        params.put("STORE_ID", sales.getStore().getStoreId());
        params.put("SUBMIT_DATE", cdate);
        params.put("TOTAL_SALES", sales.getTotalSales());
        params.put("NON_TAX_SALES", sales.getNonTaxSales());
        params.put("NET_SALES", sales.getNetSales());
        params.put("SALES_TAX", sales.getSalesTax());
        params.put("GROSS_SALES", sales.getGrossSales());
        params.put("ACCOUNT_RECEIVABLE", sales.getAcounntReceivable());
        params.put("TOTAl_PAID_OUT", sales.getTotalPaidOut());
        params.put("UBER_ACCOUNT", sales.getUberAccount());
        params.put("AMOUNT_PER_BIRD", sales.getAmountPerBird());
        params.put("BEGNING_HEAD_COUNT", sales.getBegningHeadCount());
        params.put("STORE_TRANSFER", sales.getStoreTransfer());
        params.put("PURCHASE", sales.getPurchase());
        params.put("CHICKEN_USAGE", sales.getChickenUsage());
        params.put("BIRDS_WASTED", sales.getBirdsWasted());
        params.put("BIRDS_ON_HAND", sales.getBirdsOnHand());
        params.put("ENDING_INVENTORY", sales.getEndingEnventory());
        params.put("VARIANCE", sales.getVariance());
        params.put("CREATED_DATE", curDate);
        params.put("CREATED_BY", userId);
        params.put("LAST_MODIFIED_DATE", curDate);
        params.put("LAST_MODIFIED_BY", userId);
        params.put("CUSTOMER_COUNT", sales.getCustomerCount());
        params.put("TOTAL_DEPOSIT", sales.getTotalDeposit());
        params.put("DOOR_DASH_ACCOUNT", sales.getDoorDashAccount());
        params.put("CASH", sales.getCash());
        params.put("CHECK_AVERAGE", sales.getCheckAverage());
        params.put("REFOUNDS", sales.getRefounds());

        return salesInsert.executeAndReturnKey(params).intValue();
    }

    @Override
    public SalesReportDTO getSalesListReportByDate(String startDate, int storeId) {
        String sql = "SELECT SUM(s.`TOTAL_SALES`) - SUM(s.`NON_TAX_SALES`) AS salesLastWeek FROM sales s"
                + " WHERE s.`SUBMIT_DATE`= ?";
        return this.jdbcTemplate.queryForObject(sql, new SalesReportDTOMapper(), startDate);
    }

    @Override
    public boolean isExitRecord(String submitDate, int storeId) {
        String sql = "SELECT COUNT(s.`SUBMIT_DATE`) AS rem FROM sales s"
                + " WHERE s.`SUBMIT_DATE` = ? AND s.`STORE_ID`= ?";
        return this.jdbcTemplate.queryForObject(sql, boolean.class, submitDate, storeId);

    }

    @Override
    public int getNetSalesLastWeekReportByDate(String submitDate, int storeId) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            try {
                cal.setTime(dateFormat.parse(submitDate));
            } catch (ParseException ex) {
                Logger.getLogger(SalesDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            cal.add(Calendar.DATE, -7);
            Date todate1 = cal.getTime();
            String netSalesLastWeek = dateFormat.format(todate1);

            String sql = "SELECT s.`NET_SALES` FROM sales s WHERE s.`STORE_ID` =? AND s.`SUBMIT_DATE`=?;";
            return this.jdbcTemplate.queryForObject(sql, Integer.class, storeId, netSalesLastWeek);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int getNetSalesLastYearReportByDate(String submitDate, int storeId) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            try {
                cal.setTime(dateFormat.parse(submitDate));
            } catch (ParseException ex) {
                Logger.getLogger(SalesDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            cal.add(Calendar.YEAR, -1);
            String netSalesLastYears = dateFormat.format(cal.getTime());

            String sql = "SELECT s.`NET_SALES` FROM sales s WHERE s.`STORE_ID` =? AND s.`SUBMIT_DATE`=?;";
            return this.jdbcTemplate.queryForObject(sql, Integer.class, storeId, netSalesLastYears);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int getSalesLastBegningHeadCountByDate(String submitDate, int storeId) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            try {
                cal.setTime(dateFormat.parse(submitDate));
            } catch (ParseException ex) {
                Logger.getLogger(SalesDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            cal.add(Calendar.DATE, -1);
            Date todate1 = cal.getTime();
            String begningHeadCount = dateFormat.format(todate1);

            String sql = "SELECT s.`ENDING_INVENTORY` FROM sales s"
                    + " WHERE s.`STORE_ID`=? AND s.`SUBMIT_DATE`=?";
            return this.jdbcTemplate.queryForObject(sql, Integer.class, storeId, begningHeadCount);
        } catch (Exception e) {
            return 0;
        }
    }
}
