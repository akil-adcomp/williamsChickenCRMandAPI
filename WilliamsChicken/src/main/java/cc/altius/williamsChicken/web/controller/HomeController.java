/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.web.controller;

import cc.altius.williamsChicken.model.CustomUserDetails;
import java.awt.Image;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author manish
 */
@Controller
public class HomeController {
    
    @RequestMapping("/home/home.htm")
    public String showHome(HttpServletRequest request, ModelMap model, HttpSession session) throws UnsupportedEncodingException {
        System.out.println("in the home........");
        return "home/home";
    }

}
