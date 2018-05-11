/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.dao;

import cc.altius.williamsChicken.model.CustomUserDetails;
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
public interface UserDao {

    public int updateFailedAttemptsByUserId(String userName);

    public User getUserByUsername(String username);

    public int resetFailedAttemptsByUserId(int userId);

    public CustomUserDetails getUserDetailsByUsername(String username);

    public List<String> getBusinessFunctionsForUserId(int userId);

    public List<Role> getCanCreateRoleList(String roleId);

    public int addUser(User user);

    public User getUserByUserId(int userId);

    public boolean canCreateRoleByRoleId(String roleId, String canCreateRoleId);

    public void updateUser(User user);

    public String resetPasswordByUserId(int userId);

    public boolean confirmPassword(Password password);

    public void updatePassword(Password password, int offset);

    public List<User> getUserList(boolean active, String roleId);

}
