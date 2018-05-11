/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.security;

import cc.altius.utils.IPUtils;
import cc.altius.williamsChicken.dao.LogDao;
import cc.altius.williamsChicken.dao.UserDao;
import cc.altius.williamsChicken.framework.GlobalConstants;
import cc.altius.williamsChicken.model.CustomUserDetails;
import cc.altius.williamsChicken.utils.LogUtils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author manish
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private LogDao logDao;
    private Set<String> allowedIpRange;

    public CustomUserDetailsService() {
        this.allowedIpRange = new HashSet<>();
        this.allowedIpRange.addAll(Arrays.asList(GlobalConstants.ALLOWED_IP_RANGE));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String ipAddress = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRemoteAddr();
        System.out.println("ipaddress "+ipAddress);
        try {
            CustomUserDetails user = this.userDao.getUserDetailsByUsername(username);
            if (!user.isActive()) {
                this.logDao.accessLog(ipAddress, username, null, false, "Account disabled");
            } else if (!user.isAccountNonLocked()) {
                this.logDao.accessLog(ipAddress, username, null, false, "Account locked");
            } else if (!(user.isOutsideAccess() || checkIfIpIsFromAllowedRange(ipAddress))) {
                user.setActive(false);
                this.logDao.accessLog(ipAddress, username, null, false, "Outside access");
            } else {
                if (user.isPasswordExpired()) {
                    // only insert the ROLE_BF_PASSWORD_EXPIRED
                    LogUtils.debugLogger.debug("Credentials are Expired so only put in ROLE_BF_PASSWORD_EXPIRED into Authoirites");
                    List<String> businessFunctions = new LinkedList<String>();
                    businessFunctions.add("ROLE_BF_PASSWORD_EXPIRED");
                    user.setBusinessFunction(businessFunctions);
                    System.out.println("user == "+user.getBusinessFunction().get(0));
                } else {
                    user.setBusinessFunction(this.userDao.getBusinessFunctionsForUserId(user.getUserId()));
                }
            }
            return user;
        } catch (EmptyResultDataAccessException erda) {
            throw new UsernameNotFoundException("Username not found");
        }
    }

    private boolean checkIfIpIsFromAllowedRange(String ipToCheck) {
        for (String curRange : this.allowedIpRange) {
            IPUtils curIpRange = new IPUtils(curRange);
            if (curIpRange.checkIP(ipToCheck)) {
                return true;
            }
        }
        return false;
    }
}
