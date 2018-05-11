/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.dao.impl;

import cc.altius.williamsChicken.dao.ReportDao;
import cc.altius.williamsChicken.dao.UserDao;
import cc.altius.williamsChicken.model.DTO.AccessLogReportDTO;
import cc.altius.williamsChicken.model.DTO.FCWReportDTO;
import cc.altius.williamsChicken.model.DTO.PayrollReportDTO;
import cc.altius.williamsChicken.model.DTO.mapper.AccessLogReportDTORowMapper;
import cc.altius.williamsChicken.model.FCW;
import cc.altius.williamsChicken.model.Payroll;
import cc.altius.williamsChicken.model.rowMapper.PayrollRowMapper;
import cc.altius.williamsChicken.model.DTO.mapper.FCWResultSetExtractor;
import cc.altius.williamsChicken.model.DTO.mapper.PayrollResultSetExtractor;
import cc.altius.williamsChicken.model.rowMapper.FCWRowMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shrutika
 */
@Repository
public class ReportDaoImpl implements ReportDao {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Autowired
    UserDao userDao;

    /**
     * Method to get the report of access log from specified date, userId,
     * success
     *
     * @param startDate statrtDate is a date from when you want to get the
     * report
     * @param stopDate stopDate is a date till when you want to get the report
     * @param userId userId is used to get the report from a particular user
     * @param success success is used to get the report as per the requirement
     * i.e. succeed/failed
     * @param pageNo
     * @return Returns the list of access log report
     */
    @Override
    public List<AccessLogReportDTO> getAccessLogReport(String startDate, String stopDate, int userId, int success) {
        startDate += " 00:00:00";
        stopDate += " 23:59:59";
        StringBuilder sql = new StringBuilder("SELECT access_log.`ACCESS_DATE`, access_log.`IP`, access_log.`USERNAME`, access_log.`USER_ID`, access_log.`SUCCESS`, access_log.`OUTCOME` FROM access_log WHERE "
                + " access_log.`ACCESS_DATE` BETWEEN :startDate AND :stopDate");

        Map<String, Object> params = new HashMap<>();
        params.put("startDate", startDate);
        params.put("stopDate", stopDate);

        if (userId != -1) {
            sql.append(" AND access_log.`USER_ID`=:userId");
            params.put("userId", userId);
        }

        if (success != -1) {
            sql.append(" AND access_log.`SUCCESS`=:success");
            params.put("success", success);
        }

        NamedParameterJdbcTemplate nm = new NamedParameterJdbcTemplate(jdbcTemplate);

//        LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(sql.toString(), params));
        return nm.query(sql.toString(), params, new AccessLogReportDTORowMapper());
    }

    @Override
    public List<FCWReportDTO> getFCWReport(String startDate, String stopDate) {
        startDate += " 00:00:00";
        stopDate += " 23:59:59";
        String sql = "SELECT DATE(f.`CREATED_DATE`) AS`date`,f.`PAID_OUT_AMOUNT`,f.`STORE_ID`,f.`SUBMIT_DATE`,f.`CREATED_BY`,f.`INVOICE`,f.`VENDOR_ID`,f.`AMOUNT`,f.`OF_CHICKEN_PUR`,s.`STORE_NAME`,v.`VENDOR_NAME`,u.`USERNAME` FROM fcw f "
                + " LEFT JOIN store s ON f.`STORE_ID`=s.`STORE_ID`"
                + " LEFT JOIN vendor v ON f.`VENDOR_ID`=v.`VENDOR_ID`"
                + " LEFT JOIN `user` u ON f.`CREATED_BY`=u.`USER_ID`"
                + " WHERE f.`CREATED_DATE` BETWEEN ? AND ?"
                + " ORDER BY f.`STORE_ID`, DATE(f.`CREATED_DATE`)";
        return this.jdbcTemplate.query(sql, new FCWResultSetExtractor(), startDate, stopDate);
    }

    @Override
    public List<PayrollReportDTO> getPayrollReport(String startDate, String stopDate) {
        startDate += " 00:00:00";
        stopDate += " 23:59:59";
        String sql = "SELECT p.`STORE_ID`,p.`EMPLOYEE_ID`,p.`PAY_RATE`,p.`REG_HOUR`,p.`OT`,p.`PAYROLL_ID`,p.`SUBMIT_DATE`,DATE(p.`CREATED_DATE`) AS `date`,s.`STORE_NAME`,u.`USERNAME`,CONCAT(e.`FIRST_NAME` ,' ',e.`LAST_NAME`) AS `name` FROM payroll p "
                + " LEFT JOIN store s ON p.`STORE_ID`=s.`STORE_ID`"
                + " LEFT JOIN employee e ON e.`EMPLOYEE_ID`=p.`EMPLOYEE_ID`"
                + " LEFT JOIN `user` u ON p.`CREATED_BY`=u.`USER_ID`"
                + " WHERE p.`CREATED_DATE` BETWEEN ? AND ?"
                + " ORDER BY p.`STORE_ID`, DATE(p.`CREATED_DATE`)";
        return this.jdbcTemplate.query(sql, new PayrollResultSetExtractor(), startDate, stopDate);
    }

    @Override
    public List<FCW> getFCWList(String startDate, String stopDate) {
        startDate += " 00:00:00";
        stopDate += " 23:59:59";
        String sql = "SELECT DATE(f.`CREATED_DATE`) AS`date`,f.`PAID_OUT_AMOUNT`,f.`STORE_ID`,f.`SUBMIT_DATE`,f.`CREATED_BY`,f.`INVOICE`,f.`VENDOR_ID`,f.`AMOUNT`,f.`OF_CHICKEN_PUR`,s.`STORE_NAME`,v.`VENDOR_NAME`,u.`USERNAME` FROM fcw f "
                + " LEFT JOIN store s ON f.`STORE_ID`=s.`STORE_ID`"
                + " LEFT JOIN vendor v ON f.`VENDOR_ID`=v.`VENDOR_ID`"
                + " LEFT JOIN `user` u ON f.`CREATED_BY`=u.`USER_ID`"
                + " WHERE f.`CREATED_DATE` BETWEEN ? AND ?"
                + " GROUP BY f.`STORE_ID`, DATE(f.`CREATED_DATE`),f.`VENDOR_ID`";
        return this.jdbcTemplate.query(sql, new FCWRowMapper(), startDate, stopDate);
    }
}
