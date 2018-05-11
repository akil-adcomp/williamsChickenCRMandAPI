/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.service.impl;

import cc.altius.dao.StoreDao;
import cc.altius.model.Store;
import cc.altius.service.StoreService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author manish
 */
@Service
public class StoreServiceimpl implements StoreService {

    @Autowired
    private StoreDao storeDao;

    @Override
    public List<Store> getStoreList() {
        return this.storeDao.getStoreList();
    }

    @Override
    public Store getStoreIdByUserId(int userId) {
        return this.storeDao.getStoreIdByUserId(userId);
    }
}
