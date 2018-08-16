/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.dao.impl;

import cc.altius.dao.FCWDao;
import cc.altius.model.DTO.Mapper.FCWDetailsRowMapper;
import cc.altius.model.FCW;
import cc.altius.model.mapper.FCWRowMapper;
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
 * @author altius
 */
@Repository
public class FCWDaoImpl implements FCWDao {

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
    public int addFCW(List<FCW> fcws, int userId) {
        int fcwId = 1;
        FCW fcwObj = fcws.get(0);
        Date cdate = null;
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        try {
            cdate = (Date) dateFormat.parse(fcwObj.getSubmitDate());
        } catch (ParseException ex) {
            Logger.getLogger(FCWDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        int storeId = fcwObj.getStore().getStoreId();
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMDHMS);
        String sql1 = "INSERT INTO fcw VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?)";
        List<Object[]> batchParams = new ArrayList<>();
        for (FCW fcw : fcws) {
            Object[] tmp = {storeId, fcw.getVendor().getVendorId(), fcw.getInvoice(), fcw.getAmount(), cdate, fcw.getNoOfStoreTranscation(), curDate, userId, curDate, userId, fcw.getDummyVendor()};
            batchParams.add(tmp);
        }

        int[] insertId = this.jdbcTemplate.batchUpdate(sql1, batchParams);
        if (insertId.length > 0) {
            SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("fcw_paid_outs");
            Map<String, Object> params = new HashMap<>();
            params.put("STORE_ID", storeId);
            params.put("SUBMIT_DATE", cdate);
            params.put("PAID_OUTS", fcwObj.getPaidOutAmount());
            insert.execute(params);

            fcwId = 1;
        } else {
            fcwId = 0;
        }
        return fcwId;
    }

    @Override
    public List<FCW> getFCWReportByDate(String startDate, String stopDate) {

        startDate += " 00:00:00";
        stopDate += " 23:59:59";

        String sql = "SELECT f.*,u.`USER_ID`,u.`USERNAME`,s.`STORE_ID`,s.`STORE_NAME`,v.`VENDOR_ID`,v.`VENDOR_NAME`FROM fcw f"
                + " LEFT JOIN vendor v ON f.`VENDOR_ID`=v.`VENDOR_ID`"
                + " LEFT JOIN store s ON f.`STORE_ID`=s.`STORE_ID`"
                + " LEFT JOIN `user` u ON f.`CREATED_BY`=u.`USER_ID`"
                + " WHERE f.`CREATED_DATE` BETWEEN ? AND ?;";
        return this.jdbcTemplate.query(sql, new FCWRowMapper(), startDate, stopDate);
    }

    @Override
    public List<FCW> getFCWDetailsReport(String startDate, String stopDate) {
        startDate += " 00:00:00";
        stopDate += " 23:59:59";
        String sql = "SELECT v.`VENDOR_NAME`,f.`INVOICE`,f.`AMOUNT`,f.`OF_CHICKEN_PUR`FROM fcw f"
                + " LEFT JOIN vendor v ON f.`VENDOR_ID`=v.`VENDOR_ID`"
                + " WHERE f.`CREATED_DATE` BETWEEN ? AND ?";
        return this.jdbcTemplate.query(sql, new FCWDetailsRowMapper(), startDate, stopDate);
    }

    @Override
    public double getTotalPaidOutsByDateAndStoreId(String submitDate, int storeId) {
        double totalPaidOuts = 0.00;
        String sql = " SELECT SUM(f.`PAID_OUTS`) AS paidOuts FROM fcw_paid_outs f WHERE f.`STORE_ID`=? AND DATE(f.`SUBMIT_DATE`)=?;";
        try {
            totalPaidOuts = this.jdbcTemplate.queryForObject(sql, Integer.class, storeId, submitDate);
        } catch (Exception e) {
            totalPaidOuts = 0.00;
        }
        return totalPaidOuts;
    }

    @Override
    public boolean isFCWRecordExit(String submitDate, int storeId) {
        String sql = " SELECT COUNT(b.`SUBMIT_DATE`) AS rem FROM fcw b WHERE DATE(b.`SUBMIT_DATE`)=? AND b.`STORE_ID`=?;";
        return this.jdbcTemplate.queryForObject(sql, boolean.class, submitDate, storeId);
    }

}
