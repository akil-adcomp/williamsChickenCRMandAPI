/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.controller;

import cc.altius.model.FCW;
import cc.altius.model.Payroll;
import cc.altius.model.ResponseFormat;
import cc.altius.model.ValidToken;
import cc.altius.service.ApiService;
import cc.altius.service.FCWService;
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
 * @author altius
 */
@RestController
public class FCWController {

    @Autowired
    private FCWService fcwService;
    @Autowired
    private ApiService apiService;
    private @Value("#{settings['williamsChickenApiToken']}")
    String williamsChickenApiToken;

    @PostMapping(value = "/addFCW", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Add a fcw method",
    notes = "Add a fcw method",
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
                ValidToken validToken = this.apiService.validateToken(token, userId);
                if (validToken.isIsValid()) {
                    LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Valid Request"));
                    LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Valid Request"));
                    Gson gson = new Gson();
                    List<FCW> fcw = gson.fromJson(content, new TypeToken<ArrayList<FCW>>() {
                    }.getType());

                    LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("fcw " + fcw.toString()));
                    LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("fcw " + fcw.toString()));

                    int insertedCount = this.fcwService.addFCW(fcw, userId);
                    if (insertedCount > 0) {
                        LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("insertedCount :" + insertedCount));
                        LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("insertedCount :" + insertedCount));
                        responseFormat.setStatus("success");
                        return new ResponseEntity(responseFormat, HttpStatus.OK);
                    } else {
                        LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("insertedCount :" + insertedCount));
                        LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("insertedCount :" + insertedCount));
                        return new ResponseEntity("failed", HttpStatus.INTERNAL_SERVER_ERROR);
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
            responseFormat.setFailedReason("Invalid token");
            responseFormat.setFailedValue(ErrorConstants.INVALID_VERSION_TOKEN);
            return new ResponseEntity(responseFormat, HttpStatus.NOT_ACCEPTABLE);

        }
    }

    @PostMapping(value = "/getFCWReport", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get FCW Report",
    notes = "Get FCW Report",
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
            ValidToken validToken = this.apiService.validateToken(token, userId);
//            if (validToken.isIsValid()) {
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Valid Request"));
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Valid Request"));

            List<FCW> fcwList = this.fcwService.getFCWReportByDate(startDate, endDate);
            if (fcwList.isEmpty()) {
                responseFormat.setStatus("failed");
                responseFormat.setFailedReason("EmptyResultSetException");
                responseFormat.setFailedValue(ErrorConstants.EMPTY_LIST);
                return new ResponseEntity(responseFormat, HttpStatus.NOT_ACCEPTABLE);
            } else {
                LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("fcw list :" + fcwList));
                LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("fcw list :" + fcwList));
                return new ResponseEntity(fcwList, HttpStatus.OK);
            }
//            } else {
//                responseFormat.setStatus("failed");
//                responseFormat.setFailedReason("INVALID_TOKEN");
//                responseFormat.setFailedValue(ErrorConstants.INVALID_TOKEN);
//                return new ResponseEntity(responseFormat, HttpStatus.UNAUTHORIZED);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Error occured Because : \n " + e));
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Error occured Because : \n " + e));
            responseFormat.setStatus("failed");
            responseFormat.setFailedReason("Exception Occured :" + e.getClass().getSimpleName());
            return new ResponseEntity(responseFormat, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
