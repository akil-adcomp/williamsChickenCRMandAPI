/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.controller;

import cc.altius.model.DTO.SalesReportDTO;
import cc.altius.model.Payroll;
import cc.altius.model.ResponseFormat;
import cc.altius.model.Sales;
import cc.altius.model.ValidTokenAndExpDate;
import cc.altius.service.ApiService;
import cc.altius.service.BankRegistrationService;
import cc.altius.service.FCWService;
import cc.altius.service.SalesService;
import cc.altius.utils.ErrorConstants;
import cc.altius.utils.LogUtils;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
public class SalesController {

    @Autowired
    private SalesService salesService;
    @Autowired
    private ApiService apiService;
    @Autowired
    private BankRegistrationService bankRegistrationService;
    @Autowired
    private FCWService fCWService;
    private @Value("#{settings['williamsChickenApiToken']}")
    String williamsChickenApiToken;

    @PostMapping(value = "/addSales", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Add a sales method",
            notes = "Add a sales method",
            response = Payroll.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = Boolean.class, message = "OK"),
        @ApiResponse(code = 401, response = ResponseFormat.class, message = "INVALID TOKEN"),
        @ApiResponse(code = 500, response = ResponseFormat.class, message = "Exception Occured"),})
    public ResponseEntity addSales(
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
                    Sales sales = gson.fromJson(content, Sales.class);

                    LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("sales " + sales.toString()));
                    LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("sales " + sales.toString()));

                    int insertedCount = this.salesService.addSales(sales, userId);

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
            responseFormat.setFailedReason("You are using older version of APP");
            responseFormat.setFailedValue(ErrorConstants.INVALID_VERSION_TOKEN);
            return new ResponseEntity(responseFormat, HttpStatus.NOT_ACCEPTABLE);

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
    public ResponseEntity getSalesReport(
            @ApiParam(name = "token", value = "Token for authentication", required = true)
            @RequestHeader(value = "token") String token,
            @ApiParam(name = "storeId", value = "App for Store Id", required = true)
            @RequestHeader(value = "storeId") int storeId,
            @ApiParam(name = "apptoken", value = "APP Token for authentication", required = true)
            @RequestHeader(value = "apptoken") String appToken,
            @ApiParam(name = "startDate", value = "Start Date for report", required = true)
            @RequestHeader(value = "startDate") String startDate,
            @ApiParam(name = "userId", value = "Users Id", required = true)
            @RequestHeader(value = "userId") int userId) {
        ResponseFormat responseFormat = new ResponseFormat();
        if (appToken.equals(williamsChickenApiToken)) {
            try {
                LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(" token :" + token));
                LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog(" token :" + token));
                ValidTokenAndExpDate validTokenAndExpDate = this.apiService.validateToken(token, userId);
                boolean isExitRecord = this.salesService.isExitRecord(startDate, storeId);
                if (validTokenAndExpDate.isIsValid()) {
                    LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("Valid Request"));
                    LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("Valid Request"));
                    if (isExitRecord) {
                        responseFormat.setStatus("failed");
                        responseFormat.setFailedReason("Already Exist");
                        responseFormat.setFailedValue(ErrorConstants.ALREADY_EXIT_RECORD);
                        return new ResponseEntity(responseFormat, HttpStatus.NOT_ACCEPTABLE);
                    } else {
                        // SalesReportDTO salesReportDTO = this.salesService.getSalesListReportByDate(startDate, storeId);
                        int begningHeadCount = this.salesService.getSalesLastBegningHeadCountByDate(startDate, storeId);
                        int netSalesLastWeek = this.salesService.getNetSalesLastWeekReportByDate(startDate, storeId);
                        int netSalesLastYear = this.salesService.getNetSalesLastYearReportByDate(startDate, storeId);
                        double totalDeposit = this.bankRegistrationService.getTotalDepositsByDateAndStoreId(startDate, storeId);
                        double totalPaidOuts = this.fCWService.getTotalPaidOutsByDateAndStoreId(startDate, storeId);
                        SalesReportDTO salesReportDTO = new SalesReportDTO();
                        salesReportDTO.setBegningHeadCount(begningHeadCount);
                        salesReportDTO.setNetSalesLastWeek(netSalesLastWeek);
                        salesReportDTO.setNetSalesLastYear(netSalesLastYear);
                        salesReportDTO.setTotalDeposit(totalDeposit);
                        salesReportDTO.setTotalPaidOuts(totalPaidOuts);
                        return new ResponseEntity(salesReportDTO, HttpStatus.OK);
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
