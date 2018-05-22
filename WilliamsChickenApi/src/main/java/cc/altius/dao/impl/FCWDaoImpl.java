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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMDHMS);
        String sql1 = "INSERT INTO fcw VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?)";
        List<Object[]> batchParams = new ArrayList<>();
        for (FCW fcw : fcws) {
            Date cdate = null;
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try {
                cdate = (Date) dateFormat.parse(fcw.getSubmitDate());
            } catch (ParseException ex) {
                Logger.getLogger(FCWDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            Object[] tmp = {fcw.getStore().getStoreId(), fcw.getVendor().getVendorId(), fcw.getInvoice(), fcw.getAmount(), cdate, fcw.getPaidOutAmount(), fcw.getOfChickenPur(), curDate, userId, curDate, userId, fcw.getDummyVendor()};
            batchParams.add(tmp);
        }

        int[] insertId = this.jdbcTemplate.batchUpdate(sql1, batchParams);
        if (insertId.length > 0) {
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
}
