/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.service.impl;

import cc.altius.dao.ApiDao;
import cc.altius.framework.GlobalConstants;
import cc.altius.model.ValidToken;
import cc.altius.service.ApiService;
import cc.altius.utils.ErrorConstants;
import cc.altius.utils.LogUtils;
import cc.altius.utils.PassPhrase;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author altius
 */
@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiDao apiDao;

    @Override
    public Map<String, Object> signIn(String userName, String password) {
        return this.apiDao.signIn(userName, password);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> forgotPassword(String emailId) {
        Boolean updatedStatus = false;
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {

            Boolean existingFlag = (Boolean) responseMap.get("existingFlag");

            responseMap.put("updatedStatus", updatedStatus);
            return responseMap;
        } catch (IncorrectResultSizeDataAccessException i) {
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("User Does Not exist "));
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("User Does Not exist "));
            responseMap.put("failedReason", "Exception Occured");
            responseMap.put("failedValue", HttpStatus.INTERNAL_SERVER_ERROR);
            responseMap.put("updatedStatus", false);
            return responseMap;
        }
    }

    @Override
    public Map<String, Object> updatePassword(String emailId, String oldPassword, String newPassword) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap = this.apiDao.checkCustomerToken(emailId, oldPassword);
        if ((Boolean) responseMap.get("isCustomerValid")) {
            int i = this.apiDao.updatePassword(emailId, newPassword);
            if (i > 0) {
                responseMap.put("status", "Success");
            } else {
                responseMap.put("status", "Failed");
            }
        } else {
            responseMap.put("status", "Failed");
        }
        return responseMap;
    }

    /*
     * Returns true if the Token is valid and false if it is not valid
     */
    @Override
    public ValidToken validateToken(String token, int userId) {
        return this.apiDao.validateToken(token, userId);
    }

    @Override
    public Map<String, Object> getTokenByUserId(int userId) {
        return this.apiDao.getTokenByUserId(userId);
    }

    @Override
    public Map<String, Object> updateUserAuthorization(int userId, int authorizeFlag) {
        return this.apiDao.updateUserAuthorization(userId, authorizeFlag);
    }

    @Override
    public String updateToken(String string, int userId) {
        return this.apiDao.updateToken(string, userId);
    }
}
