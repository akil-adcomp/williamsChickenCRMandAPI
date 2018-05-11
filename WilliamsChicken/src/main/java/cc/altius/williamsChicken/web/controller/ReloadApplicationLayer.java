/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.web.controller;

import cc.altius.williamsChicken.framework.ApplicationSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author shrutika
 */
@Controller
public class ReloadApplicationLayer {

    @RequestMapping("/admin/reloadApplicationLayer.htm")
    public String reloadApplicationLayer() {
        ApplicationSession applicationSession = ApplicationSession.getCurrent();
        applicationSession.reloadAll();
        return "redirect:../home/home.htm?msg=msg.reloadSuccess";
    }
}
