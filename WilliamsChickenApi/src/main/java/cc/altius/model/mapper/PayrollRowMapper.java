/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model.mapper;

import cc.altius.model.DateRange;
import cc.altius.model.Employee;
import cc.altius.model.Payroll;
import cc.altius.model.Store;
import cc.altius.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author manish
 */
public class PayrollRowMapper implements RowMapper<Payroll> {

    @Override
    public Payroll mapRow(ResultSet rs, int i) throws SQLException {
        Payroll data = new Payroll();
        data.setCreatedBy(rs.getInt("CREATED_BY"));
        data.setCreatedDate(rs.getDate("CREATED_DATE"));

        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
        employee.setFirstName(rs.getString("FIRST_NAME"));
        employee.setLastName(rs.getString("LAST_NAME"));
        data.setEmployee(employee);

        data.setLastModifiedBy(rs.getInt("LAST_MODIFIED_BY"));
        data.setLastModifiedDate(rs.getDate("LAST_MODIFIED_DATE"));
        data.setOt(rs.getInt("OT"));
        data.setPayRate(rs.getDouble("PAY_RATE"));
        data.setPayrollId(rs.getInt("PAYROLL_ID"));
        data.setRegHour(rs.getDouble("REG_HOUR"));

        Store store = new Store();
        store.setStoreId(rs.getInt("STORE_ID"));
        store.setStoreName(rs.getString("STORE_NAME"));
        data.setStore(store);
        User user = new User();
        user.setUserId(rs.getInt("CREATED_BY"));
        user.setUsername(rs.getString("USERNAME"));
        data.setUser(user);
        
        DateRange dateRange=new DateRange();
        dateRange.setStartDate(rs.getString("START_DATE"));
        dateRange.setStopDate(rs.getString("STOP_DATE"));
        data.setDateRange(dateRange);
        return data;
    }
}
