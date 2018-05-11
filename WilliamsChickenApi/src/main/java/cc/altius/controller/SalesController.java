/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.controller;

import cc.altius.model.Payroll;
import cc.altius.model.ResponseFormat;
import cc.altius.model.Sales;
import cc.altius.model.ValidToken;
import cc.altius.service.ApiService;
import cc.altius.service.SalesService;
import cc.altius.utils.ErrorConstants;
import cc.altius.utils.LogUtils;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SalesController {

    @Autowired
    private SalesService salesService;
    @Autowired
    private ApiService apiService;

    @PostMapping(value = "/addSales", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Add a sales method",
    notes = "Add a sales method",
    response = Payroll.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = Boolean.class, message = "OK"),
        @ApiResponse(code = 401, response = ResponseFormat.class, message = "INVALID TOKEN"),
        @ApiResponse(code = 500, response = ResponseFormat.class, message = "Exception Occured"),})
    public ResponseEntity addPaymentMethod(
            @ApiParam(name = "token", value = "Token for authentication", required = true)
            @RequestHeader(value = "token") String token,
            @ApiParam(name = "userId", value = "Users Id", required = true)
            @RequestHeader(value = "userId") int userId,
            @ApiParam(name = "content", value = "content", required = true)
            @RequestBody String content) {
        ResponseFormat responseFormat = new ResponseFormat();
        try {
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(" token :" + token));
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog(" token :" + token));
            ValidToken validToken = this.apiService.validateToken(token, userId);
            if (validToken.isIsValid()) {
                LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Valid Request"));
                LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Valid Request"));

                Gson gson = new Gson();
                Sales sales = gson.fromJson(content, Sales.class);

                LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("sales " + sales.toString()));
                LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("payroll " + sales.toString()));

                int insertedCount = this.salesService.addSales(sales);
                if (insertedCount > 0) {
                    LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("insertedCount :" + insertedCount));
                    LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("insertedCount :" + insertedCount));
                    return new ResponseEntity("success", HttpStatus.OK);
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
    }

    @PostMapping(value = "/getSalesReport", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get Sales Report",
    notes = "Get Sales Report",
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
            if (validToken.isIsValid()) {
                LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Valid Request"));
                LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Valid Request"));

                List<Sales> salesList = this.salesService.getSalesListReportByDate(startDate, endDate);
                if (salesList.isEmpty()) {
                    responseFormat.setStatus("failed");
                    responseFormat.setFailedReason("EmptyResultSetException");
                    responseFormat.setFailedValue(ErrorConstants.EMPTY_LIST);
                    return new ResponseEntity(responseFormat, HttpStatus.NOT_ACCEPTABLE);
                } else {
                    LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("sales list :" + salesList));
                    LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("sales list :" + salesList));
                    return new ResponseEntity(salesList, HttpStatus.OK);
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
}
