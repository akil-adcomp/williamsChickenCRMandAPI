/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.dao.impl;

import cc.altius.utils.DateUtils;
import cc.altius.williamsChicken.dao.EmailerDao;
import cc.altius.williamsChicken.model.DTO.mapper.EmailRowMapper;
import cc.altius.williamsChicken.model.Email;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

/**
 *
 * @author altius
 */
@Repository
public class EmailerDaoImpl implements EmailerDao {

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
    public int addEmail(Email email) {
        Date curDate = DateUtils.getCurrentDateObject(DateUtils.IST);
        email.setCreatedDate(curDate);
        email.setLastModifiedDate(curDate);
        email.setToSendDate(curDate);
        SimpleJdbcInsert emailInsert = new SimpleJdbcInsert(this.dataSource).withTableName("emailer").usingGeneratedKeyColumns("EMAIL_ID");
        Map<String, Object> params = new HashMap<>();
        params.put("TO_SEND", email.getToSend());
        params.put("CC_SEND_TO", email.getCcToSend());
        params.put("SUBJECT", email.getSubject());
        params.put("BODY", email.getBody());
        params.put("CREATED_DATE", email.getCreatedDate());
        params.put("TO_SEND_DATE", email.getToSendDate());
        params.put("LAST_MODIFIED_DATE", email.getLastModifiedDate());
        params.put("RESPONSE", email.getResponse());
        params.put("ATTEMPTS", email.getAttempts());
        params.put("STATUS", email.getStatus());
        int emailId = emailInsert.executeAndReturnKey(params).intValue();
        return emailId;
    }

    @Override
    public void sendEmail() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Email> getEmailList() {
        String sql = "SELECT * FROM emailer e WHERE e.`STATUS`=0;";
        return jdbcTemplate.query(sql, new EmailRowMapper());
    }

    @Override
    public void updateEmail(int status, int attempts, int emailerId, String response) {
        String sql = "UPDATE emailer e SET e.`ATTEMPTS`=?,e.`STATUS`=?,e.`RESPONSE`=?"
                + " WHERE e.`EMAIL_ID`=?";
        this.jdbcTemplate.update(sql, attempts, status, response, emailerId);
    }

    @Override
    public int updatePasswordByEmailId(String emailId, String password) {
        Date offsetDate = DateUtils.getOffsetFromCurrentDateObject(DateUtils.IST, -1);
        String sql = "UPDATE `user` u SET u.`PASSWORD`=?,u.`EXPIRES_ON`=? WHERE u.`EMAIL_ID`=?";
        return jdbcTemplate.update(sql, password, offsetDate, emailId);
    }

    @Override
    public boolean isExitUser(String emailId) {
        try {
            String sql = "SELECT IF(u.`EMAIL_ID` IS NOT NULL,1,0) AS userCount FROM `user` u WHERE u.`EMAIL_ID`=?";
            Integer userId = this.jdbcTemplate.queryForObject(sql, Integer.class, emailId);
            if (userId != 0) {
                return true;
            } else {
                return false;
            }
        } catch (IncorrectResultSizeDataAccessException rsd) {
            return false;
        }
    }
}
