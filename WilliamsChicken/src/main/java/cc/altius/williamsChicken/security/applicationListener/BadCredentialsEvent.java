/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.security.applicationListener;

import cc.altius.williamsChicken.service.LogService;
import cc.altius.williamsChicken.service.UserService;
import cc.altius.williamsChicken.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 *
 * @author shrutika
 */
public class BadCredentialsEvent implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        String name = (String) e.getAuthentication().getPrincipal();
        this.userService.updateFailedAttemptsByUserId(name);
        this.logService.accessLog(((WebAuthenticationDetails) e.getAuthentication().getDetails()).getRemoteAddress(), name, null, false, "Incorrect password");
        LogUtils.systemLogger.info(LogUtils.buildStringForAccessLog("No User found with username :" + name));
    }
}
