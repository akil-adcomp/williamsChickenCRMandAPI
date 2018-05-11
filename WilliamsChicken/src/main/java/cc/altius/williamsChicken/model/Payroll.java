/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author manish
 */
public class Payroll implements Serializable{

    private int payrollId;
    private Store store;
    private Employee employee;
    private int regHour;
    private int ot;
    private double payRate;
    private double regPay;
    private double otPay;
    private double totalPay;
    private Date startDate;
    private Date endDate;
    private Date createdDate;
    private int createdBy;
    private Date lastModifiedDate;
    private int lastModifiedBy;
    private User user;
  

    public int getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(int payrollId) {
        this.payrollId = payrollId;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getRegHour() {
        return regHour;
    }

    public void setRegHour(int regHour) {
        this.regHour = regHour;
    }

    public int getOt() {
        return ot;
    }

    public void setOt(int ot) {
        this.ot = ot;
    }

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    public double getRegPay() {
        return regPay;
    }

    public void setRegPay(double regPay) {
        this.regPay = regPay;
    }

    public double getOtPay() {
        return otPay;
    }

    public void setOtPay(double otPay) {
        this.otPay = otPay;
    }

    public double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(double totalPay) {
        this.totalPay = totalPay;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(int lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Payroll{" + "payrollId=" + payrollId + ", store=" + store + ", employee=" + employee + ", regHour=" + regHour + ", ot=" + ot + ", payRate=" + payRate + ", regPay=" + regPay + ", otPay=" + otPay + ", totalPay=" + totalPay + ", startDate=" + startDate + ", endDate=" + endDate + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", lastModifiedDate=" + lastModifiedDate + ", lastModifiedBy=" + lastModifiedBy + ", user=" + user + '}';
    }
}
