/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.dao.impl;

import cc.altius.dao.SummaryDao;
import cc.altius.model.BankRegister;
import cc.altius.model.DTO.SalesSummaryDTO;
import cc.altius.model.FCW;
import cc.altius.model.Payroll;
import cc.altius.model.Sales;
import cc.altius.model.mapper.BankRegisterRowMapper;
import cc.altius.model.mapper.FCWRowMapper;
import cc.altius.model.mapper.PayrollRowMapper;
import cc.altius.model.mapper.SalesRowMapper;
import cc.altius.utils.DateUtils;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author altius
 */
@Repository
public class SummaryDaoImpl implements SummaryDao {

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
    public List<FCW> getFCWList(int storeId, int periodId) {
        List<FCW> fcwlist = new ArrayList<>();
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD);
        String startDate = null;
        String stopDate = null;
        String sql = "SELECT f.*,st.`STORE_NAME`,fpo.`SUBMIT_DATE`,fpo.`PAID_OUTS`,v.`VENDOR_NAME` "
                + " FROM fcw f "
                + " LEFT JOIN store st ON st.`STORE_ID` = f.`STORE_ID` "
                + " LEFT JOIN fcw_paid_outs fpo ON fpo.`STORE_ID`= f.`STORE_ID` "
                + " LEFT JOIN vendor v ON v.`VENDOR_ID` = f.`VENDOR_ID` "
                + " WHERE f.`STORE_ID`= ? AND f.`SUBMIT_DATE` BETWEEN ? AND ? "
                + " GROUP BY f.`FCW_ID`;";
        if (periodId == 1) {
            //Current 
            startDate = curDate;
            return this.jdbcTemplate.query(sql, new FCWRowMapper(), storeId, curDate, startDate);
        } else if (periodId == 2) {
            //Yesterday
            startDate = DateUtils.getOffsetFromCurrentDateString(DateUtils.IST, DateUtils.YMD, -1);
            stopDate = DateUtils.getOffsetFromCurrentDateString(DateUtils.IST, DateUtils.YMD, -1);
            return this.jdbcTemplate.query(sql, new FCWRowMapper(), storeId, stopDate, startDate);
        } else if (periodId == 3) {
            //Yesterday
            startDate = DateUtils.getOffsetFromCurrentDateString(DateUtils.IST, DateUtils.YMD, -2);
            stopDate = DateUtils.getOffsetFromCurrentDateString(DateUtils.IST, DateUtils.YMD, -2);
            return this.jdbcTemplate.query(sql, new FCWRowMapper(), storeId, stopDate, startDate);
        }
        return fcwlist;
    }

    @Override
    public List<BankRegister> getBankRegisterList(int storeId, int periodId) {
        List<BankRegister> banklist = new ArrayList<>();
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD);
        String startDate = null;
        String stopDate = null;
        String sql = "SELECT b.* , pm.`PAYMENT_MODE_NAME` "
                + " FROM bank_register b "
                + " LEFT JOIN payment_mode pm ON pm.`PAYMENT_MODE_ID`= b.`PAYMENT_MODE_ID` "
                + " WHERE b.`STORE_ID`= ? AND b.`SUBMIT_DATE` BETWEEN ? AND ?;";
        if (periodId == 1) {
            //Current 
            startDate = curDate;
            return this.jdbcTemplate.query(sql, new BankRegisterRowMapper(), storeId, curDate, startDate);
        } else if (periodId == 2) {
            //Yesterday
            startDate = DateUtils.getOffsetFromCurrentDateString(DateUtils.IST, DateUtils.YMD, -1);
            stopDate = DateUtils.getOffsetFromCurrentDateString(DateUtils.IST, DateUtils.YMD, -1);
            return this.jdbcTemplate.query(sql, new BankRegisterRowMapper(), storeId, stopDate, startDate);
        } else if (periodId == 3) {
            //Yesterday
            startDate = DateUtils.getOffsetFromCurrentDateString(DateUtils.IST, DateUtils.YMD, -2);
            stopDate = DateUtils.getOffsetFromCurrentDateString(DateUtils.IST, DateUtils.YMD, -2);
            return this.jdbcTemplate.query(sql, new BankRegisterRowMapper(), storeId, stopDate, startDate);
        }
        return banklist;
    }

    @Override
    public List<Payroll> getPayrollList(int storeId, String startDate, String stopDate) {
        List<Payroll> payrolllist = new ArrayList<>();
        String sql = "SELECT p.* , e.* , s.* , u.*  FROM payroll p "
                + " LEFT JOIN employee e ON e.`EMPLOYEE_ID`=p.`EMPLOYEE_ID`"
                + " LEFT JOIN store s ON s.`STORE_ID` = e.`STORE_ID`"
                + " LEFT JOIN `user` u ON u.`USER_ID` = p.`CREATED_BY`"
                + " WHERE p.`STORE_ID`=? "
                + " AND p.`START_DATE` = ? AND p.`STOP_DATE`= ? ;";
            return this.jdbcTemplate.query(sql, new PayrollRowMapper(), storeId, startDate, stopDate);
    }

    @Override
    public Sales getSalesData(int storeId, int periodId) {
        Sales sales = new Sales();
        try {

            String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD);
            String startDate = null;
            String stopDate = null;
            String sql = " SELECT  s.* , st.*  FROM sales s "
                    + " LEFT JOIN store st ON st.`STORE_ID` = s.`STORE_ID` "
                    + " WHERE s.`STORE_ID`= ? "
                    + " AND s.`SUBMIT_DATE` BETWEEN ? AND ?;";
            if (periodId == 1) {
                //Current 
                startDate = curDate;
                return this.jdbcTemplate.queryForObject(sql, new SalesRowMapper(), storeId, curDate, startDate);
            } else if (periodId == 2) {
                //Yesterday
                startDate = DateUtils.getOffsetFromCurrentDateString(DateUtils.IST, DateUtils.YMD, -1);
                stopDate = DateUtils.getOffsetFromCurrentDateString(DateUtils.IST, DateUtils.YMD, -1);
                return this.jdbcTemplate.queryForObject(sql, new SalesRowMapper(), storeId, stopDate, startDate);
            } else if (periodId == 3) {
                //2 day ago
                startDate = DateUtils.getOffsetFromCurrentDateString(DateUtils.IST, DateUtils.YMD, -2);
                stopDate = DateUtils.getOffsetFromCurrentDateString(DateUtils.IST, DateUtils.YMD, -2);
                return this.jdbcTemplate.queryForObject(sql, new SalesRowMapper(), storeId, stopDate, startDate);

            }
            return sales;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
