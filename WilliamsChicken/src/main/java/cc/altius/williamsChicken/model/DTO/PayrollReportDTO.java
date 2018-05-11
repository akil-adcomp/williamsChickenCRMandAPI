/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model.DTO;

import cc.altius.williamsChicken.model.PayrollEmployeeDetails;
import cc.altius.williamsChicken.model.Store;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author altius
 */
public class PayrollReportDTO {

    private Date date;
    private Store store;
    private Date submitDate;
    private double totalRegPay;
    private double totalOTPay;
    private double dailyTotalPay;
    private List<PayrollEmployeeDetails> payrollDetails;

    public PayrollReportDTO() {
        payrollDetails = new LinkedList<>();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public double getTotalRegPay() {
        return totalRegPay;
    }

    public void setTotalRegPay(double totalRegPay) {
        this.totalRegPay = totalRegPay;
    }

    public double getTotalOTPay() {
        return totalOTPay;
    }

    public void setTotalOTPay(double totalOTPay) {
        this.totalOTPay = totalOTPay;
    }

    public double getDailyTotalPay() {
        return dailyTotalPay;
    }

    public void setDailyTotalPay(double dailyTotalPay) {
        this.dailyTotalPay = dailyTotalPay;
    }

    public List<PayrollEmployeeDetails> getPayrollDetails() {
        return payrollDetails;
    }

    public void setPayrollDetails(List<PayrollEmployeeDetails> payrollDetails) {
        this.payrollDetails = payrollDetails;
    }

    public void addOTTotal(int overTime, double paymentRate) {

        double overTimePerHr = paymentRate * 1.5;
        double overTimePay = (int) Math.round(overTime * overTimePerHr * 100) / (double) 100;
        this.totalOTPay += overTimePay;
    }

    public void addRegPayTotal(int overTime, int regHour, double paymentRate) {
        //  (int)Math.round(regularPay + overTimePay * 100)/(double)100;
        double regularPay = (int) Math.round(regHour * paymentRate * 100) / (double) 100;
        double overTimePerHr = paymentRate * 1.5;
        double overTimePay = overTime * overTimePerHr;
        double totalPayAmount = (int) Math.round(regularPay + overTimePay * 100) / (double) 100;
        this.totalRegPay += regularPay;
        this.dailyTotalPay += totalPayAmount;
    }
}
