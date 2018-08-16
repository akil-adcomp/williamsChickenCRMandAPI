/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.service;

import cc.altius.model.ValidTokenAndExpDate;
import java.util.Map;

/**
 *
 * @author altius
 */
public interface ApiService {

    public Map<String, Object> signIn(String emailId, String password);

    public Map<String, Object> forgotPassword(String emailId);

    public Map<String, Object> updatePassword(int userId, String oldPassword, String newPassword);

    public ValidTokenAndExpDate validateToken(String token, int userId);

    public Map<String, Object> getTokenByUserId(int userId);

    public Map<String, Object> updateUserAuthorization(int userId, int authorizeFlag);

    public String updateToken(String string, int userId);
}
