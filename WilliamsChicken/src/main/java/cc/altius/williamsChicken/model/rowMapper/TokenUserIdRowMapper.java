/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model.rowMapper;

import cc.altius.williamsChicken.model.Token;
import cc.altius.williamsChicken.model.TokenUserId;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author altius
 */
public class TokenUserIdRowMapper implements RowMapper<TokenUserId> {

    @Override
    public TokenUserId mapRow(ResultSet rs, int i) throws SQLException {
        TokenUserId tp = new TokenUserId();
        tp.setUserId(rs.getInt(("USER_ID")));
        tp.setAuthorizedFlag(rs.getBoolean("AUTHORISED_FLAG"));
        Token t = new Token();
        t.setTokenStr(rs.getString("TOKEN"));
        t.setExpiryDate(rs.getTimestamp("EXPIRY_DATE"));
        tp.setToken(t);

        return tp;
    }
}
