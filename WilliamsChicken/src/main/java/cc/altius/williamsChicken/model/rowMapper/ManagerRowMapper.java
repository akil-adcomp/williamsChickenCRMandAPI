/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model.rowMapper;

import cc.altius.williamsChicken.model.Manager;
import cc.altius.williamsChicken.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author manish
 */
public class ManagerRowMapper implements RowMapper<Manager> {
    
    @Override
    public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
        Manager data = new Manager();
        data.setFirstName(rs.getString("FIRST_NAME"));
        data.setLastName(rs.getString("LAST_NAME"));
        data.setEmailId(rs.getString("EMAIL_ID"));
        data.setPhoneNo(rs.getString("PHONE_NUMBER"));
        data.setManagerId(rs.getInt("MANAGER_ID"));
        data.setCreatedDate(rs.getDate("CREATED_DATE"));
        data.setCreatedBy(rs.getInt("CREATED_BY"));
        data.setLastModifiedDate(rs.getDate("LAST_MODIFIED_DATE"));
        data.setLastModifiedBy(rs.getInt("LAST_MODIFIED_BY"));
        data.setActive(rs.getBoolean("ACTIVE"));
        data.setStoreName(rs.getString("STORE_NAME"));
        
        User user = new User();
        user.setUserId(rs.getInt("CREATED_BY"));
        user.setUsername(rs.getString("USERNAME"));
        data.setUser(user);
        return data;
    }
}
