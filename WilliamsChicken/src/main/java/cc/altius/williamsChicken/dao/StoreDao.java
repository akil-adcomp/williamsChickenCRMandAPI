/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.dao;

import cc.altius.williamsChicken.model.Store;
import java.util.List;

/**
 *
 * @author altius
 */
public interface StoreDao {

    public List<Store> getStoreList();

    public int addStore(Store store);

    public Store getStoreByStoreId(int storeId);

    public int updateStore(Store store);

    public int updateStoreActiveStatus(List<Integer> storeIds, int publishValue);
}
