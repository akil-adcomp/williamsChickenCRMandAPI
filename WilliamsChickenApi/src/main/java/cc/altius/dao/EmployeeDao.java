/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.dao;

import cc.altius.model.Employee;
import java.util.List;

/**
 *
 * @author altius
 */
public interface EmployeeDao {

    public List<Employee> getEmployeeList();
}
