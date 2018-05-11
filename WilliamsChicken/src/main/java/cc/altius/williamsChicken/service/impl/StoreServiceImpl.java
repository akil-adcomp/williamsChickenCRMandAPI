/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.service.impl;

import cc.altius.williamsChicken.dao.StoreDao;
import cc.altius.williamsChicken.model.Store;
import cc.altius.williamsChicken.service.StoreService;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author altius
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreDao storeDao;

    @Override
    public List<Store> getStoreList() {
        return this.storeDao.getStoreList();
    }

    @Override
    public int addStore(Store store) {
        return this.storeDao.addStore(store);
    }

    @Override
    public Store getStoreByStoreId(int storeId) {
        return this.storeDao.getStoreByStoreId(storeId);
    }

    @Override
    public int updateStore(Store store) {
        return this.storeDao.updateStore(store);
    }

    @Override
    public Map updateStoreActiveStatus(String storeIds, int publishValue) {
        Map map = new HashMap();
        try {
            String[] arr = storeIds.split(",");
            List<Integer> Ids = new LinkedList<>();
            for (String s : arr) {
                Ids.add(Integer.parseInt(s));
            }
            int result = this.storeDao.updateStoreActiveStatus(Ids, publishValue);
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
