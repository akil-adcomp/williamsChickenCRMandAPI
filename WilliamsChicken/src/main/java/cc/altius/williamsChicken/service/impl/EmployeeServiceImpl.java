/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.service.impl;

import cc.altius.williamsChicken.dao.EmployeeDao;
import cc.altius.williamsChicken.model.Employee;
import cc.altius.williamsChicken.service.EmployeeService;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author manish
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{
    
    @Autowired
    private EmployeeDao employeeDao;
    
       @Override
    public List<Employee> getEmployeeList() {
        return this.employeeDao.getEmployeeList();
    }

    @Override
    public int addEmployee(Employee employee) {
        return this.employeeDao.addEmployee(employee);
    }

    @Override
    public Employee getEmployeeByEmployeeId(int employeeId) {
        return this.employeeDao.getEmployeeByEmployeeId(employeeId);
    }

    @Override
    public int updateEmployee(Employee employee) {
        return this.employeeDao.updateEmployee(employee);
    }

    @Override
    public Map updateEmployeeActiveStatus(String employeeIds, int publishValue) {
        Map map = new HashMap();
        try {
            String[] arr = employeeIds.split(",");
            List<Integer> Ids = new LinkedList<>();
            for (String s : arr) {
                Ids.add(Integer.parseInt(s));
            }
            int result = this.employeeDao.updateEmployeeActiveStatus(Ids, publishValue);
            if (result > 0) {
                map.put("result", 1);
            } else {
                map.put("result", 0);
            }

        } catch (Exception e) {
            map.put("result", 0);
            e.printStackTrace();
        }
        return map;
    }
}
