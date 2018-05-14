/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model;

import java.io.Serializable;

/**
 *
 * @author altius
 */
public class PayrollEmployeeDetails implements Serializable {

    private Employee employee;
    private int ot;
    private int regHour;
    private double payRate;
    private double totalRegPay;
    private double totalOTPay;
    private double dailyTotalPay;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getOt() {
        return ot;
    }

    public void setOt(int ot) {
        this.ot = ot;
    }

    public int getRegHour() {
        return regHour;
    }

    public void setRegHour(int regHour) {
        this.regHour = regHour;
    }

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
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

    @Override
    public String toString() {
        return "PayrollEmployeeDetails{" + "employee=" + employee + ", ot=" + ot + ", regHour=" + regHour + ", payRate=" + payRate + ", totalRegPay=" + totalRegPay + ", totalOTPay=" + totalOTPay + ", dailyTotalPay=" + dailyTotalPay + '}';
    }
}
