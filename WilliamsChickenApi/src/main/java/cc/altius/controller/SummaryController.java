/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.controller;

import cc.altius.model.BankRegister;
import cc.altius.model.DTO.SalesSummaryDTO;
import cc.altius.model.FCW;
import cc.altius.model.Payroll;
import cc.altius.model.ResponseFormat;
import cc.altius.model.Sales;
import cc.altius.model.ValidTokenAndExpDate;
import cc.altius.service.ApiService;
import cc.altius.service.BankRegistrationService;
import cc.altius.service.SummaryService;
import cc.altius.utils.ErrorConstants;
import cc.altius.utils.LogUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
public class SummaryController {
    
    @Autowired
    private SummaryService summaryService;
    @Autowired
    private BankRegistrationService  bankRegistrationService;
    @Autowired
    private ApiService apiService;
    private @Value("#{settings['williamsChickenApiToken']}")
    String williamsChickenApiToken;
    
      
      @PostMapping(value = "/getFCWList", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get FCW List",
    notes = "Get FCW List",
    response = FCW.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = Boolean.class, message = "OK"),
        @ApiResponse(code = 401, response = ResponseFormat.class, message = "INVALID TOKEN"),
        @ApiResponse(code = 500, response = ResponseFormat.class, message = "Exception Occured"),})
    public ResponseEntity getFCWList(
            @ApiParam(name = "token", value = "Token for authentication", required = true)
            @RequestHeader(value = "token") String token,
            @ApiParam(name = "apptoken", value = "APP Token for authentication", required = true)
            @RequestHeader(value = "apptoken") String appToken,
             @ApiParam(name = "storeId", value = "store Id", required = true)
            @RequestHeader(value = "storeId") int storeId,
             @ApiParam(name = "periodId", value = "period Id", required = true)
            @RequestHeader(value = "periodId") int periodId,
            @ApiParam(name = "userId", value = "Users Id", required = true)
            @RequestHeader(value = "userId") int userId) {

        LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("UserId " + userId));
        LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("UserId " + userId));
        ResponseFormat responseFormat = new ResponseFormat();
        Map<String, Object> responseMap = null;
        if (appToken.equals(williamsChickenApiToken)) {
            try {
                ValidTokenAndExpDate validTokenAndExpDate = this.apiService.validateToken(token, userId);
                if (validTokenAndExpDate.isIsValid()) {
                    List<FCW> fcwList = this.summaryService.getFCWList(storeId,periodId);
                    return new ResponseEntity(fcwList, HttpStatus.OK);
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
            responseFormat.setFailedReason("You are using older version of APP");
            responseFormat.setFailedValue(ErrorConstants.INVALID_VERSION_TOKEN);
            return new ResponseEntity(responseFormat, HttpStatus.NOT_ACCEPTABLE);

        }
    }
      
       @PostMapping(value = "/getBankRegisterList", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get Bank Register List",
    notes = "Get Bank Register List",
    response = BankRegister.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = Boolean.class, message = "OK"),
        @ApiResponse(code = 401, response = ResponseFormat.class, message = "INVALID TOKEN"),
        @ApiResponse(code = 500, response = ResponseFormat.class, message = "Exception Occured"),})
    public ResponseEntity getBankRegisterList(
            @ApiParam(name = "token", value = "Token for authentication", required = true)
            @RequestHeader(value = "token") String token,
            @ApiParam(name = "apptoken", value = "APP Token for authentication", required = true)
            @RequestHeader(value = "apptoken") String appToken,
             @ApiParam(name = "storeId", value = "store Id", required = true)
            @RequestHeader(value = "storeId") int storeId,
             @ApiParam(name = "periodId", value = "period Id", required = true)
            @RequestHeader(value = "periodId") int periodId,
            @ApiParam(name = "userId", value = "Users Id", required = true)
            @RequestHeader(value = "userId") int userId) {

        LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("UserId " + userId));
        LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("UserId " + userId));
        ResponseFormat responseFormat = new ResponseFormat();
        Map<String, Object> responseMap = null;
        if (appToken.equals(williamsChickenApiToken)) {
            try {
                ValidTokenAndExpDate validTokenAndExpDate = this.apiService.validateToken(token, userId);
                if (validTokenAndExpDate.isIsValid()) {
                    List<BankRegister> bankRegisterList = this.summaryService.getBankRegisterList(storeId,periodId);
                    return new ResponseEntity(bankRegisterList, HttpStatus.OK);
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
            responseFormat.setFailedReason("You are using older version of APP");
            responseFormat.setFailedValue(ErrorConstants.INVALID_VERSION_TOKEN);
            return new ResponseEntity(responseFormat, HttpStatus.NOT_ACCEPTABLE);

        }
    }
       
        @PostMapping(value = "/getPayrollList", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get Payroll List",
    notes = "Get Payroll List",
    response = Payroll.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = Boolean.class, message = "OK"),
        @ApiResponse(code = 401, response = ResponseFormat.class, message = "INVALID TOKEN"),
        @ApiResponse(code = 500, response = ResponseFormat.class, message = "Exception Occured"),})
    public ResponseEntity getPayrollList(
            @ApiParam(name = "token", value = "Token for authentication", required = true)
            @RequestHeader(value = "token") String token,
            @ApiParam(name = "apptoken", value = "APP Token for authentication", required = true)
            @RequestHeader(value = "apptoken") String appToken,
             @ApiParam(name = "storeId", value = "store Id", required = true)
            @RequestHeader(value = "storeId") int storeId,
             @ApiParam(name = "startDate", value = "start date", required = true)
            @RequestHeader(value = "startDate") String  startDate,
             @ApiParam(name = "stopDate", value = "stop date", required = true)
            @RequestHeader(value = "stopDate") String  stopDate,
            @ApiParam(name = "userId", value = "Users Id", required = true)
            @RequestHeader(value = "userId") int userId) {

        LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("UserId " + userId));
        LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("UserId " + userId));
        ResponseFormat responseFormat = new ResponseFormat();
        Map<String, Object> responseMap = null;
        if (appToken.equals(williamsChickenApiToken)) {
            try {
                ValidTokenAndExpDate validTokenAndExpDate = this.apiService.validateToken(token, userId);
                if (validTokenAndExpDate.isIsValid()) {
                    List<Payroll> payrollList = this.summaryService.getPayrollList(storeId,startDate,stopDate);
                    return new ResponseEntity(payrollList, HttpStatus.OK);
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
            responseFormat.setFailedReason("You are using older version of APP");
            responseFormat.setFailedValue(ErrorConstants.INVALID_VERSION_TOKEN);
            return new ResponseEntity(responseFormat, HttpStatus.NOT_ACCEPTABLE);

        }
    }
        
        @PostMapping(value = "/getSalesSummaryData", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get Sales",
            notes = "Get Sales",
            response = SalesSummaryDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = Boolean.class, message = "OK"),
        @ApiResponse(code = 401, response = ResponseFormat.class, message = "INVALID TOKEN"),
        @ApiResponse(code = 500, response = ResponseFormat.class, message = "Exception Occured"),})
    public ResponseEntity getSalesSummaryData(
            @ApiParam(name = "token", value = "Token for authentication", required = true)
            @RequestHeader(value = "token") String token,
            @ApiParam(name = "storeId", value = "App for Store Id", required = true)
            @RequestHeader(value = "storeId") int storeId,
            @ApiParam(name = "apptoken", value = "APP Token for authentication", required = true)
            @RequestHeader(value = "apptoken") String appToken,
             @ApiParam(name = "periodId", value = "period Id", required = true)
            @RequestHeader(value = "periodId") int periodId,
            @ApiParam(name = "userId", value = "Users Id", required = true)
            @RequestHeader(value = "userId") int userId) {
        ResponseFormat responseFormat = new ResponseFormat();
        if (appToken.equals(williamsChickenApiToken)) {
            try {
                LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(" token :" + token));
                LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog(" token :" + token));
                ValidTokenAndExpDate validTokenAndExpDate = this.apiService.validateToken(token, userId);
                if (validTokenAndExpDate.isIsValid()) {
                    LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Valid Request"));
                    LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Valid Request"));
                        SalesSummaryDTO summaryDTO=summaryService.getSalesSummaryData(storeId, periodId);
                        return new ResponseEntity(summaryDTO, HttpStatus.OK);
                   
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
                responseFormat.setFailedReason("Exception Occured :" + e.getClass().getSimpleName());
                return new ResponseEntity(responseFormat, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            responseFormat.setStatus("Failed");
            responseFormat.setFailedReason("You are using older version of APP");
            responseFormat.setFailedValue(ErrorConstants.INVALID_VERSION_TOKEN);
            return new ResponseEntity(responseFormat, HttpStatus.NOT_ACCEPTABLE);

        }
    }
    
}
