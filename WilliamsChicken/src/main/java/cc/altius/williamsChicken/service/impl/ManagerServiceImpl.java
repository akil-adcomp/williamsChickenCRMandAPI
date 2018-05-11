/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.service.impl;

import cc.altius.williamsChicken.dao.ManagerDao;
import cc.altius.williamsChicken.model.Manager;
import cc.altius.williamsChicken.model.Vendor;
import cc.altius.williamsChicken.service.ManagerService;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author manish
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Override
    public List<Manager> getManagerList() {
        return this.managerDao.getManagerList();
    }

    @Override
    public int addManager(Manager manager) {
        return this.managerDao.addManager(manager);
    }

    @Override
    public Manager getManagerByManagerId(int managerId) {
        return this.managerDao.getManagerByManagerId(managerId);
    }

    @Override
    public int updateManager(Manager manager) {
        return this.managerDao.updateManager(manager);
    }

    @Override
    public Map updateManagerActiveStatus(String managerIds, int publishValue) {
        Map map = new HashMap();
        try {
            String[] arr = managerIds.split(",");
            List<Integer> Ids = new LinkedList<>();
            for (String s : arr) {
                Ids.add(Integer.parseInt(s));
            }
            int result = this.managerDao.updateManagerActiveStatus(Ids, publishValue);
            if (result > 0) {
                map.put("result", 1);
            } else {
                map.put("result", 0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", 0);
        }
        return map;
    }
}
