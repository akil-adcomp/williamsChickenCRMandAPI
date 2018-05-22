/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.controller;

import cc.altius.framework.GlobalConstants;
import cc.altius.model.DTO.LoginUserDetails;
import cc.altius.model.ResponseFormat;
import cc.altius.model.Token;
import cc.altius.model.TokenUserId;
import cc.altius.model.ValidToken;
import cc.altius.service.ApiService;
import cc.altius.utils.ErrorConstants;
import cc.altius.utils.LogUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author altius
 */
@RestController
public class APILogIn {

    @Autowired
    private ApiService apiService;
    private @Value("#{settings['williamsChickenApiToken']}")
    String williamsChickenApiToken;

    @PostMapping(value = "/signIn", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Existing Customer logs in with password",
    notes = "Existing Customer logs in by passing password and return token,userId,authorization status",
    response = TokenUserId.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = TokenUserId.class, message = "OK"),
        @ApiResponse(code = 404, response = ResponseFormat.class, message = "TOKEN_RETRIVAL"),
        @ApiResponse(code = 500, response = ResponseFormat.class, message = "Exception Occured")})
    public ResponseEntity signIn(
            @ApiParam(name = "apptoken", value = "App Token for validation", required = true)
            @RequestHeader(value = "apptoken") String token,
            @ApiParam(name = "userName", value = "User Name", required = true)
            @RequestHeader(value = "userName") String userName,
            @ApiParam(name = "password", value = "Users password", required = true)
            @RequestHeader(value = "password") String password) {
        LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("UserName " + userName + "/password :" + password));
        LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("UserName " + userName + "/password :" + password));
        LoginUserDetails loginUserDetails = null;
        Map<String, Object> responseMap = null;
        ResponseFormat responseFormat = new ResponseFormat();

        if (token.equals(williamsChickenApiToken)) {
            try {
                responseMap = this.apiService.signIn(userName, password);
                loginUserDetails = (LoginUserDetails) responseMap.get("loginUserDetails");
                if (loginUserDetails != null) {
                    return new ResponseEntity(loginUserDetails, HttpStatus.OK);
                } else {
                    responseFormat.setStatus("failed");
                    responseFormat.setFailedReason(responseMap.get("failedReason") + "");
                    responseFormat.setFailedValue((Integer) responseMap.get("failedValue"));
                    responseFormat.setParameter("userName :" + userName + "/password :" + password);
                    return new ResponseEntity(responseFormat, HttpStatus.NOT_ACCEPTABLE);
                }
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Error occured Because : \n " + e));
                LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Error occured Because : \n " + e));
                responseFormat.setStatus("failed");
                responseFormat.setFailedReason("Exception Occured :" + e.getClass());
                responseFormat.setFailedValue(ErrorConstants.EXCEPTION_OCCURED);
                responseFormat.setParameter("userName :" + userName + "/passowrd :" + password);
                return new ResponseEntity(responseFormat, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            //             token validation failed
            responseFormat.setStatus("Failed");
            responseFormat.setFailedReason("Invalid token");
            responseFormat.setFailedValue(ErrorConstants.INVALID_VERSION_TOKEN);
            return new ResponseEntity(responseFormat, HttpStatus.NOT_ACCEPTABLE);

        }

    }

    @PostMapping(value = "/forgotPassword", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = " Forgot password with emailId ",
    notes = " Ask for new password using emailId ",
    response = TokenUserId.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = TokenUserId.class, message = "OK"),
        @ApiResponse(code = 404, response = ResponseFormat.class, message = "OTP SENDING_FAILED"),
        @ApiResponse(code = 500, response = ResponseFormat.class, message = "Exception Occured")})
    public ResponseEntity forgotPassword(
            @ApiParam(name = "emailId", value = "Email Id for forgot password", required = true)
            @RequestHeader(value = "emailId") String emailId) {
        LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("emailId " + emailId));
        Boolean updatedStatus;
        ResponseFormat responseFormat = new ResponseFormat();
        Map<String, Object> responseMap = null;
        try {
            responseMap = this.apiService.forgotPassword(emailId);
            updatedStatus = (Boolean) responseMap.get("updatedStatus");
            if (updatedStatus) {
                return new ResponseEntity(updatedStatus, HttpStatus.OK);
            } else {
                responseFormat.setStatus("failed");
                responseFormat.setFailedReason(responseMap.get("failedReason") + "");
                responseFormat.setFailedValue((Integer) responseMap.get("failedValue"));
                responseFormat.setParameter("emailId :" + emailId);
                return new ResponseEntity(responseFormat, HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Error occured Because : \n " + e));
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Error occured Because : \n " + e));
            responseFormat.setStatus("failed");
            responseFormat.setFailedReason("Exception Occured :" + e.getClass());
            responseFormat.setFailedValue(ErrorConstants.EXCEPTION_OCCURED);
            return new ResponseEntity(responseFormat, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/updatePassword", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = " Update password with emailId ",
    notes = " Update new password using emailId ",
    response = TokenUserId.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = TokenUserId.class, message = "OK"),
        @ApiResponse(code = 404, response = ResponseFormat.class, message = "OTP SENDING_FAILED"),
        @ApiResponse(code = 500, response = ResponseFormat.class, message = "Exception Occured")})
    public ResponseEntity updatePassword(
            @RequestHeader(value = "token") String token,
            @RequestHeader(value = "emailId") String emailId,
            @ApiParam(name = "userId", value = "Users Id", required = true)
            @RequestHeader(value = "userId") int userId,
            @RequestHeader(value = "oldPassword") String oldPassword,
            @RequestHeader(value = "newPassword") String newPassword) {
        LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("emailId " + emailId + "/oldPassword :" + oldPassword + "/newPassword:" + newPassword));
        String status = "";
        ResponseFormat responseFormat = new ResponseFormat();
        Map<String, Object> responseMap = null;
        try {
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(" token :" + token));
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog(" token :" + token));
            ValidToken validToken = this.apiService.validateToken(token, userId);
            if (validToken.isIsValid()) {
                responseMap = this.apiService.updatePassword(emailId, oldPassword, newPassword);
                status = (String) responseMap.get("status");
                if (status.equals("Success")) {
                    return new ResponseEntity(status, HttpStatus.OK);
                } else {
                    responseFormat.setStatus("failed");
                    responseFormat.setFailedReason(responseMap.get("failedReason") + "");
                    responseFormat.setFailedValue((Integer) responseMap.get("failedValue"));
                    return new ResponseEntity(responseFormat, HttpStatus.NOT_ACCEPTABLE);
                }
            } else {
                responseFormat.setStatus("failed");
                responseFormat.setFailedReason("INVALID_TOKEN");
                responseFormat.setFailedValue(ErrorConstants.INVALID_TOKEN);
                return new ResponseEntity(responseFormat, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Error occured Because : \n " + e));
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Error occured Because : \n " + e));
            responseFormat.setStatus("failed");
            responseFormat.setFailedReason("Exception Occured :" + e.getClass());
            responseFormat.setFailedValue(ErrorConstants.EXCEPTION_OCCURED);
            return new ResponseEntity(responseFormat, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/getTokenByUserId", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get New Token By UserId",
    notes = "Get token deatils by userId .It will forcefully create new token even if existing token is not expired in order to avoid loop between 4hrs to 4.15hrs ",
    response = Token.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = Token.class, message = "OK"),
        @ApiResponse(code = 404, response = ResponseFormat.class, message = "USER_DOES_NOT_EXIST / TOKEN_RETRIVAL"),
        @ApiResponse(code = 500, response = ResponseFormat.class, message = "Exception Occured")})
    public ResponseEntity getTokenByUserId(
            @ApiParam(name = "apptoken", value = "App Token for validation", required = true)
            @RequestHeader(value = "apptoken") String token,
            @ApiParam(name = "userId", value = "Users Id", required = true)
            @RequestHeader(value = "userId") int userId) {
        LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("UserId " + userId));
        LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("UserId " + userId));
        ResponseFormat responseFormat = new ResponseFormat();
        Map<String, Object> responseMap = null;
        if (token.equals(williamsChickenApiToken)) {
            try {
                responseMap = this.apiService.getTokenByUserId(userId);
                if (responseMap.get("token") != null) {
                    return new ResponseEntity(responseMap.get("token"), HttpStatus.OK);
                } else {
                    responseFormat.setStatus("failed");
                    responseFormat.setFailedReason(responseMap.get("failedReason") + "");
                    responseFormat.setFailedValue((Integer) responseMap.get("failedValue"));
                    responseFormat.setParameter("UserId :" + userId);
                    return new ResponseEntity(responseFormat, HttpStatus.NOT_ACCEPTABLE);
                }
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Error occured Because : \n " + e));
                LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Error occured Because : \n " + e));
                responseFormat.setStatus("failed");
                responseFormat.setFailedReason("Exception Occured :" + e.getClass());
                responseFormat.setFailedValue(ErrorConstants.EXCEPTION_OCCURED);
                return new ResponseEntity(responseFormat, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            responseFormat.setStatus("Failed");
            responseFormat.setFailedReason("Invalid token");
            responseFormat.setFailedValue(ErrorConstants.INVALID_VERSION_TOKEN);
            return new ResponseEntity(responseFormat, HttpStatus.NOT_ACCEPTABLE);

        }
    }

    @PostMapping(value = "/updateToken", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get updated token", notes = "Returns updated token", response = String.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = Boolean.class, message = "OK"),
        @ApiResponse(code = 401, response = ResponseFormat.class, message = "INVALID TOKEN"),
        @ApiResponse(code = 500, response = ResponseFormat.class, message = "Exception Occured")})
    public ResponseEntity updateToken(
            @ApiParam(name = "apptoken", value = "App Token for validation", required = true)
            @RequestHeader(value = "apptoken") String token,
            @ApiParam(name = "userId", value = "userId for update token", required = true)
            @RequestHeader(value = "userId") final int userId) {
        LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("UserId " + userId));
        LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("UserId " + userId));

        LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("apptoken " + token));
        LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("oldToken " + token));

        ResponseFormat responseFormat = new ResponseFormat();
        if (token.equals(williamsChickenApiToken)) {
            try {
                String updateToken = this.apiService.updateToken(token, userId);
                if (updateToken != null) {
                    return new ResponseEntity(updateToken, HttpStatus.OK);
                } else {
                    responseFormat.setStatus("Failed");
                    responseFormat.setFailedReason("FAILED");
                    responseFormat.setFailedValue(ErrorConstants.INVALID_TOKEN);
                    return new ResponseEntity(responseFormat, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } catch (Exception e) {
                e.printStackTrace();
                responseFormat.setStatus("Failed");
                responseFormat.setFailedReason("Exception Occured :" + e);
                responseFormat.setFailedValue(ErrorConstants.UPDATE_FAILED);
                return new ResponseEntity(responseFormat, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            responseFormat.setStatus("Failed");
            responseFormat.setFailedReason("Invalid token");
            responseFormat.setFailedValue(ErrorConstants.INVALID_VERSION_TOKEN);
            return new ResponseEntity(responseFormat, HttpStatus.NOT_ACCEPTABLE);

        }
    }
}
