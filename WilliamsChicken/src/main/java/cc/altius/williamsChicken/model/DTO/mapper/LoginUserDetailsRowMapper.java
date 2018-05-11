/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model.DTO.mapper;

import cc.altius.williamsChicken.model.DTO.LoginUserDetails;
import cc.altius.williamsChicken.model.Token;
import cc.altius.williamsChicken.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.mail.Address;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author altius
 */
public class LoginUserDetailsRowMapper implements RowMapper<LoginUserDetails> {

    @Override
    public LoginUserDetails mapRow(ResultSet rs, int i) throws SQLException {
        LoginUserDetails l = new LoginUserDetails();

        Token t = new Token();
        t.setTokenStr(rs.getString("TOKEN"));
        t.setExpiryDate(rs.getTimestamp("EXPIRY_DATE"));
        l.setToken(t);

        User user = new User();
        user.setUserId(rs.getInt("USER_ID"));
        user.setEmailId(rs.getString("EMAIL_ID"));
        user.setPhoneNo(rs.getString("PHONE_NO"));
        l.setUser(user);

        l.setRetailerId(rs.getInt("RETAILER_ID"));

//        l.setNotificationCount(rs.getInt("NOTIFICATION_COUNT"));
//        l.setCartCount(rs.getInt("CART_COUNT"));

        l.setPassword(rs.getString("PASSWORD"));
        l.setAuthorizedFlag(rs.getBoolean("AUTHORISED_FLAG"));

        return l;
    }
}
