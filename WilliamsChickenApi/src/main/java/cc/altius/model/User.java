/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author altius
 */
public class User implements Serializable {

    private int userId;
    private String username;
    private String password;
    private boolean active;
    private boolean expired;
    private int failedAttempts;
    private Date expiresOn;
    private boolean outsideAccess;
    private Date lastLoginDate;
    private String emailId;
    private String phoneNo;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public Date getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(Date expiresOn) {
        this.expiresOn = expiresOn;
    }

    public boolean isOutsideAccess() {
        return outsideAccess;
    }

    public void setOutsideAccess(boolean outsideAccess) {
        this.outsideAccess = outsideAccess;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", username=" + username + ", password=" + password + ", active=" + active + ", expired=" + expired + ", failedAttempts=" + failedAttempts + ", expiresOn=" + expiresOn + ", outsideAccess=" + outsideAccess + ", lastLoginDate=" + lastLoginDate + ", emailId=" + emailId + ", phoneNo=" + phoneNo + '}';
    }
}