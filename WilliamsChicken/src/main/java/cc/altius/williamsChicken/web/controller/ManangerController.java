/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.web.controller;

import cc.altius.williamsChicken.model.CustomUserDetails;
import cc.altius.williamsChicken.model.Manager;
import cc.altius.williamsChicken.model.Store;
import cc.altius.williamsChicken.service.ManagerService;
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
 * @author manish
 */
@Controller
public class ManangerController {

    @Autowired
    private ManagerService managerService;
    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "/manager/addManager.htm", method = RequestMethod.GET)
    public String showAddManagerForm(ModelMap model) {
        Manager manager = new Manager();
        List<Store> storeList = this.storeService.getStoreList();
        model.addAttribute("storeList", storeList);
        model.addAttribute("manager", manager);
        return "/manager/addManager";
    }

    @RequestMapping(value = "/manager/addManager.htm", method = RequestMethod.POST)
    public String onAddManagerSubmit(@ModelAttribute("manager") Manager manager, Errors errors, ModelMap model, HttpServletRequest request) {
        String cancel = ServletRequestUtils.getStringParameter(request, "_cancel", null);
        if (cancel != null) {
            manager = null;
            return "redirect:/home/home.htm?msg=msg.actionCancelled";
        } else {
//            if (this.managerService.existUserByUsername(manager.getEmailId())) {
//                errors.rejectValue("emailId", "msg.duplicateManager");
//                return "/manager/addManager";
//            }
            int managerId = this.managerService.addManager(manager);
            if (managerId == 0) {
                model.addAttribute("error", "An error occured while adding manager");
                return "/manager/addManager";
            } else {
                return "redirect:/manager/managerList.htm?msg=msg.managerAddedSuccessfully";
            }
        }
    }

    @RequestMapping(value = "/manager/managerList.htm", method = RequestMethod.GET)
    public String showManagerListPage(ModelMap model, HttpServletRequest request, HttpSession session) {
        List<Manager> managerList = this.managerService.getManagerList();
        List<Store> storeList = this.storeService.getStoreList();
        model.addAttribute("storelist", storeList);
        model.addAttribute("managerList", managerList);
        return "/manager/managerList";
    }

    @RequestMapping(value = "/manager/showEditManager.htm", method = RequestMethod.POST)
    public String showEditManagerForm(@RequestParam(value = "managerId", required = true) int managerId, ModelMap model) {
        CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Manager manager = this.managerService.getManagerByManagerId(managerId);
        model.addAttribute("manager", manager);
        return "/manager/editManager";
    }

    @RequestMapping(value = "/manager/editManager.htm", method = RequestMethod.POST)
    public String onEditManagerSubmit(@ModelAttribute("manager") Manager manager, Errors errors, ModelMap model, HttpServletRequest request) {
        String cancel = ServletRequestUtils.getStringParameter(request, "_cancel", null);
        if (cancel != null) {
            manager = null;
            return "redirect:/home/home.htm?msg=msg.actionCancelled";
        }
        try {
            int updatedId = this.managerService.updateManager(manager);
            if (updatedId > 0) {
                return "redirect:/manager/managerList.htm?msg=msg.managerUpdatedSuccessfully";
            } else {
                model.addAttribute("error", "An error occured while updating manager");
                return "/manager/editManager";
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occured while updating manager");
            return "/manager/editManager";
        }
    }

    @RequestMapping(value = "/manager/activeManager.htm", method = RequestMethod.POST)
    public String activeManager(HttpServletRequest request, ModelMap model) {
        String managerIds = ServletRequestUtils.getStringParameter(request, "managerIds", null);
        int publishValue = ServletRequestUtils.getIntParameter(request, "publishValue", -1);

        Map m = this.managerService.updateManagerActiveStatus(managerIds, publishValue);
        if ((Integer) m.get("result") == 1 && publishValue == 1) {
            model.addAttribute("msg", "Manager active successfully");
            return "redirect:/manager/managerList.htm";
        } else if ((Integer) m.get("result") == 1 && publishValue == 0) {
            model.addAttribute("msg", "Manager inactive successfully");
            return "redirect:/manager/managerList.htm";
        } else {
            model.addAttribute("msg", "An error occured while update manager");
            return "redirect:/manager/managerList.htm";
        }
    }
}
