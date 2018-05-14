/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.web.controller;

import cc.altius.williamsChicken.model.CustomUserDetails;
import cc.altius.williamsChicken.model.State;
import cc.altius.williamsChicken.model.Store;
import cc.altius.williamsChicken.service.StateService;
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
public class StoreController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private StateService stateService;

    @RequestMapping(value = "/store/addStore.htm", method = RequestMethod.GET)
    public String showAddStoreForm(ModelMap model) {
        Store store = new Store();
        List<State> stateList = this.stateService.getStateList();
        model.addAttribute("stateList", stateList);
        model.addAttribute("store", store);
        return "/store/addStore";
    }

    @RequestMapping(value = "/store/addStore.htm", method = RequestMethod.POST)
    public String onAddStoreSubmit(@ModelAttribute("store") Store store, Errors errors, ModelMap model, HttpServletRequest request) {
        String cancel = ServletRequestUtils.getStringParameter(request, "_cancel", null);
        if (cancel != null) {
            store = null;
            return "redirect:/home/home.htm?msg=msg.actionCancelled";
        } else {
            int storeId = this.storeService.addStore(store);
            if (storeId == 0) {
                model.addAttribute("error", "An error occured while adding store");
                return "/store/addStore";
            } else {
                return "redirect:/store/storeList.htm?msg=msg.storeAddedSuccessfully";
            }
        }
    }

    @RequestMapping(value = "/store/storeList.htm", method = RequestMethod.GET)
    public String showStoreListPage(ModelMap model, HttpServletRequest request, HttpSession session) {
        List<Store> storeList = this.storeService.getStoreList();
        model.addAttribute("storeList", storeList);
        return "/store/storeList";
    }

    @RequestMapping(value = "/store/showEditStore.htm", method = RequestMethod.POST)
    public String showEditStoreForm(@RequestParam(value = "storeId", required = true) int storeId, ModelMap model) {
        CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Store store = this.storeService.getStoreByStoreId(storeId);
        List<State> stateList = this.stateService.getStateList();
        model.addAttribute("stateList", stateList);
        model.addAttribute("store", store);
        return "/store/editStore";
    }

    @RequestMapping(value = "/store/editStore.htm", method = RequestMethod.POST)
    public String onEditEmployeeSubmit(@ModelAttribute("store") Store store, Errors errors, ModelMap model, HttpServletRequest request) {
        String cancel = ServletRequestUtils.getStringParameter(request, "_cancel", null);
        if (cancel != null) {
            store = null;
            return "redirect:/home/home.htm?msg=msg.actionCancelled";
        }
        try {
            int updatedId = this.storeService.updateStore(store);
            if (updatedId > 0) {
                return "redirect:/store/storeList.htm?msg=msg.storeUpdatedSuccessfully";
            } else {
                model.addAttribute("error", "An error occured while updating store");
                return "/store/editStore";
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occured while updating employee");
            return "/store/editStore";
        }
    }

    @RequestMapping(value = "/store/activeStore.htm", method = RequestMethod.POST)
    public String activeStore(HttpServletRequest request, ModelMap model) {
        String storeIds = ServletRequestUtils.getStringParameter(request, "storeIds", null);
        int publishValue = ServletRequestUtils.getIntParameter(request, "publishValue", -1);
        System.out.println("id == " + storeIds + " value == " + publishValue);
        Map m = this.storeService.updateStoreActiveStatus(storeIds, publishValue);
        if ((Integer) m.get("result") == 1 && publishValue == 1) {
            model.addAttribute("msg", "Store active successfully");
            return "redirect:/store/storeList.htm";
        } else if ((Integer) m.get("result") == 1 && publishValue == 0) {
            model.addAttribute("msg", "Store inactive successfully");
            return "redirect:/store/storeList.htm";
        } else {
            model.addAttribute("error", "An error occured while update store");
            return "redirect:/store/storeList.htm";
        }
    }
}
