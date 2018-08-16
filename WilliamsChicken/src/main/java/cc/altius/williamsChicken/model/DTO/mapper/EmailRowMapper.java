/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model.DTO.mapper;

import cc.altius.williamsChicken.model.Email;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author altius
 */
public class EmailRowMapper implements RowMapper<Email> {

    @Override
    public Email mapRow(ResultSet rs, int i) throws SQLException {
        Email data = new Email();
        data.setAttempts(rs.getInt("ATTEMPTS"));
        data.setBccToSend(rs.getString("BCC_SEND_TO"));
        data.setBody(rs.getString("BODY"));
        data.setCcToSend(rs.getString("CC_SEND_TO"));
        data.setCreatedDate(rs.getDate("CREATED_DATE"));
        data.setEmailId(rs.getInt("EMAIL_ID"));
        data.setLastModifiedDate(rs.getDate("LAST_MODIFIED_DATE"));
        data.setResponse(rs.getString("RESPONSE"));
        data.setStatus(rs.getInt("STATUS"));
        data.setSubject(rs.getString("SUBJECT"));
        data.setToSend(rs.getString("TO_SEND"));
        data.setToSendDate(rs.getDate("TO_SEND_DATE"));
        return data;
    }
}
