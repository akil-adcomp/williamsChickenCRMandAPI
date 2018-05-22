/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.dao.impl;

import cc.altius.dao.SalesDao;
import cc.altius.model.DTO.Mapper.SalesReportDTOMapper;
import cc.altius.model.DTO.SalesReportDTO;
import cc.altius.model.Sales;
import cc.altius.model.mapper.SalesRowMapper;
import cc.altius.utils.DateUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
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
        params.put("ACOUNT_RECEIVABLE", sales.getAcounntReceivable());
        params.put("TOTAl_PAID_OUT", sales.getTotalPaidOut());
        params.put("OFFICER_DISCOUNT", sales.getOfficerDiscount());
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

        return salesInsert.executeAndReturnKey(params).intValue();
    }

    @Override
    public SalesReportDTO getSalesListReportByDate(String startDate) {
        String sql = "SELECT SUM(s.`TOTAL_SALES`) - SUM(s.`NON_TAX_SALES`) AS salesLastWeek FROM sales s"
                + " WHERE s.`SUBMIT_DATE`= ?";
        return this.jdbcTemplate.queryForObject(sql, new SalesReportDTOMapper(), startDate);
    }

    @Override
    public boolean isExitRecord(String submitDate) {
        String sql = "SELECT COUNT(s.`SUBMIT_DATE`) AS rem FROM sales s"
                + " WHERE s.`SUBMIT_DATE` = ?";
        return this.jdbcTemplate.queryForObject(sql, boolean.class, submitDate);

    }
}
