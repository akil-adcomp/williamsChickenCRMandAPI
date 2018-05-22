/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.web.controller;

import cc.altius.utils.DateUtils;
import cc.altius.utils.POI.POICell;
import cc.altius.utils.POI.POIRow;
import cc.altius.utils.POI.POIWorkSheet;
import cc.altius.williamsChicken.exception.CouldNotBuildExcelException;
import cc.altius.williamsChicken.model.DTO.AccessLogReportDTO;
import cc.altius.williamsChicken.model.DTO.FCWReportDTO;
import cc.altius.williamsChicken.model.DTO.PayrollReportDTO;
import cc.altius.williamsChicken.model.DTO.SalesReportDTO;
import cc.altius.williamsChicken.model.FCW;
import cc.altius.williamsChicken.model.Payroll;
import cc.altius.williamsChicken.model.User;
import cc.altius.williamsChicken.service.ReportService;
import cc.altius.williamsChicken.service.UserService;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author manish
 */
@Controller
public class ReportController {

    @Autowired
    UserService userService;
    @Autowired
    ReportService reportService;

    @RequestMapping(value = "/report/reportAccessLogExcel.htm")
    public void getAccessLogExcelReport(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws CouldNotBuildExcelException {
        try {
            String startDate = ServletRequestUtils.getStringParameter(request, "startDate", DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD));
            String stopDate = ServletRequestUtils.getStringParameter(request, "stopDate", DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD));
            int userId = ServletRequestUtils.getIntParameter(request, "userId", -1);
            int success = ServletRequestUtils.getIntParameter(request, "success", -1);

            List<AccessLogReportDTO> reportList = this.reportService.getAccessLogReport(startDate, stopDate, userId, success);

            OutputStream out = response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment;filename=AccessLog-" + startDate + "_to_" + stopDate + ".xls");
            response.setContentType("application/vnd.ms-excel");
            POIWorkSheet mySheet = new POIWorkSheet(out, "Access Log report");
            mySheet.setPrintTitle(false);
            POIRow headerRow = new POIRow(POIRow.HEADER_ROW);
            headerRow.addCell("Access dt");
            headerRow.addCell("IP");
            headerRow.addCell("Username");
            headerRow.addCell("Success");
            headerRow.addCell("Outcome");

            mySheet.addRow(headerRow);

            for (AccessLogReportDTO data : reportList) {
                POIRow dataRow = new POIRow();
                dataRow.addCell(data.getAccessDate(), POICell.TYPE_DATETIME);
                dataRow.addCell(data.getIpAddress(), POICell.TYPE_TEXT);
                dataRow.addCell(data.getUsername(), POICell.TYPE_TEXT);
                dataRow.addCell((data.getSuccess() == 1 ? "Success" : "Failed"), POICell.TYPE_TEXT);
                dataRow.addCell(data.getOutcome(), POICell.TYPE_TEXT);

                mySheet.addRow(dataRow);
            }
            mySheet.writeWorkBook();
            out.close();
            out.flush();
        } catch (IOException io) {
//            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(io));
            throw new CouldNotBuildExcelException(io.getMessage());
        } catch (Exception e) {
//            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(e));
            throw new CouldNotBuildExcelException(e.getMessage());
        }
    }

    @RequestMapping(value = "/report/reportAccessLog.htm")
    public String showAccessLogReport(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String startDate = ServletRequestUtils.getStringParameter(request, "startDate", DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD));
        String stopDate = ServletRequestUtils.getStringParameter(request, "stopDate", DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD));
        int userId = ServletRequestUtils.getIntParameter(request, "userId", -1);
        int success = ServletRequestUtils.getIntParameter(request, "success", -1);
        List<AccessLogReportDTO> reportList = this.reportService.getAccessLogReport(startDate, stopDate, userId, success);

        modelMap.addAttribute("reportList", reportList);

        List<User> userList = this.userService.getUserList(false, "");
        modelMap.addAttribute("userList", userList);
        modelMap.addAttribute("startDate", startDate);
        modelMap.addAttribute("stopDate", stopDate);
        modelMap.addAttribute("userId", userId);
        modelMap.addAttribute("success", success);
        return "report/reportAccessLog";
    }

    @RequestMapping(value = "/report/reportFCW.htm")
    public String showFCWReport(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String startDate = ServletRequestUtils.getStringParameter(request, "startDate", DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD));
        String stopDate = ServletRequestUtils.getStringParameter(request, "stopDate", DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD));
        List<FCWReportDTO> fcwList = this.reportService.getFCWReport(startDate, stopDate);
        modelMap.addAttribute("fcwList", fcwList);
        modelMap.addAttribute("startDate", startDate);
        modelMap.addAttribute("stopDate", stopDate);
        return "report/reportFCW";
    }

    @RequestMapping(value = "/report/reportFCWExcel.htm")
    public void getFCWExcelReport(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws CouldNotBuildExcelException {
        try {
            String startDate = ServletRequestUtils.getStringParameter(request, "startDate", DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD));
            String stopDate = ServletRequestUtils.getStringParameter(request, "stopDate", DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD));

            List<FCWReportDTO> fcwList = this.reportService.getFCWReport(startDate, stopDate);

            OutputStream out = response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment;filename=FCW-" + startDate + "_to_" + stopDate + ".xls");
            response.setContentType("application/vnd.ms-excel");
            POIWorkSheet mySheet = new POIWorkSheet(out, "FCW  Report");
            mySheet.setPrintTitle(false);
            POIRow headerRow = new POIRow(POIRow.HEADER_ROW);
            headerRow.addCell("Date");
            headerRow.addCell("Store Name");
            headerRow.addCell("Submit Date");
            headerRow.addCell("Manager Name");
            headerRow.addCell("Daily Total");
            headerRow.addCell("# No Of Chechicken");
            headerRow.addCell("Details");

            headerRow.addCell("Amount");
            headerRow.addCell("Submit Date");
            headerRow.addCell("Daily Total");
            headerRow.addCell("Of Chicken Pur");

            mySheet.addRow(headerRow);

            for (FCWReportDTO data : fcwList) {
                POIRow dataRow = new POIRow();
                dataRow.addCell(data.getStore().getStateName(), POICell.TYPE_TEXT);
//                dataRow.addCell(data.getVendor().getVendorName(), POICell.TYPE_TEXT);
//                dataRow.addCell(data.getInvoice(), POICell.TYPE_TEXT);
//                dataRow.addCell(data.getAmount(), POICell.TYPE_TEXT);
//                dataRow.addCell(data.getSubmitDate(), POICell.TYPE_DATETIME);
//                dataRow.addCell(data.getDailyTotal(), POICell.TYPE_TEXT);
//                dataRow.addCell(data.getOfChickenPur(), POICell.TYPE_TEXT);
                mySheet.addRow(dataRow);
            }
            mySheet.writeWorkBook();
            out.close();
            out.flush();
        } catch (IOException io) {
//            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(io));
            throw new CouldNotBuildExcelException(io.getMessage());
        } catch (Exception e) {
//            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(e));
            throw new CouldNotBuildExcelException(e.getMessage());
        }
    }

    @RequestMapping(value = "/report/reportPayroll.htm")
    public String showPayrollReport(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String startDate = ServletRequestUtils.getStringParameter(request, "startDate", DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD));
        String stopDate = ServletRequestUtils.getStringParameter(request, "stopDate", DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD));

        List<PayrollReportDTO> payrollList = this.reportService.getPayrollReport(startDate, stopDate);

        modelMap.addAttribute("payrollList", payrollList);

        modelMap.addAttribute("startDate", startDate);
        modelMap.addAttribute("stopDate", stopDate);
        return "report/reportPayroll";
    }

    @RequestMapping(value = "/report/reportPayrollExcel.htm")
    public void getPayrollExcelReport(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws CouldNotBuildExcelException {
        try {
            String startDate = ServletRequestUtils.getStringParameter(request, "startDate", DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD));
            String stopDate = ServletRequestUtils.getStringParameter(request, "stopDate", DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD));

            List<PayrollReportDTO> payrollList = this.reportService.getPayrollReport(startDate, stopDate);

            OutputStream out = response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment;filename=Payroll-" + startDate + "_to_" + stopDate + ".xls");
            response.setContentType("application/vnd.ms-excel");
            POIWorkSheet mySheet = new POIWorkSheet(out, "FCW  Report");
            mySheet.setPrintTitle(false);
            POIRow headerRow = new POIRow(POIRow.HEADER_ROW);
            headerRow.addCell("Store Name");
            headerRow.addCell("Employee Name");
            headerRow.addCell("Reg Hour");
            headerRow.addCell("O/T");
            headerRow.addCell("Pay Rate");
            headerRow.addCell("Reg Pay");
            headerRow.addCell("Total Pay");
            headerRow.addCell("Start Date");
            headerRow.addCell("End Date");

            mySheet.addRow(headerRow);

            for (PayrollReportDTO data : payrollList) {
                POIRow dataRow = new POIRow();
                dataRow.addCell(data.getStore().getStateName(), POICell.TYPE_TEXT);
//                dataRow.addCell(data.getEmployee().getFirstName(), POICell.TYPE_TEXT);
//                dataRow.addCell(data.getRegHour(), POICell.TYPE_TEXT);
//                dataRow.addCell(data.getOt(), POICell.TYPE_TEXT);
//                dataRow.addCell(data.getPayRate(), POICell.TYPE_TEXT);
//                dataRow.addCell(data.getRegPay(), POICell.TYPE_TEXT);
//                dataRow.addCell(data.getTotalPay(), POICell.TYPE_TEXT);
//                dataRow.addCell(data.getStartDate(), POICell.TYPE_DATETIME);
//                dataRow.addCell(data.getEndDate(), POICell.TYPE_DATETIME);
                mySheet.addRow(dataRow);
            }
            mySheet.writeWorkBook();
            out.close();
            out.flush();
        } catch (IOException io) {
//            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(io));
            throw new CouldNotBuildExcelException(io.getMessage());
        } catch (Exception e) {
//            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog(e));
            throw new CouldNotBuildExcelException(e.getMessage());
        }
    }

    @RequestMapping(value = "/report/reportSales.htm")
    public String showSalesReport(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        System.out.println("inside sales");
        String startDate = ServletRequestUtils.getStringParameter(request, "startDate", DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD));
        String stopDate = ServletRequestUtils.getStringParameter(request, "stopDate", DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMD));
        List<SalesReportDTO> salesList = this.reportService.getSalesReport(startDate, stopDate);
        System.out.println("startDate "+startDate+" stopDate = "+stopDate+" list == "+salesList);
        modelMap.addAttribute("salesList", salesList);
        modelMap.addAttribute("startDate", startDate);
        modelMap.addAttribute("stopDate", stopDate);
        return "report/reportSales";
    }
}
