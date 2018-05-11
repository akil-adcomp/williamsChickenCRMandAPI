/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.service.impl;

import cc.altius.williamsChicken.dao.UserDao;
import cc.altius.williamsChicken.model.CustomUserDetails;
import cc.altius.williamsChicken.model.Password;
import cc.altius.williamsChicken.model.Role;
import cc.altius.williamsChicken.model.User;
import cc.altius.williamsChicken.service.UserService;
import cc.altius.williamsChicken.utils.LogUtils;
import cc.altius.utils.PassPhrase;
import cc.altius.williamsChicken.model.Employee;
import cc.altius.williamsChicken.model.Manager;
import cc.altius.williamsChicken.model.Vendor;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author manish
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int updateFailedAttemptsByUserId(String userName) {
        return this.userDao.updateFailedAttemptsByUserId(userName);
    }

    @Override
    public int resetFailedAttemptsByUserId(int userId) {
        return this.userDao.resetFailedAttemptsByUserId(userId);
    }

    @Override
    public List<Role> getRoleList() {
        CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return this.userDao.getCanCreateRoleList(curUser.getRole().getRoleId());
    }

    @Override
    public boolean existUserByUsername(String username) {
        if (this.userDao.getUserByUsername(username) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int addUser(User user) {
        try {
//            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("User Added Successfully"));
            return this.userDao.addUser(user);
        } catch (Exception e) {
//            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(e));
            return 0;
        }
    }

    @Override
    public User getUserByUserId(int userId) {
        return this.userDao.getUserByUserId(userId);
    }

    @Override
    public boolean canCreateRoleByRoleId(String roleId, String canCreateRoleId) {
        return this.userDao.canCreateRoleByRoleId(roleId, canCreateRoleId);
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userDao.getUserByUsername(username);
    }

    @Override
    public void updateUser(User user) {
        this.userDao.updateUser(user);
    }

    @Override
    public String resetPasswordByUserId(int userId) {
        return this.userDao.resetPasswordByUserId(userId);
    }

    @Override
    public boolean confirmPassword(Password password) {
        return this.userDao.confirmPassword(password);
    }

    @Override
    public void updatePassword(Password password, int offset) {
        this.userDao.updatePassword(password, offset);
    }

    @Override
    public List<String> getBusinessFunctionsForUserId(int userId) {
        return this.userDao.getBusinessFunctionsForUserId(userId);
    }

    @Override
    public List<User> getUserList(boolean active, String roleId) {
        return this.userDao.getUserList(active, roleId);
    }

    
}
