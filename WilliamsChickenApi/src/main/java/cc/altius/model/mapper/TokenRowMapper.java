/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model.mapper;

import cc.altius.model.Token;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author altius
 */
public class TokenRowMapper implements RowMapper<Token> {

    @Override
    public Token mapRow(ResultSet rs, int i) throws SQLException {
        Token t = new Token();
        t.setTokenStr(rs.getString("TOKEN"));
        t.setExpiryDate(rs.getTimestamp("EXPIRY_DATE"));
        t.setAuthorizedFlag(rs.getBoolean("AUTHORISED_FLAG"));
        return t;
    }
}
