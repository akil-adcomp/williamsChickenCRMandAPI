/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.controller;

import cc.altius.model.Employee;
import cc.altius.model.ResponseFormat;
import cc.altius.model.Store;
import cc.altius.model.ValidToken;
import cc.altius.model.Vendor;
import cc.altius.service.ApiService;
import cc.altius.service.StoreService;
import cc.altius.utils.ErrorConstants;
import cc.altius.utils.LogUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import java.util.List;
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
public class StoreController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private ApiService apiService;
    private @Value("#{settings['williamsChickenApiToken']}")
    String williamsChickenApiToken;

    @PostMapping(value = "/getStoreList", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get Store List",
    notes = "Get Store List",
    response = Employee.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = Boolean.class, message = "OK"),
        @ApiResponse(code = 401, response = ResponseFormat.class, message = "INVALID TOKEN"),
        @ApiResponse(code = 500, response = ResponseFormat.class, message = "Exception Occured"),})
    public ResponseEntity getVendorList(
            @ApiParam(name = "token", value = "Token for authentication", required = true)
            @RequestHeader(value = "token") String token,
            @ApiParam(name = "apptoken", value = "APP Token for authentication", required = true)
            @RequestHeader(value = "apptoken") String appToken,
            @ApiParam(name = "userId", value = "Users Id", required = true)
            @RequestHeader(value = "userId") int userId) {
        LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("UserId " + userId));
        LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("UserId " + userId));
        ResponseFormat responseFormat = new ResponseFormat();
        Map<String, Object> responseMap = null;
        if (appToken.equals(williamsChickenApiToken)) {
            try {
                ValidToken validToken = this.apiService.validateToken(token, userId);
                if (validToken.isIsValid()) {
                    List<Store> storeList = this.storeService.getStoreList();
                    return new ResponseEntity(storeList, HttpStatus.OK);
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
        } else {
            responseFormat.setStatus("Failed");
            responseFormat.setFailedReason("Invalid token");
            responseFormat.setFailedValue(ErrorConstants.INVALID_VERSION_TOKEN);
            return new ResponseEntity(responseFormat, HttpStatus.NOT_ACCEPTABLE);

        }
    }

    @PostMapping(value = "/getStoreId", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get Store List",
    notes = "Get Store Id",
    response = Employee.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = Boolean.class, message = "OK"),
        @ApiResponse(code = 401, response = ResponseFormat.class, message = "INVALID TOKEN"),
        @ApiResponse(code = 500, response = ResponseFormat.class, message = "Exception Occured"),})
    public ResponseEntity getStoreId(
            @ApiParam(name = "token", value = "Token for authentication", required = true)
            @RequestHeader(value = "token") String token,
            @ApiParam(name = "apptoken", value = "APP Token for authentication", required = true)
            @RequestHeader(value = "apptoken") String appToken,
            @ApiParam(name = "userId", value = "Users Id", required = true)
            @RequestHeader(value = "userId") int userId) {
        LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("UserId " + userId));
        LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("UserId " + userId));
        ResponseFormat responseFormat = new ResponseFormat();
        Map<String, Object> responseMap = null;
        if (appToken.equals(williamsChickenApiToken)) {
            try {
                ValidToken validToken = this.apiService.validateToken(token, userId);
                if (validToken.isIsValid()) {
                    Store store = this.storeService.getStoreIdByUserId(userId);
                    return new ResponseEntity(store, HttpStatus.OK);
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
        } else {
            responseFormat.setStatus("Failed");
            responseFormat.setFailedReason("Invalid token");
            responseFormat.setFailedValue(ErrorConstants.INVALID_VERSION_TOKEN);
            return new ResponseEntity(responseFormat, HttpStatus.NOT_ACCEPTABLE);

        }
    }
}
