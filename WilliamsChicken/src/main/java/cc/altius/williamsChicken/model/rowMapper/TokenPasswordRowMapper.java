/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model.rowMapper;
import cc.altius.williamsChicken.model.Token;
import cc.altius.williamsChicken.model.TokenPassword;
import cc.altius.williamsChicken.model.TokenUserId;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author altius
 */
public class TokenPasswordRowMapper implements RowMapper<TokenPassword> {

    @Override
    public TokenPassword mapRow(ResultSet rs, int i) throws SQLException {
        TokenPassword tp = new TokenPassword();
        tp.setPassword(rs.getString("PASSWORD"));
        TokenUserId tu = new TokenUserId();
        tu.setUserId(rs.getInt(("USER_ID")));
        tu.setAuthorizedFlag(rs.getBoolean("AUTHORISED_FLAG"));
        Token t = new Token();
        t.setTokenStr(rs.getString("TOKEN"));
        t.setExpiryDate(rs.getTimestamp("EXPIRY_DATE"));
        tu.setToken(t);
        tp.setTokenUserId(tu);
        return tp;
    }
}
