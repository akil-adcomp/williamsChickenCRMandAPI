/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model.DTO.mapper;

import cc.altius.williamsChicken.model.BankRegisterDetails;
import cc.altius.williamsChicken.model.DTO.BankRegisterDTO;
import cc.altius.williamsChicken.model.PaymentMode;
import cc.altius.williamsChicken.model.Store;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 *
 * @author Nikhil Pande
 */
public class BankRegisterResultSetExtractor implements ResultSetExtractor<List<BankRegisterDTO>> {

    @Override
    public List<BankRegisterDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
        int oldStoreId = 0;
        int newStoreId = -1;
        String newCreated = null;
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String oldCreatedDate = sdf.format(d);
        List<BankRegisterDTO> bankRegisterList = new LinkedList<>();
        BankRegisterDTO brdto = new BankRegisterDTO();
        boolean isFirst = true;
        while (rs.next()) {
            newStoreId = rs.getInt("STORE_ID");
            newCreated = sdf.format(rs.getDate("SUBMIT_DATE"));
            if (oldStoreId != newStoreId || !oldCreatedDate.equals(newCreated)) {
                if (!isFirst) {
                    bankRegisterList.add(brdto);
                }
                isFirst = false;
                brdto = new BankRegisterDTO();
                Store s = new Store();
                s.setStoreId(rs.getInt("STORE_ID"));
                s.setStoreName(rs.getString("STORE_NAME"));
                brdto.setStore(s);
                brdto.setSubmitDate(rs.getTimestamp("SUBMIT_DATE"));
            }
            BankRegisterDetails brd = new BankRegisterDetails();
            PaymentMode pm = new PaymentMode();
            pm.setPayemntModeId(rs.getInt("PAYMENT_MODE_ID"));
            pm.setPayemntModeDesc(rs.getString("PAYMENT_MODE_NAME"));
            brd.setPaymentMode(pm);
            brd.setManagerId(rs.getInt("MANAGER_ID"));
            brd.setManagerName(rs.getString("managerName"));
            brd.setInitials(rs.getString("INITIALS"));
            brd.setAmount(rs.getDouble("amount"));
            brdto.getListBankRegisterDetails().add(brd);
            brdto.addTotalAmount(brd.getAmount());
            oldStoreId = newStoreId;
            oldCreatedDate = newCreated;
        }
        if (!isFirst) {
            bankRegisterList.add(brdto);
        }
        return bankRegisterList;
    }

}
