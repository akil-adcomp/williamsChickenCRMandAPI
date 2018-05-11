/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.service;

import cc.altius.williamsChicken.model.Store;
import java.util.List;
import java.util.Map;

/**
 *
 * @author altius
 */
public interface StoreService {

    public List<Store> getStoreList();

    public int addStore(Store store);

    public Store getStoreByStoreId(int storeId);

    public int updateStore(Store store);

     public Map updateStoreActiveStatus(String managerIds, int publishValue);
}
