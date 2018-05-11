/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.service;

import cc.altius.williamsChicken.model.Employee;
import java.util.List;
import java.util.Map;

/**
 *
 * @author altius
 */
public interface EmployeeService {

    public List<Employee> getEmployeeList();

    public int addEmployee(Employee employee);

    public Employee getEmployeeByEmployeeId(int employeeId);

    public int updateEmployee(Employee employee);

    public Map updateEmployeeActiveStatus(String employeeIds, int publishValue);
}
