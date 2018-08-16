/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.web.controller;

import cc.altius.williamsChicken.model.Email;
import cc.altius.williamsChicken.service.EmailerService;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author manish
 */
@Controller
public class HomeController {

    @Autowired
    private EmailerService emailerService;

    @RequestMapping("/home/home.htm")
    public String showHome(HttpServletRequest request, ModelMap model, HttpSession session) throws UnsupportedEncodingException {
        return "home/home";
    }

    @RequestMapping("/home/forgotPassword.htm")
    public String forgotPassword(HttpServletRequest request, ModelMap model, HttpSession session) throws UnsupportedEncodingException {
        return "home/forgotPassword";
    }

    @RequestMapping(value = "/home/forgotPassword.htm", method = RequestMethod.POST)
    public String forgotPasswordPost(HttpServletRequest request, ModelMap model, HttpSession session) throws UnsupportedEncodingException {
        String emailId = ServletRequestUtils.getStringParameter(request, "emailId", null);
        if (this.emailerService.isExitUser(emailId)) {
            this.emailerService.buildEmailTemplate(emailId);
            model.put("msg", "Email sent successfully on your email id");
            return "home/login";
        } else {
            model.put("error", "This email id does not exist");
            return "home/forgotPassword";
        }

    }
}
