/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.web.controller;

import cc.altius.williamsChicken.model.CustomUserDetails;
import cc.altius.williamsChicken.model.Employee;
import cc.altius.williamsChicken.model.Store;
import cc.altius.williamsChicken.service.EmployeeService;
import cc.altius.williamsChicken.service.StoreService;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author altius
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "/employee/addEmployee.htm", method = RequestMethod.GET)
    public String showAddEmployeeForm(ModelMap model) {
        Employee employee = new Employee();
        List<Store> storeList = this.storeService.getStoreList();
        model.addAttribute("storeList", storeList);
        model.addAttribute("employee", employee);
        return "/employee/addEmployee";
    }

    @RequestMapping(value = "/employee/addEmployee.htm", method = RequestMethod.POST)
    public String onAddEmployeeSubmit(@ModelAttribute("employee") Employee employee, Errors errors, ModelMap model, HttpServletRequest request) {
        String cancel = ServletRequestUtils.getStringParameter(request, "_cancel", null);
        if (cancel != null) {
            employee = null;
            return "redirect:/home/home.htm?msg=msg.actionCancelled";
        } else {
            int employeeId = this.employeeService.addEmployee(employee);
            if (employeeId == 0) {
                model.addAttribute("error", "An error occured while adding employee");
                return "/employee/addEmployee";
            } else {
                return "redirect:/employee/employeeList.htm?msg=msg.employeeAddedSuccessfully";
            }
        }
    }

    @RequestMapping(value = "/employee/employeeList.htm", method = RequestMethod.GET)
    public String showEmployeeListPage(ModelMap model, HttpServletRequest request, HttpSession session) {
        List<Employee> employeeList = this.employeeService.getEmployeeList();
        List<Store> storeList = this.storeService.getStoreList();
        model.addAttribute("storeList", storeList);
        model.addAttribute("employeeList", employeeList);
        return "/employee/employeeList";
    }

    @RequestMapping(value = "/employee/showEditEmployee.htm", method = RequestMethod.POST)
    public String showEditEmployeeForm(@RequestParam(value = "employeeId", required = true) int employeeId, ModelMap model) {
        CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Employee employee = this.employeeService.getEmployeeByEmployeeId(employeeId);
        model.addAttribute("employee", employee);
        return "/employee/editEmployee";
    }

    @RequestMapping(value = "/employee/editEmployee.htm", method = RequestMethod.POST)
    public String onEditEmployeeSubmit(@ModelAttribute("employee") Employee employee, Errors errors, ModelMap model, HttpServletRequest request) {
        String cancel = ServletRequestUtils.getStringParameter(request, "_cancel", null);
        if (cancel != null) {
            employee = null;
            return "redirect:/home/home.htm?msg=msg.actionCancelled";
        }
        try {
            int updatedId = this.employeeService.updateEmployee(employee);
            if (updatedId > 0) {
                return "redirect:/employee/employeeList.htm?msg=msg.employeeUpdatedSuccessfully";
            } else {
                model.addAttribute("error", "An error occured while updating employee");
                return "/employee/editEmployee";
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occured while updating employee");
            return "/employee/editEmployee";
        }
    }

    @RequestMapping(value = "/employee/activeEmployee.htm", method = RequestMethod.POST)
    public String activeEmployee(HttpServletRequest request, ModelMap model) {
        String employeeIds = ServletRequestUtils.getStringParameter(request, "employeeIds", null);
        int publishValue = ServletRequestUtils.getIntParameter(request, "publishValue", -1);

        Map m = this.employeeService.updateEmployeeActiveStatus(employeeIds, publishValue);
        if ((Integer) m.get("result") == 1 && publishValue == 1) {
            model.addAttribute("msg", "Employee active successfully");
            return "redirect:/employee/employeeList.htm";
        } else if ((Integer) m.get("result") == 1 && publishValue == 0) {
            model.addAttribute("msg", "Emaployee inactive successfully");
            return "redirect:/employee/employeeList.htm";
        } else {
            model.addAttribute("msg", "An error occured while update employee");
            return "redirect:/employee/employeeList.htm";
        }
    }
}
