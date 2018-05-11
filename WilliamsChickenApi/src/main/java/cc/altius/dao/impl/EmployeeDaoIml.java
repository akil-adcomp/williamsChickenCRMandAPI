/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.dao.impl;

import cc.altius.dao.EmployeeDao;
import cc.altius.model.Employee;
import cc.altius.model.mapper.EmployeeRowMapper;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author altius
 */
@Repository
public class EmployeeDaoIml implements EmployeeDao {

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
        String sql = "SELECT e.*,u.`USERNAME`,u.`USER_ID` FROM employee e "
                + " LEFT JOIN `user` u ON e.`LAST_MODIFIED_BY`=u.`USER_ID`"
                + " ORDER BY e.`EMPLOYEE_ID`";
        return this.jdbcTemplate.query(sql, new EmployeeRowMapper());
    }
}
