/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.web.controller;

import cc.altius.williamsChicken.service.EmailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 * this schedeular runs after every 2 seconds and insert the lead in to dialer
 *
 * @author manish
 */
@Controller
public class SchedulerController {

    @Autowired
    private EmailerService emailerService;
    private @Value("#{schedulerSettings['active']}")
    String schedulerActive;

    @Scheduled(cron = "00 */01 * * * *")
//    @RequestMapping(value = "/home/test.htm")
    public void insertFailedLeadsAndUpdateLeadsToDialer() {
        try {
            if (schedulerActive.equals("1")) {
                this.emailerService.sendEmail();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
