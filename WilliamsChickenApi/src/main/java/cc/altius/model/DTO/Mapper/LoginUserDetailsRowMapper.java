/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model.DTO.Mapper;

import cc.altius.model.DTO.LoginUserDetails;
import cc.altius.model.Store;
import cc.altius.model.Token;
import cc.altius.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author manish
 */
public class LoginUserDetailsRowMapper implements RowMapper<LoginUserDetails> {

    @Override
    public LoginUserDetails mapRow(ResultSet rs, int i) throws SQLException {
        LoginUserDetails l = new LoginUserDetails();

        Token t = new Token();
        t.setTokenStr(rs.getString("TOKEN"));
        t.setExpiryDate(rs.getTimestamp("EXPIRY_DATE"));
        t.setAuthorizedFlag(rs.getBoolean("AUTHORISED_FLAG"));
        l.setToken(t);

        User user = new User();
        user.setUserId(rs.getInt("USER_ID"));
        user.setUsername(rs.getString("USERNAME"));
        user.setEmailId(rs.getString("EMAIL_ID"));
        user.setPassword(rs.getString("PASSWORD"));
        l.setUser(user);

        l.setStoreId(rs.getInt("STORE_ID"));
        l.setStoreName(rs.getString("STORE_NAME"));
        return l;
    }
}
