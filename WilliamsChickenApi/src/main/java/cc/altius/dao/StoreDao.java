/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.dao;

import cc.altius.model.Store;
import java.util.List;

/**
 *
 * @author altius
 */
public interface StoreDao {

    public List<Store> getStoreList();
    
    public Store getStoreIdByUserId(int userId);
}
