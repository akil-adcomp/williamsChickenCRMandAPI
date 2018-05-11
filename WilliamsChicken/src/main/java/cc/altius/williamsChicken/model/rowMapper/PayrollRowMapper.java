/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model.rowMapper;

import cc.altius.williamsChicken.model.Employee;
import cc.altius.williamsChicken.model.Payroll;
import cc.altius.williamsChicken.model.Store;
import cc.altius.williamsChicken.model.User;
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
        employee.setFirstName(rs.getString("name"));
        data.setEmployee(employee);

        data.setEndDate(rs.getDate("END_DATE"));
        data.setLastModifiedBy(rs.getInt("LAST_MODIFIED_BY"));
        data.setLastModifiedDate(rs.getDate("LAST_MODIFIED_DATE"));
        data.setOt(rs.getInt("OT"));
        data.setOtPay(rs.getDouble("OT_PAY"));
        data.setPayRate(rs.getDouble("PAY_RATE"));
        data.setPayrollId(rs.getInt("PAYROLL_ID"));
        data.setRegHour(rs.getInt("REG_HOUR"));
        data.setRegPay(rs.getDouble("REG_PAY"));
        data.setStartDate(rs.getDate("START_DATE"));

        Store store = new Store();
        store.setStoreId(rs.getInt("STORE_ID"));
        store.setStoreName(rs.getString("STORE_NAME"));
        data.setStore(store);

        data.setTotalPay(rs.getDouble("TOTAL_PAY"));
        User user = new User();
        user.setUserId(rs.getInt("CREATED_BY"));
        user.setUsername(rs.getString("USERNAME"));
        data.setUser(user);
        return data;
    }
}
