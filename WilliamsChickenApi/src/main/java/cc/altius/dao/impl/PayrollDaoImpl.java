/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.dao.impl;

import cc.altius.dao.PayrollDao;
import cc.altius.model.Payroll;
import cc.altius.model.mapper.PayrollRowMapper;
import cc.altius.utils.DateUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * @author manish
 */
@Repository
public class PayrollDaoImpl implements PayrollDao {

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
    public int addPayroll(List<Payroll> payroll, int userId) {
        int payrollId = 0;
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMDHMS);
        String sql = "INSERT INTO payroll VALUE(NULL,?,?,?,?,?,?,?,?,?,?)";
        List<Object[]> batchParams = new ArrayList<>();
        for (Payroll data : payroll) {
            Date cdate = null;
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try {
                cdate = (Date) dateFormat.parse(data.getSubmitDate());
            } catch (ParseException ex) {
                Logger.getLogger(PayrollDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            Object[] tmp = {data.getStore().getStoreId(), data.getEmployee().getEmployeeId(), data.getRegHour(), data.getOt(), data.getPayRate(), cdate, curDate, userId, curDate, userId};
            batchParams.add(tmp);
        }

        int[] insertIds = this.jdbcTemplate.batchUpdate(sql, batchParams);
        if (insertIds.length > 0) {
            payrollId = 1;
        } else {
            payrollId = 0;
        }
        return payrollId;
    }

    @Override
    public List<Payroll> getPayrollReportByDate(String startDate, String stopDate) {
        startDate += " 00:00:00";
        stopDate += " 23:59:59";
        String sql = "SELECT p.*,u.`USER_ID`,u.`USERNAME`, st.`STORE_ID`,st.`STORE_NAME`,e.`EMAPLOYEE_ID`,CONCAT(e.`FIRST_NAME` ' ' e.`LAST_NAME`) AS `name`FROM payroll p "
                + " LEFT JOIN store st ON s.`STORE_ID` = st.`STORE_ID`"
                + " LEFT JOIN employee e ON p.`EMPLOYEE_ID`=e.`EMAPLOYEE_ID`"
                + " LEFT JOIN `user` u ON p.`CREATED_BY`=u.`USER_ID`"
                + " WHERE p.`CREATED_DATE` BETWEEN ? AND ?";
        return this.jdbcTemplate.query(sql, new PayrollRowMapper(), startDate, stopDate);
    }
}
