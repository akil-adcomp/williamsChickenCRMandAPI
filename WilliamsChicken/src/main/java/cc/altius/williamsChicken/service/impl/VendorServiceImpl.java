/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.service.impl;

import cc.altius.williamsChicken.dao.VendorDao;
import cc.altius.williamsChicken.model.Vendor;
import cc.altius.williamsChicken.service.VendorService;
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
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorDao vendorDao;

    @Override
    public int addVendor(Vendor vendor) {
        return this.vendorDao.addVendor(vendor);
    }

    @Override
    public Vendor getVendorByVendorId(int vendorId) {
        return this.vendorDao.getVendorByVendorId(vendorId);
    }

    @Override
    public int updateVendor(Vendor vendor) {
        return this.vendorDao.updateVendor(vendor);
    }

    @Override
    public Map updateVendorActiveStatus(String vendorIds, int publishValue) {
        Map map = new HashMap();
        try {
            String[] arr = vendorIds.split(",");
            List<Integer> Ids = new LinkedList<>();
            for (String s : arr) {
                Ids.add(Integer.parseInt(s));
            }
            int result = this.vendorDao.updateVendorActiveStatus(Ids, publishValue);
            if (result > 0) {
                map.put("result", 1);
            } else {
                map.put("result", 0);
            }

        } catch (Exception e) {
            map.put("result", 0);
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public List<Vendor> getVendorList() {
        return this.vendorDao.getVendorList();
    }
}
