/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.service.impl;

import cc.altius.dao.BankRegistrationDao;
import cc.altius.model.BankRegister;
import cc.altius.service.BankRegistrationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nikhil Pande
 */
@Service
public class BankRegistrationServiceImpl implements BankRegistrationService {

    @Autowired
    private BankRegistrationDao bankRegistrationDao;

    @Override
    public int addBankRegister(List<BankRegister> list, int userId) {
        return this.bankRegistrationDao.addBankRegister(list, userId);
    }

    @Override
    public double getTotalDepositsByDateAndStoreId(String submitDate, int storeId) {
        return this.bankRegistrationDao.getTotalDepositsByDateAndStoreId(submitDate, storeId);
    }

    @Override
    public boolean isBankRegisterRecordExit(String submitDate, int storeId) {
        return this.bankRegistrationDao.isBankRegisterRecordExit(submitDate, storeId);
    }

}
