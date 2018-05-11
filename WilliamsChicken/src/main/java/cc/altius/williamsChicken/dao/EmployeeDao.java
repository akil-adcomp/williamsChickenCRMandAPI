/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.dao;

import cc.altius.williamsChicken.model.Employee;
import java.util.List;

/**
 *
 * @author manish
 */
public interface EmployeeDao {

    public List<Employee> getEmployeeList();

    public int addEmployee(Employee employee);

    public Employee getEmployeeByEmployeeId(int employeeId);

    public int updateEmployee(Employee employee);

    public int updateEmployeeActiveStatus(List<Integer> employeeIds, int publishValue);
}
