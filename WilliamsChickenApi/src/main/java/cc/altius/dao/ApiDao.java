/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.dao;

import cc.altius.model.ValidTokenAndExpDate;
import java.util.Map;

/**
 *
 * @author altius
 */
public interface ApiDao {

    public Map<String, Object> checkCustomerToken(int userId, String password);

    public Map<String, Object> signIn(String emailId, String password);

    public Map<String, Object> forgotPassword(String emailIdo);

    public int updatePassword(int userId, String newPassword);

    public Map<String, Object> getTokenByUserId(int userId);

    public Map<String, Object> updateUserAuthorization(int userId, int authorizeFlag);

    public ValidTokenAndExpDate validateToken(String token, int userId);

    public String updateToken(String oldToken, int userId);
}
