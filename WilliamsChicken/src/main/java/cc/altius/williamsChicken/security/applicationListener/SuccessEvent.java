/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.security.applicationListener;

import cc.altius.williamsChicken.model.CustomUserDetails;
import cc.altius.williamsChicken.service.LogService;
import cc.altius.williamsChicken.service.UserService;
import cc.altius.williamsChicken.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 *
 * @author manish
 */
public class SuccessEvent implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent e) {
        CustomUserDetails cud = (CustomUserDetails) e.getAuthentication().getPrincipal();
        this.userService.resetFailedAttemptsByUserId(cud.getUserId());
        try {
            this.logService.accessLog(((WebAuthenticationDetails) e.getAuthentication().getDetails()).getRemoteAddress(), cud.getUsername(), cud.getUserId(), true, "Success");
        } catch (Exception e1) {
            LogUtils.systemLogger.info(LogUtils.buildStringForAccessLog(e1));
        }
        LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(" User found with username :" + cud.getUsername()));

    }
}
