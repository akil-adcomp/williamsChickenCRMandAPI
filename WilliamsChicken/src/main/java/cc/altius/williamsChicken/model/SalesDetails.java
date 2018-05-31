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
public class SalesDetails implements Serializable {

    private double totalSales;
    private double nonTaxSales;
    private double NetSales;
    private double salesTax;
    private double grossSales;
    private double accountReceivable;
    private double totalPaidOut;
    private double uberAccount;
    private double amountPerBird;
    private int customerCount;
    private double totalDeposit;
    private double doorDashAccount;
    private double cash;
    private double checkAverage;
    private double refounds;

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public double getNonTaxSales() {
        return nonTaxSales;
    }

    public void setNonTaxSales(double nonTaxSales) {
        this.nonTaxSales = nonTaxSales;
    }

    public double getNetSales() {
        return NetSales;
    }

    public void setNetSales(double NetSales) {
        this.NetSales = NetSales;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public double getGrossSales() {
        return grossSales;
    }

    public void setGrossSales(double grossSales) {
        this.grossSales = grossSales;
    }

    public double getAccountReceivable() {
        return accountReceivable;
    }

    public void setAccountReceivable(double accountReceivable) {
        this.accountReceivable = accountReceivable;
    }

    public double getTotalPaidOut() {
        return totalPaidOut;
    }

    public void setTotalPaidOut(double totalPaidOut) {
        this.totalPaidOut = totalPaidOut;
    }

    public double getUberAccount() {
        return uberAccount;
    }

    public void setUberAccount(double uberAccount) {
        this.uberAccount = uberAccount;
    }

    public double getAmountPerBird() {
        return amountPerBird;
    }

    public void setAmountPerBird(double amountPerBird) {
        this.amountPerBird = amountPerBird;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    public double getTotalDeposit() {
        return totalDeposit;
    }

    public void setTotalDeposit(double totalDeposit) {
        this.totalDeposit = totalDeposit;
    }

    public double getDoorDashAccount() {
        return doorDashAccount;
    }

    public void setDoorDashAccount(double doorDashAccount) {
        this.doorDashAccount = doorDashAccount;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getCheckAverage() {
        return checkAverage;
    }

    public void setCheckAverage(double checkAverage) {
        this.checkAverage = checkAverage;
    }

    public double getRefounds() {
        return refounds;
    }

    public void setRefounds(double refounds) {
        this.refounds = refounds;
    }

    @Override
    public String toString() {
        return "SalesDetails{" + "totalSales=" + totalSales + ", nonTaxSales=" + nonTaxSales + ", NetSales=" + NetSales + ", salesTax=" + salesTax + ", grossSales=" + grossSales + ", accountReceivable=" + accountReceivable + ", totalPaidOut=" + totalPaidOut + ", uberAccount=" + uberAccount + ", amountPerBird=" + amountPerBird + ", customerCount=" + customerCount + ", totalDeposit=" + totalDeposit + ", doorDashAccount=" + doorDashAccount + ", cash=" + cash + ", checkAverage=" + checkAverage + ", refounds=" + refounds + '}';
    }
}
