/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.dao.impl;

import cc.altius.dao.BankRegistrationDao;
import cc.altius.model.BankRegister;
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
 * @author Nikhil Pande
 */
@Repository
public class BankRegistrationDaoImpl implements BankRegistrationDao {

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
    public int addBankRegister(List<BankRegister> list, int userId) {
        int bankRegId = 1;
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD);
        String sql1 = "INSERT INTO bank_register VALUES (NULL,?,?,?,?,?,?,?,?,?);";
        List<Object[]> batchParams = new ArrayList<>();
        for (BankRegister br : list) {
            Date cdate = null;
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            try {
                cdate = (Date) dateFormat.parse(br.getSubmitDate());
            } catch (ParseException ex) {
                Logger.getLogger(FCWDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            Object[] tmp = {br.getInitials(), br.getStoreId(), br.getPaymentMode().getPayemntModeId(), br.getAmount(), cdate, curDate, userId, curDate, userId};
            batchParams.add(tmp);
        }

        int[] insertId = this.jdbcTemplate.batchUpdate(sql1, batchParams);
        if (insertId.length > 0) {
            bankRegId = insertId.length;
        } else {
            bankRegId = 0;
        }
        return bankRegId;
    }

    @Override
    public double getTotalDepositsByDateAndStoreId(String submitDate, int storeId) {
        double totalAmount = 0.00;
        String sql = " SELECT SUM(b.`AMOUNT`) AS totalDeposits FROM bank_register b WHERE b.`STORE_ID`=? AND DATE(b.`SUBMIT_DATE`)=?;";
        try {
            totalAmount = this.jdbcTemplate.queryForObject(sql, Integer.class, storeId, submitDate);
        } catch (Exception e) {
//            e.printStackTrace();
            totalAmount = 0.00;
        }
        return totalAmount;
    }

    @Override
    public boolean isBankRegisterRecordExit(String submitDate, int storeId) {
        String sql = " SELECT COUNT(b.`SUBMIT_DATE`) AS rem FROM bank_register b WHERE DATE(b.`SUBMIT_DATE`)=? AND b.`STORE_ID`=?;";
        return this.jdbcTemplate.queryForObject(sql, boolean.class, submitDate, storeId);
    }

}
