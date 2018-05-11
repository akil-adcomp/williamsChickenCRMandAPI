/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.service;

import cc.altius.williamsChicken.model.Employee;
import cc.altius.williamsChicken.model.Manager;
import cc.altius.williamsChicken.model.Password;
import cc.altius.williamsChicken.model.Role;
import cc.altius.williamsChicken.model.User;
import cc.altius.williamsChicken.model.Vendor;
import java.util.List;
import java.util.Map;

/**
 *
 * @author manish
 */
public interface UserService {

    public int updateFailedAttemptsByUserId(String userName);

    public int resetFailedAttemptsByUserId(int userId);

    public List<Role> getRoleList();

    public boolean existUserByUsername(String username);

    public int addUser(User user);

    public User getUserByUserId(int userId);

    public boolean canCreateRoleByRoleId(String roleId, String canCreateRoleId);

    public User getUserByUsername(String username);

    public void updateUser(User user);

    public String resetPasswordByUserId(int userId);

    public boolean confirmPassword(Password password);

    public void updatePassword(Password password, int offset);

    public List<String> getBusinessFunctionsForUserId(int userId);

    public List<User> getUserList(boolean active, String roleId);

}
