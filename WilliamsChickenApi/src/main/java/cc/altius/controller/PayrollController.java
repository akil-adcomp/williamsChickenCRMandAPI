/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.controller;

import cc.altius.model.Payroll;
import cc.altius.model.ResponseFormat;
import cc.altius.model.ValidTokenAndExpDate;
import cc.altius.service.ApiService;
import cc.altius.service.PayrollService;
import cc.altius.utils.ErrorConstants;
import cc.altius.utils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author manish
 */
@RestController
public class PayrollController {

    @Autowired
    private PayrollService payrollService;
    @Autowired
    private ApiService apiService;
    private @Value("#{settings['williamsChickenApiToken']}")
    String williamsChickenApiToken;

    @PostMapping(value = "/addPayroll", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Add a payroll method",
            notes = "Add a payroll method",
            response = Payroll.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = Boolean.class, message = "OK"),
        @ApiResponse(code = 401, response = ResponseFormat.class, message = "INVALID TOKEN"),
        @ApiResponse(code = 500, response = ResponseFormat.class, message = "Exception Occured"),})
    public ResponseEntity addPaymentMethod(
            @ApiParam(name = "token", value = "Token for authentication", required = true)
            @RequestHeader(value = "token") String token,
            @ApiParam(name = "apptoken", value = "APP Token for authentication", required = true)
            @RequestHeader(value = "apptoken") String appToken,
            @ApiParam(name = "userId", value = "Users Id", required = true)
            @RequestHeader(value = "userId") int userId,
            @ApiParam(name = "content", value = "content", required = true)
            @RequestBody String content) {
        ResponseFormat responseFormat = new ResponseFormat();
        if (appToken.equals(williamsChickenApiToken)) {
            try {
                LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(" token :" + token));
                LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog(" token :" + token));
                ValidTokenAndExpDate validTokenAndExpDate = this.apiService.validateToken(token, userId);
                if (validTokenAndExpDate.isIsValid()) {
                    LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Valid Request"));
                    LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Valid Request"));
                    Gson gson = new Gson();

                    List<Payroll> payroll = gson.fromJson(content, new TypeToken<ArrayList<Payroll>>() {
                    }.getType());
                    
                    LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("payroll " + payroll.toString()));
                    LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("payroll " + payroll.toString()));

                    int insertedCount = this.payrollService.addPayroll(payroll, userId);
                    if (insertedCount > 0) {
                        LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("insertedCount :" + insertedCount));
                        LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("insertedCount :" + insertedCount));
                        responseFormat.setStatus("success");
                        return new ResponseEntity(responseFormat, HttpStatus.OK);
                    } else {
                        LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("insertedCount :" + insertedCount));
                        LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("insertedCount :" + insertedCount));
                        return new ResponseEntity("failed", HttpStatus.OK);
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

    @PostMapping(value = "/getPayrollReport", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get Payroll Report",
            notes = "Get Payroll Report",
            response = Payroll.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = Boolean.class, message = "OK"),
        @ApiResponse(code = 401, response = ResponseFormat.class, message = "INVALID TOKEN"),
        @ApiResponse(code = 500, response = ResponseFormat.class, message = "Exception Occured"),})
    public ResponseEntity getPayrollReport(
            @ApiParam(name = "token", value = "Token for authentication", required = true)
            @RequestHeader(value = "token") String token,
            @ApiParam(name = "startDate", value = "Start Date for report", required = true)
            @RequestHeader(value = "startDate") String startDate,
            @ApiParam(name = "userId", value = "Users Id", required = true)
            @RequestHeader(value = "userId") int userId,
            @ApiParam(name = "endDate", value = "End Date for report", required = true)
            @RequestHeader(value = "endDate") String endDate) {
        ResponseFormat responseFormat = new ResponseFormat();
        try {
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(" token :" + token));
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog(" token :" + token));
            ValidTokenAndExpDate validTokenAndExpDate = this.apiService.validateToken(token, userId);
            if (validTokenAndExpDate.isIsValid()) {
                LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Valid Request"));
                LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Valid Request"));

                List<Payroll> payrollList = this.payrollService.getPayrollReportByDate(startDate, endDate);
                if (payrollList.isEmpty()) {
                    responseFormat.setStatus("failed");
                    responseFormat.setFailedReason("EmptyResultSetException");
                    responseFormat.setFailedValue(ErrorConstants.EMPTY_LIST);
                    return new ResponseEntity(responseFormat, HttpStatus.NOT_ACCEPTABLE);
                } else {
                    LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("payroll list :" + payrollList));
                    LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("payroll list :" + payrollList));
                    return new ResponseEntity(payrollList, HttpStatus.OK);
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
            responseFormat.setFailedReason("Exception Occured :" + e.getClass().getSimpleName());
            return new ResponseEntity(responseFormat, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/checkPayrollRecordExit", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Check Payroll Record Exit",
            notes = "Check payroll record exit for given date and store ID.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = Boolean.class, message = "OK"),
        @ApiResponse(code = 401, response = ResponseFormat.class, message = "INVALID TOKEN"),
        @ApiResponse(code = 500, response = ResponseFormat.class, message = "Exception Occured"),})
    public ResponseEntity checkPayrollRecordExit(
            @ApiParam(name = "token", value = "Token for authentication", required = true)
            @RequestHeader(value = "token") String token,
            @ApiParam(name = "storeId", value = "App for Store Id", required = true)
            @RequestHeader(value = "storeId") int storeId,
            @ApiParam(name = "apptoken", value = "APP Token for authentication", required = true)
            @RequestHeader(value = "apptoken") String appToken,
            @ApiParam(name = "startDate", value = "Start Date for report", required = true)
            @RequestHeader(value = "startDate") String startDate,
            @ApiParam(name = "stopDate", value = "Stop Date for report", required = true)
            @RequestHeader(value = "stopDate") String stopDate,
            @ApiParam(name = "userId", value = "Users Id", required = true)
            @RequestHeader(value = "userId") int userId) {
        ResponseFormat responseFormat = new ResponseFormat();
        if (appToken.equals(williamsChickenApiToken)) {
            try {
                LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(" token :" + token));
                LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog(" token :" + token));
                ValidTokenAndExpDate validTokenAndExpDate = this.apiService.validateToken(token, userId);
                int isExitRecord = this.payrollService.isPayrollRecordExit(startDate,stopDate, storeId);
                System.out.println("isExist"+isExitRecord);
                if (validTokenAndExpDate.isIsValid()) {
                    LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Valid Request"));
                    LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Valid Request"));
                    if (isExitRecord > 0) {
                        System.out.println("already exist");
                        responseFormat.setStatus("failed");
                        responseFormat.setFailedReason("Already Exist");
                        responseFormat.setFailedValue(ErrorConstants.ALREADY_EXIT_RECORD);
                        return new ResponseEntity(responseFormat, HttpStatus.NOT_ACCEPTABLE);
                    } else {
                        System.out.println("okkk");
                        responseFormat.setStatus("success");
                        return new ResponseEntity(responseFormat,HttpStatus.OK);
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
