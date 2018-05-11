/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.dao;

import cc.altius.williamsChicken.model.Vendor;
import java.util.List;

/**
 *
 * @author manish
 */
public interface VendorDao {

    public int addVendor(Vendor vendor);

    public Vendor getVendorByVendorId(int vendorId);

    public int updateVendor(Vendor vendor);

    public int updateVendorActiveStatus(List<Integer> vendorIds, int publishValue);

    public List<Vendor> getVendorList();
}
