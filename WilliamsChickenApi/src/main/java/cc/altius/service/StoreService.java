/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.service;

import cc.altius.model.Store;
import java.util.List;

/**
 *
 * @author altius
 */
public interface StoreService {

    public List<Store> getStoreList();

    public Store getStoreIdByUserId(int userId);
}
