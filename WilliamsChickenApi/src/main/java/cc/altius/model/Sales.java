/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author altius
 */
public class Sales implements Serializable{

    private int salesId;
    private Store store;
    private String submitDate;
    private double totalSales;
    private double nonTaxSales;
    private double NetSales;
    private double salesTax;
    private double grossSales;
    private double acounntReceivable;
    private double totalPaidOut;
    private double officerDiscount;
    private double amountPerBird;
    private int begningHeadCount;
    private int storeTransfer;
    private int purchase;
    private int chickenUsage;
    private int birdsWasted;
    private int birdsOnHand;
    private int endingEnventory;
    private int variance;
    private Date createdDate;
    private int createdBy;
    private Date lastModifiedDate;
    private int lastModifiedBy;

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

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

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

    public double getAcounntReceivable() {
        return acounntReceivable;
    }

    public void setAcounntReceivable(double acounntReceivable) {
        this.acounntReceivable = acounntReceivable;
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

    public int getBegningHeadCount() {
        return begningHeadCount;
    }

    public void setBegningHeadCount(int begningHeadCount) {
        this.begningHeadCount = begningHeadCount;
    }

    public int getStoreTransfer() {
        return storeTransfer;
    }

    public void setStoreTransfer(int storeTransfer) {
        this.storeTransfer = storeTransfer;
    }

    public int getPurchase() {
        return purchase;
    }

    public void setPurchase(int purchase) {
        this.purchase = purchase;
    }

    public int getChickenUsage() {
        return chickenUsage;
    }

    public void setChickenUsage(int chickenUsage) {
        this.chickenUsage = chickenUsage;
    }

    public int getBirdsWasted() {
        return birdsWasted;
    }

    public void setBirdsWasted(int birdsWasted) {
        this.birdsWasted = birdsWasted;
    }

    public int getBirdsOnHand() {
        return birdsOnHand;
    }

    public void setBirdsOnHand(int birdsOnHand) {
        this.birdsOnHand = birdsOnHand;
    }

    public int getEndingEnventory() {
        return endingEnventory;
    }

    public void setEndingEnventory(int endingEnventory) {
        this.endingEnventory = endingEnventory;
    }

    public int getVariance() {
        return variance;
    }

    public void setVariance(int variance) {
        this.variance = variance;
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

    @Override
    public String toString() {
        return "Sales{" + "salesId=" + salesId + ", store=" + store + ", submitDate=" + submitDate + ", totalSales=" + totalSales + ", nonTaxSales=" + nonTaxSales + ", NetSales=" + NetSales + ", salesTax=" + salesTax + ", grossSales=" + grossSales + ", acounntReceivable=" + acounntReceivable + ", totalPaidOut=" + totalPaidOut + ", officerDiscount=" + officerDiscount + ", amountPerBird=" + amountPerBird + ", begningHeadCount=" + begningHeadCount + ", storeTransfer=" + storeTransfer + ", purchase=" + purchase + ", chickenUsage=" + chickenUsage + ", birdsWasted=" + birdsWasted + ", birdsOnHand=" + birdsOnHand + ", endingEnventory=" + endingEnventory + ", variance=" + variance + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", lastModifiedDate=" + lastModifiedDate + ", lastModifiedBy=" + lastModifiedBy + '}';
    }
}
