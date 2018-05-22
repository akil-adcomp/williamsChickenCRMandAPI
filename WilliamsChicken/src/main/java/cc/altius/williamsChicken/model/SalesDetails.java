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
    private double officerDiscount;
    private double amountPerBird;

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

    public void setAcounntReceivable(double accountReceivable) {
        this.accountReceivable = accountReceivable;
    }

    public double getTotalPaidOut() {
        return totalPaidOut;
    }

    public void setTotalPaidOut(double totalPaidOut) {
        this.totalPaidOut = totalPaidOut;
    }

    public double getOfficerDiscount() {
        return officerDiscount;
    }

    public void setOfficerDiscount(double officerDiscount) {
        this.officerDiscount = officerDiscount;
    }

    public double getAmountPerBird() {
        return amountPerBird;
    }

    public void setAmountPerBird(double amountPerBird) {
        this.amountPerBird = amountPerBird;
    }

    @Override
    public String toString() {
        return "SalesDetails{" + "totalSales=" + totalSales + ", nonTaxSales=" + nonTaxSales + ", NetSales=" + NetSales + ", salesTax=" + salesTax + ", grossSales=" + grossSales + ", accountReceivable=" + accountReceivable + ", totalPaidOut=" + totalPaidOut + ", officerDiscount=" + officerDiscount + ", amountPerBird=" + amountPerBird + '}';
    }
}
