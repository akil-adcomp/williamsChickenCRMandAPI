/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model.mapper;

import cc.altius.model.BankRegister;
import cc.altius.model.PaymentMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author altius
 */
public class BankRegisterRowMapper implements RowMapper<BankRegister> {

    @Override
    public BankRegister mapRow(ResultSet rs, int i) throws SQLException {
        BankRegister bankRegister=new BankRegister();
        bankRegister.setAmount(rs.getInt("AMOUNT"));
        bankRegister.setInitials(rs.getString("INITIALS"));
        bankRegister.setStoreId(rs.getInt("STORE_ID"));
        bankRegister.setSubmitDate(rs.getString("SUBMIT_DATE"));
        PaymentMode paymentMode =new PaymentMode();
        paymentMode.setPayemntModeDesc(rs.getString("PAYMENT_MODE_NAME"));
        paymentMode.setPayemntModeId(rs.getInt("PAYMENT_MODE_ID"));
        bankRegister.setPaymentMode(paymentMode);
        return  bankRegister;
    }
    
}
