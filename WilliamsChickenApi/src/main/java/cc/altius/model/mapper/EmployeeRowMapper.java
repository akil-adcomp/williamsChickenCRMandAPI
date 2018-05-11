/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model.mapper;

import cc.altius.model.Employee;
import cc.altius.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author manish
 */
public class EmployeeRowMapper implements RowMapper<Employee> {
    
    @Override
    public Employee mapRow(ResultSet rs, int i) throws SQLException {
        Employee data = new Employee();
        data.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
        data.setFirstName(rs.getString("FIRST_NAME"));
        data.setLastName(rs.getString("LAST_NAME"));
//        data.setEmailId(rs.getString("EMAIL_ID"));
//        data.setPhoneNo(rs.getString("PHONE_NO"));
//        data.setCreatedDate(rs.getDate("CREATED_DATE"));
//        data.setCeratedBy(rs.getInt("CREATED_BY"));
//        data.setLastModifiedBy(rs.getInt("LAST_MODIFIED_DATE"));
//        data.setLastModifiedDate(rs.getDate("LAST_MODIFIED_DATE"));
//        data.setActive(rs.getBoolean("ACTIVE"));
//        
//        User user = new User();
//        user.setUserId(rs.getInt("USER_ID"));
//        user.setUserName(rs.getString("USERNAME"));
//        data.setUser(user);
        return data;
    }
}
