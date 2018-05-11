/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.service;

import cc.altius.williamsChicken.model.Vendor;
import java.util.List;
import java.util.Map;

/**
 *
 * @author manish
 */
public interface VendorService {

    public List<Vendor> getVendorList();

    public int addVendor(Vendor vendor);

    public Vendor getVendorByVendorId(int vendorId);

    public int updateVendor(Vendor vendor);

    public Map updateVendorActiveStatus(String vendorIds, int publishValue);
}
