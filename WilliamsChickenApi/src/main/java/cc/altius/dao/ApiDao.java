/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.dao;

import cc.altius.model.ValidToken;
import java.util.Map;

/**
 *
 * @author altius
 */
public interface ApiDao {

    public Map<String, Object> checkExistingCustomer(String mobileNo);

    public Map<String, Object> checkCustomerValidity(String mobileNo, String password);

    public Map<String, Object> signIn(String mobileNo, String password);

    public Map<String, Object> signUp(String mobileNo, String password);

//    public Map<String, Object> signout(int userId);

    public Map<String, Object> forgotPassword(String mobileNo);

    public int updatePassword(String mobileNo, String newPassword);

    public Map<String, Object> getTokenByUserId(int userId);

    public Map<String, Object> updateUserAuthorization(int userId, int authorizeFlag);

    public ValidToken validateToken(String token, int userId);
    
    public String updateToken(String oldToken, int userId);
}
