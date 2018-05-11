/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.dao.impl;

import cc.altius.dao.SalesDao;
import cc.altius.model.Sales;
import cc.altius.model.mapper.SalesRowMapper;
import cc.altius.utils.DateUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public int addSales(Sales sales) {
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMDHMS);
        SimpleJdbcInsert salesInsert = new SimpleJdbcInsert(this.dataSource).withTableName("sales").usingGeneratedKeyColumns("SALES_ID");
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("STORE_ID", sales.getStore().getStoreId());
        params.put("SUBMIT_DATE", sales.getSubmitDate());
        params.put("TOTAL_SALES", sales.getTotalSales());
        params.put("NON_TAX_SALES", sales.getNonTaxSales());
        params.put("NET_SALES", sales.getNetSales());
        params.put("SALES_TAX", sales.getSalesTax());
        params.put("GROSS_SALES", sales.getGrossSales());
        params.put("NET_SALES_LAST_WEEk", sales.getNetselsLastWeek());
        params.put("CREATED_DATE", curDate);
        params.put("CREATED_BY", sales.getUser().getUserId());
        params.put("LAST_MODIFIED_DATE", curDate);
        params.put("LAST_MODIFIED_BY", sales.getUser().getUserId());

        return salesInsert.executeAndReturnKey(params).intValue();
    }

    @Override
    public List<Sales> getSalesListReportByDate(String startDate, String stopDate) {
        startDate += " 00:00:00";
        stopDate += " 23:59:59";
        String sql = "SELECT s.*, st.`STORE_ID`,st.`STORE_NAME`,u.`USER_ID`,u.`USERNAME` FROM sales s "
                + " LEFT JOIN store st ON s.`STORE_ID` = st.`STORE_ID`"
                + " LEFT JOIN `user` u ON s.`CREATED_BY`=u.`USER_ID`"
                + " WHERE s.`CREATED_DATE` BETWEEN ? AND ?";
        return this.jdbcTemplate.query(sql, new SalesRowMapper(), startDate, stopDate);
    }
}
