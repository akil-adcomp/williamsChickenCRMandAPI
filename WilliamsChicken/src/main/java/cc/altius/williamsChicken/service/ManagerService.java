/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.service;

import cc.altius.williamsChicken.model.Manager;
import cc.altius.williamsChicken.model.Vendor;
import java.util.List;
import java.util.Map;

/**
 *
 * @author manish
 */
public interface ManagerService {

    public List<Manager> getManagerList();

    public int addManager(Manager manager);

    public Manager getManagerByManagerId(int managerId);

    public int updateManager(Manager manager);

    public Map updateManagerActiveStatus(String managerIds, int publishValue);
}
