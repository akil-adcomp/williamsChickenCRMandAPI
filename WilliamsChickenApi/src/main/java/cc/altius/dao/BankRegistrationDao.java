/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.dao;

import cc.altius.model.BankRegister;
import java.util.List;

/**
 *
 * @author Nikhil Pande
 */
public interface BankRegistrationDao {

    public int addBankRegister(List<BankRegister> list, int userId);

    public double getTotalDepositsByDateAndStoreId(String submitDate, int storeId);

    public boolean isBankRegisterRecordExit(String submitDate, int storeId);
}
