/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.dao;

import cc.altius.williamsChicken.model.Manager;
import java.util.List;

/**
 *
 * @author altius
 */
public interface ManagerDao {

    public List<Manager> getManagerList();

    public int addManager(Manager manager);

    public Manager getManagerByManagerId(int managerId);

    public int updateManager(Manager manager);

    public int updateManagerActiveStatus(List<Integer> managerIds, int publishValue);
}
