/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.web.controller;

import cc.altius.williamsChicken.model.CustomUserDetails;
import cc.altius.williamsChicken.model.State;
import cc.altius.williamsChicken.model.Vendor;
import cc.altius.williamsChicken.service.StateService;
import cc.altius.williamsChicken.service.VendorService;
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
public class VendorController {

    @Autowired
    private VendorService vendorService;
    @Autowired
    private StateService stateService;

    @RequestMapping(value = "/vendor/addVendor.htm", method = RequestMethod.GET)
    public String showAddVendorForm(ModelMap model) {
        Vendor vendor = new Vendor();
        List<State> stateList = this.stateService.getStateList();
        model.addAttribute("stateList", stateList);
        model.addAttribute("vendor", vendor);
        return "/vendor/addVendor";
    }

    @RequestMapping(value = "/vendor/addVendor.htm", method = RequestMethod.POST)
    public String onAddVendorSubmit(@ModelAttribute("vendor") Vendor vendor, Errors errors, ModelMap model, HttpServletRequest request) {
        String cancel = ServletRequestUtils.getStringParameter(request, "_cancel", null);
        if (cancel != null) {
            vendor = null;
            return "redirect:/home/home.htm?msg=msg.actionCancelled";
        } else {
            int vendorId = this.vendorService.addVendor(vendor);
            if (vendorId == 0) {
                model.addAttribute("error", "An error occured while adding vendor");
                return "/vendor/addVendor";
            } else {
                return "redirect:/vendor/vendorList.htm?msg=msg.vendorAddedSuccessfully";
            }
        }
    }

    @RequestMapping(value = "/vendor/vendorList.htm", method = RequestMethod.GET)
    public String showVendorListPage(ModelMap model, HttpServletRequest request, HttpSession session) {
        List<Vendor> vendorList = this.vendorService.getVendorList();
        System.out.println("vendorList == " + vendorList);
        model.addAttribute("vendorList", vendorList);
        return "/vendor/vendorList";
    }

    @RequestMapping(value = "/vendor/showEditVendor.htm", method = RequestMethod.POST)
    public String showEditVendorForm(@RequestParam(value = "vendorId", required = true) int vendorId, ModelMap model) {
        CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Vendor vendor = this.vendorService.getVendorByVendorId(vendorId);
        List<State> stateList = this.stateService.getStateList();
        model.addAttribute("stateList", stateList);
        model.addAttribute("vendor", vendor);
        return "/vendor/editVendor";
    }

    @RequestMapping(value = "/vendor/editVendor.htm", method = RequestMethod.POST)
    public String onEditVendorSubmit(@ModelAttribute("vendor") Vendor vendor, Errors errors, ModelMap model, HttpServletRequest request) {
        String cancel = ServletRequestUtils.getStringParameter(request, "_cancel", null);
        if (cancel != null) {
            vendor = null;
            return "redirect:/home/home.htm?msg=msg.actionCancelled";
        }
        try {
            int updatedId = this.vendorService.updateVendor(vendor);
            if (updatedId > 0) {
                return "redirect:/vendor/vendorList.htm?msg=msg.vendorUpdatedSuccessfully";
            } else {
                model.addAttribute("error", "An error occured while updating vendor");
                return "/vendor/editVendor";
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occured while updating vendor");
            return "/vendor/editVendor";
        }
    }

    @RequestMapping(value = "/vendor/activeVendor.htm", method = RequestMethod.POST)
    public String activeVendor(HttpServletRequest request, ModelMap model) {
        String vendorIds = ServletRequestUtils.getStringParameter(request, "vendorIds", "-1");
        int publishValue = ServletRequestUtils.getIntParameter(request, "publishValue", -1);
        Map m = this.vendorService.updateVendorActiveStatus(vendorIds, publishValue);
        if ((Integer) m.get("result") == 1 && publishValue == 1) {
            model.addAttribute("msg", "Vendor active successfully");
            return "redirect:/vendor/vendorList.htm";
        } else if ((Integer) m.get("result") == 1 && publishValue == 0) {
            model.addAttribute("msg", "Vendor inactive successfully");
            return "redirect:/vendor/vendorList.htm";
        } else {
            model.addAttribute("msg", "An error occured while update  vendor");
            return "redirect:/vendor/vendorList.htm";
        }
    }
}
