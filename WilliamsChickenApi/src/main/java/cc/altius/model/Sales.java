/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model;

import java.util.Date;

/**
 *
 * @author altius
 */
public class Sales {

    private int salesId;
    private Store store;
    private Date submitDate;
    private int totalSales;
    private int nonTaxSales;
    private double netSales;
    private double salesTax;
    private double grossSales;
    private int netselsLastWeek;
    private int amountReceivable;
    private int totalPaidOuts;
    private double netSalesLastYear;
    private double custCount;
    private Date createdDate;
    private int createdBy;
    private Date lastModifiedDate;
    private int lastModifiedBy;
    private User user;

    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
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

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public int getNonTaxSales() {
        return nonTaxSales;
    }

    public void setNonTaxSales(int nonTaxSales) {
        this.nonTaxSales = nonTaxSales;
    }

    public double getNetSales() {
        return netSales;
    }

    public void setNetSales(double netSales) {
        this.netSales = netSales;
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

    public int getNetselsLastWeek() {
        return netselsLastWeek;
    }

    public void setNetselsLastWeek(int netselsLastWeek) {
        this.netselsLastWeek = netselsLastWeek;
    }

    public int getAmountReceivable() {
        return amountReceivable;
    }

    public void setAmountReceivable(int amountReceivable) {
        this.amountReceivable = amountReceivable;
    }

    public int getTotalPaidOuts() {
        return totalPaidOuts;
    }

    public void setTotalPaidOuts(int totalPaidOuts) {
        this.totalPaidOuts = totalPaidOuts;
    }

    public double getNetSalesLastYear() {
        return netSalesLastYear;
    }

    public void setNetSalesLastYear(double netSalesLastYear) {
        this.netSalesLastYear = netSalesLastYear;
    }

    public double getCustCount() {
        return custCount;
    }

    public void setCustCount(double custCount) {
        this.custCount = custCount;
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
        return "Sales{" + "salesId=" + salesId + ", store=" + store + ", submitDate=" + submitDate + ", totalSales=" + totalSales + ", nonTaxSales=" + nonTaxSales + ", netSales=" + netSales + ", salesTax=" + salesTax + ", grossSales=" + grossSales + ", netselsLastWeek=" + netselsLastWeek + ", amountReceivable=" + amountReceivable + ", totalPaidOuts=" + totalPaidOuts + ", netSalesLastYear=" + netSalesLastYear + ", custCount=" + custCount + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", lastModifiedDate=" + lastModifiedDate + ", lastModifiedBy=" + lastModifiedBy + ", user=" + user + '}';
    }
}
