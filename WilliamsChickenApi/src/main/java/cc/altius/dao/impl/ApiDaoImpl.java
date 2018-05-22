/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.dao.impl;

import cc.altius.dao.ApiDao;
import cc.altius.model.DTO.LoginUserDetails;
import cc.altius.model.DTO.Mapper.LoginUserDetailsRowMapper;
import cc.altius.model.Token;
import cc.altius.model.ValidToken;
import cc.altius.model.mapper.TokenRowMapper;
import cc.altius.utils.DateUtils;
import cc.altius.utils.ErrorConstants;
import cc.altius.utils.LogUtils;
import cc.altius.utils.PassPhrase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author altius
 */
@Repository
public class ApiDaoImpl implements ApiDao {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Map<String, Object> checkCustomerToken(String emailId, String password) {
        Map<String, Object> responseMap = new HashMap<>();
        String sql = " SELECT u.`PASSWORD` FROM `user` u WHERE u.`USER_ID`";

        try {
            String dbPassword = this.jdbcTemplate.queryForObject(sql, String.class, emailId);
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog(" dbPassword :" + dbPassword));
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(" dbPassword :" + dbPassword));

            PasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(password, dbPassword)) {
                responseMap.put("isCustomerValid", true);
                LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog(" Valid User "));
                LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(" Valid User "));
            } else {
                LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Invalid Password "));
                LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Invalid Psassword "));
                responseMap.put("isCustomerValid", false);
                responseMap.put("failedReason", "INVALID_PASSWORD");
                responseMap.put("failedValue", ErrorConstants.INVALID_PASSWORD);
            }
            return responseMap;
        } catch (EmptyResultDataAccessException i) {
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("user does not exist "));
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("User Does not exist "));
            responseMap.put("isCustomerValid", false);
            responseMap.put("failedReason", "USER_DOES_NOT_EXIST");
            responseMap.put("failedValue", ErrorConstants.USER_DOES_NOT_EXIST);
            return responseMap;
        }
    }

    @Override
    public Map<String, Object> signIn(String userName, String password) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.HOUR, 6);

        LoginUserDetails loginUserDetails = null;
        Map<String, Object> responseMap = new HashMap<>();

        String sql = "SELECT u.`USERNAME`,u.`USER_ID`,u.`EMAIL_ID`,IF(u.`EXPIRY_DATE`<= NOW(),'',u.`TOKEN`) AS `TOKEN`,u.`AUTHORISED_FLAG`,u.`EXPIRY_DATE`,u.`PASSWORD`,s.`STORE_NAME`,s.`STORE_ID`FROM `user` u "
                + " LEFT JOIN manager m ON u.`USER_ID`=m.`USER_ID`"
                + " LEFT JOIN store_manager_mapping sm ON m.`MANAGER_ID`=sm.`MANAGER_ID`"
                + " LEFT JOIN store s ON sm.`STORE_ID`=s.`STORE_ID`"
                + " WHERE u.`EMAIL_ID`=?";
        try {
            loginUserDetails = this.jdbcTemplate.queryForObject(sql, new LoginUserDetailsRowMapper(), userName);
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(password, loginUserDetails.getUser().getPassword())) {
                LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog(" loginUserDetails :" + loginUserDetails.toString()));
            } else {
                LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Invalid Password "));
                responseMap.put("loginUserDetails", null);
                responseMap.put("failedReason", "INVALID_PASSWORD");
                responseMap.put("failedValue", ErrorConstants.INVALID_PASSWORD);
                return responseMap;
            }
        } catch (IncorrectResultSizeDataAccessException i) {
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("User Name does not exist"));
            responseMap.put("loginUserDetails", null);
            responseMap.put("failedReason", "User Name does not exit");
            responseMap.put("failedValue", ErrorConstants.USER_DOES_NOT_EXIST);
            return responseMap;
        }

        if (loginUserDetails.getToken().getTokenStr() != null && !loginUserDetails.getToken().getTokenStr().isEmpty()) {
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Token Exist : " + loginUserDetails.getToken().toString()));
            responseMap.put("loginUserDetails", loginUserDetails);

            try {
                //Update authorizedFlag as true then return token object
                sql = "UPDATE `user` u SET u.`AUTHORISED_FLAG`=1  WHERE u.`EMAIL_ID`=? ";
                int i = this.jdbcTemplate.update(sql, userName);
                LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog(" Updated Count" + i));
            } catch (IncorrectResultSizeDataAccessException i) {
                LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Exception occured on updating flag Beacause :" + 1));
                responseMap.put("loginUserDetails", null);
                responseMap.put("failedReason", "An error occured while updating flag");
                responseMap.put("failedValue", ErrorConstants.UPDATE_FAILED);
                return responseMap;
            }
            return responseMap;
        } else {
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Generate  16 Digit Token :"));
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Generate  16 Digit Token:"));

            try {
                String tokenStr = PassPhrase.getPassword(16);
                //Update User with new token
                sql = "UPDATE `user` u SET u.`TOKEN`=? ,u.`EXPIRY_DATE`=?,u.`AUTHORISED_FLAG`=1 WHERE u.`USER_ID`=?";
                this.jdbcTemplate.update(sql, tokenStr, c.getTime(), loginUserDetails.getUser().getUserId());
                String tokenSql = "SELECT u.`TOKEN`, u.EXPIRY_DATE,u.`AUTHORISED_FLAG` from `user` u where u.USER_ID=?";
                Token token = this.jdbcTemplate.queryForObject(tokenSql, new TokenRowMapper(), loginUserDetails.getUser().getUserId());
//                Token token = this.jdbcTemplate.queryForObject("SELECT u.`TOKEN`, u.EXPIRY_DATE,u.`AUTHORISED_FLAG` from `user` u where u.USER_ID=?", new TokenRowMapper(), loginUserDetails.getUser().getUserId());
                loginUserDetails.setToken(token);
                responseMap.put("loginUserDetails", loginUserDetails);

                return responseMap;
            } catch (Exception e) {
                LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Exception occured on Token retrival Beacause :" + e));
                LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Exception occured on Token retrival Beacause :" + e));
                responseMap.put("loginUserDetails", null);
                responseMap.put("failedReason", "An error occured while updating Token");
                responseMap.put("failedValue", ErrorConstants.TOKEN_RETRIVAL);
                return responseMap;
            }
        }
    }

    @Override
    public Map<String, Object> forgotPassword(String mobileNo) {
        Boolean updatedStatus = false;
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            String sql = "SELECT COUNT(*) FROM  `user` u WHERE u.MOBILE_NO=? ";
            int i = this.jdbcTemplate.update(sql, mobileNo);
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Count" + i));
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Count" + i));
            if (i > 0) {
                LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Existing Customer" + i));
                LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Existing Customer" + i));

                //Generate 6digit password 
                String password = PassPhrase.getPassword(6);

                //Get B-crypt password and update db
                PasswordEncoder encoder = new BCryptPasswordEncoder();
                String encryptedPassword = encoder.encode(password);

                sql = "UPDATE `user` u SET u.`PASSWORD`=?  WHERE u.MOBILE_NO=? ";
                int j = this.jdbcTemplate.update(sql, encryptedPassword, mobileNo);
                LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog(" Updated Count" + i));
                LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(" Updated Count" + i));
                if (i > 0) {
                    updatedStatus = true;
                } else {
                    updatedStatus = false;
                }
            } else {
                responseMap.put("failedReason", "USER_DOES_NOT_EXIST");
                responseMap.put("failedValue", ErrorConstants.USER_DOES_NOT_EXIST);
            }
            responseMap.put("updatedStatus", updatedStatus);
            return responseMap;
        } catch (IncorrectResultSizeDataAccessException i) {
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("User Does Not exist "));
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("User Does Not exist "));
            responseMap.put("failedReason", "Exception Occured");
            responseMap.put("failedValue", HttpStatus.INTERNAL_SERVER_ERROR);
            responseMap.put("updatedStatus", false);
            return responseMap;
        }
    }

    @Override
    public int updatePassword(String emailId, String newPassword) {

        //Get B-crypt password and update db
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptedPassword = encoder.encode(newPassword);

        //Update password 
        String sql = "UPDATE `user` u SET u.`PASSWORD`=?  WHERE u.`EMAIL_ID`=? ";
        int j = this.jdbcTemplate.update(sql, encryptedPassword, emailId);
        LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog(" Updated Count" + j));
        LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(" Updated Count" + j));
        return j;
    }

    /*
     * Returns true if the Token is valid and false if it is not valid
     */
    @Override
    public ValidToken validateToken(String token, int userId) {
        try {
            Date curDate = DateUtils.getCurrentDateObject(DateUtils.IST);
            String sql = "SELECT IF(COUNT(*)>0,1,0) AS isValid from user u  where u.TOKEN=? AND u.EXPIRY_DATE>=? AND u.`USER_ID`=" + userId;
            return this.jdbcTemplate.queryForObject(sql, new RowMapper<ValidToken>() {
                @Override
                public ValidToken mapRow(ResultSet rs, int i) throws SQLException {
                    ValidToken sss = new ValidToken();
                    sss.setIsValid(rs.getBoolean("isValid"));
                    return sss;
                }
            }, token, curDate);
        } catch (Exception e) {
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Exception e " + e));
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Exception e " + e));
            return null;
        }
    }

    @Override
    public Map<String, Object> getTokenByUserId(int userId) {
        Date curDate = DateUtils.getCurrentDateObject(DateUtils.IST);
        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        c.add(Calendar.HOUR, 4);
        Token token = null;
        Map<String, Object> responseMap = new HashMap<String, Object>();
        String sql = "SELECT u.PASSWORD,u.`USER_ID`, IF(u.`EXPIRY_DATE`<= NOW(),'',u.`TOKEN`) as `TOKEN`, u.EXPIRY_DATE,"
                + " u.`AUTHORISED_FLAG` FROM `user` u WHERE u.USER_ID=? ";
        try {
            token = this.jdbcTemplate.queryForObject(sql, new TokenRowMapper(), userId);
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog(" TokenPassowrd :" + token.toString()));
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(" TokenPassowrd :" + token.toString()));
        } catch (IncorrectResultSizeDataAccessException i) {
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("User Does Not exist "));
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("User Does Not exist "));
            responseMap.put("failedReason", "USER_DOES_NOT_EXIST");
            responseMap.put("failedValue", ErrorConstants.USER_DOES_NOT_EXIST);
            responseMap.put("token", null);
            return responseMap;
        }

        if (token.getTokenStr() != null && !token.getTokenStr().isEmpty()) {
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Token Exist : " + token.getTokenStr().toString()));
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Token Exist : " + token.getTokenStr().toString()));
            responseMap.put("token", token);
            return responseMap;
        } else {
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Generate  16 Digit Token :"));
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Generate  16 Digit Token:"));

            String tokenStr = PassPhrase.getPassword(16);

            //Update User with new token
            sql = "UPDATE `user` u SET u.`TOKEN`=? ,u.`EXPIRY_DATE`=? WHERE u.`USER_ID`=?";
            int updatedCount = this.jdbcTemplate.update(sql, tokenStr, c.getTime(), userId);
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Updated Token :" + updatedCount));
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Updated Token :" + updatedCount));

            try {
                token = this.jdbcTemplate.queryForObject("SELECT u.TOKEN, u.EXPIRY_DATE,u.`AUTHORISED_FLAG` from `user` u where u.USER_ID=?", new TokenRowMapper(), userId);
                responseMap.put("token", token);
                return responseMap;
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Exception occured on Token retrival Beacause :" + e));
                LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Exception occured on Token retrival Beacause :" + e));
                responseMap.put("token", null);
                responseMap.put("failedReason", "TOKEN_RETRIVAL");
                responseMap.put("failedValue", ErrorConstants.TOKEN_RETRIVAL);
                return responseMap;
            }
        }
    }

    @Override
    public Map<String, Object> updateUserAuthorization(int userId, int authorizeFlag) {
        Map<String, Object> responseMap = new HashMap<String, Object>();
        Boolean updatedStatus = false;
        String sql = "UPDATE `user` u SET u.`AUTHORISED_FLAG`=?  WHERE u.USER_ID=? ";
        try {
            int i = this.jdbcTemplate.update(sql, authorizeFlag, userId);
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog(" Updated Count" + i));
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(" Updated Count" + i));
            if (i > 0) {
                updatedStatus = true;
            } else {
                updatedStatus = false;
            }
            responseMap.put("updatedStatus", updatedStatus);
            return responseMap;
        } catch (IncorrectResultSizeDataAccessException i) {
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("User Does Not exist "));
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("User Does Not exist "));
            responseMap.put("failedReason", "USER_DOES_NOT_EXIST");
            responseMap.put("failedValue", ErrorConstants.USER_DOES_NOT_EXIST);
            responseMap.put("updatedStatus", false);
            return responseMap;
        }

    }

    @Override
    public String updateToken(String oldToken, int userId) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.HOUR, 6);
        String tokenStr = PassPhrase.getPassword(16);
        try {
            String sql3 = "SELECT IF(u.`EXPIRY_DATE`<= NOW(),'',u.`TOKEN`) AS `TOKEN` FROM `user` u WHERE u.`USER_ID`=?";
            String s = this.jdbcTemplate.queryForObject(sql3, String.class, userId);
            if (s == null || s.isEmpty()) {
                String sql = "UPDATE `user` u SET u.`TOKEN`=?,u.`EXPIRY_DATE`=? WHERE u.`USER_ID`=?";
                this.jdbcTemplate.update(sql, tokenStr, c.getTime(), userId);

                String sql1 = "SELECT u.`TOKEN` FROM `user` u WHERE u.`USER_ID`=?;";
                return this.jdbcTemplate.queryForObject(sql1, String.class, userId);
            } else {
                return s;
            }
        } catch (IncorrectResultSizeDataAccessException i) {
            i.printStackTrace();
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Token Does Not exist "));
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Token Does Not exist "));
            return null;
        }
    }
}
