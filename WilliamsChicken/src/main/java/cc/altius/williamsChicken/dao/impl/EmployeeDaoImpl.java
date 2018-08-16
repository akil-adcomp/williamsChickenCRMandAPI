/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.dao.impl;

import cc.altius.utils.DateUtils;
import cc.altius.williamsChicken.dao.EmployeeDao;
import cc.altius.williamsChicken.model.CustomUserDetails;
import cc.altius.williamsChicken.model.Employee;
import cc.altius.williamsChicken.model.rowMapper.EmployeeRowMapper;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author manish
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Employee> getEmployeeList() {
        String sql = "SELECT e.*,u.`USERNAME`,u.`USER_ID`,s.`STORE_NAME` FROM employee e "
                + " LEFT JOIN `user` u ON e.`LAST_MODIFIED_BY`=u.`USER_ID`"
                + " LEFT JOIN store s ON e.`STORE_ID`=s.`STORE_ID`";
//                + " ORDER BY e.`EMPLOYEE_ID`";
        return this.jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    @Override
    public int addEmployee(Employee employee) {
        int employeeId = 0;
        int curUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMDHMS);
        Map<String, Object> params = new HashMap<>();
        SimpleJdbcInsert vendorInsert = new SimpleJdbcInsert(this.dataSource).withTableName("employee").usingGeneratedKeyColumns("EMPLOYEE_ID");
        try {
            params.put("FIRST_NAME", employee.getFirstName());
            params.put("LAST_NAME", employee.getLastName());
            params.put("EMAIL_ID", employee.getEmailId());
            params.put("PHONE_NO", employee.getPhoneNo());
            params.put("PAY_RATE", employee.getPayRate());
            params.put("STORE_ID", employee.getStoreId());
            params.put("ACTIVE", true);
            params.put("CREATED_BY", curUser);
            params.put("CREATED_DATE", curDate);
            params.put("LAST_MODIFIED_BY", curUser);
            params.put("LAST_MODIFIED_DATE", curDate);
            employeeId = vendorInsert.executeAndReturnKey(params).intValue();
            return employeeId;
        } catch (Exception e) {
            e.printStackTrace();
            return employeeId;
        }
    }

    @Override
    public Employee getEmployeeByEmployeeId(int employeeId) {
        String sql = "SELECT e.*,u.`USERNAME`,u.`USER_ID`,s.`STORE_NAME` FROM employee e "
                + " LEFT JOIN `user` u ON e.`LAST_MODIFIED_BY`=u.`USER_ID`"
                + " LEFT JOIN store s ON e.`STORE_ID`=s.`STORE_ID`"
                + " WHERE e.`EMPLOYEE_ID`=?";
        return this.jdbcTemplate.queryForObject(sql, new EmployeeRowMapper(), employeeId);
    }

    @Override
    public int updateEmployee(Employee employee) {
        int updatedId = 0;
        int curUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        Date curDate = DateUtils.getCurrentDateObject(DateUtils.IST);
        Map<String, Object> params = new HashMap<>();
        try {
            String sql = "UPDATE employee e"
                    + " SET "
                    + " e.`FIRST_NAME`=:firstName,"
                    + " e.`LAST_NAME`=:lastName,"
                    + " e.`EMAIL_ID`=:emailId,"
                    + " e.`PHONE_NO`=:phone,"
                    + " e.`LAST_MODIFIED_DATE`=:lastModifiedDate,"
                    + " e.`LAST_MODIFIED_BY`=:lastModifiedBy"
                    + " WHERE e.`EMPLOYEE_ID`=:employeeId";
            params.put("firstName", employee.getFirstName());
            params.put("lastName", employee.getLastName());
            params.put("emailId", employee.getEmailId());
            params.put("phone", employee.getPhoneNo());
            params.put("lastModifiedDate", curDate);
            params.put("lastModifiedBy", curUser);
            params.put("employeeId", employee.getEmployeeId());
            updatedId = this.namedParameterJdbcTemplate.update(sql, params);
            return updatedId;
        } catch (Exception e) {
            e.printStackTrace();
            return updatedId;
        }
    }

    @Override
    public int updateEmployeeActiveStatus(List<Integer> employeeIds, int publishValue) {
        int curUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMDHMS);
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String sql = "UPDATE employee e "
                + " SET e.`ACTIVE`=:publishValue, e.`LAST_MODIFIED_DATE`=:lastModifiedDate, e.`LAST_MODIFIED_BY`=:lastModifiedBy "
                + " WHERE e.`EMPLOYEE_ID` IN (:ids)";
        try {
            parameters.addValue("publishValue", publishValue);
            parameters.addValue("ids", employeeIds);
            parameters.addValue("lastModifiedDate", curDate);
            parameters.addValue("lastModifiedBy", curUser);
            return npjt.update(sql, parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return npjt.update(sql, parameters);
    }
}
