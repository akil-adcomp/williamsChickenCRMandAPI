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
public class FCW implements Serializable {

    private int fcwId;
    private Store store;
    private Vendor vendor;
    private String invoice;
    private double amount;
    private String submitDate;
    private double paidOutAmount;
    private double totalPaidOutAmount;
    private double totalAmount;
    private int ofChickenPur;
    private int totalOfChickenPur;
    private int userId;
    private Date createdDate;
    private int createdBy;
    private Date lastModifiedDate;
    private int lastModifiedBy;
    private String dummyVendor;

    public String getDummyVendor() {
        return dummyVendor;
    }

    public void setDummyVendor(String dummyVendor) {
        this.dummyVendor = dummyVendor;
    }
    
    

    public int getFcwId() {
        return fcwId;
    }

    public void setFcwId(int fcwId) {
        this.fcwId = fcwId;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public double getPaidOutAmount() {
        return paidOutAmount;
    }

    public void setPaidOutAmount(double paidOutAmount) {
        this.paidOutAmount = paidOutAmount;
    }

    public double getTotalPaidOutAmount() {
        return totalPaidOutAmount;
    }

    public void setTotalPaidOutAmount(double totalPaidOutAmount) {
        this.totalPaidOutAmount = totalPaidOutAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getOfChickenPur() {
        return ofChickenPur;
    }

    public void setOfChickenPur(int ofChickenPur) {
        this.ofChickenPur = ofChickenPur;
    }

    public int getTotalOfChickenPur() {
        return totalOfChickenPur;
    }

    public void setTotalOfChickenPur(int totalOfChickenPur) {
        this.totalOfChickenPur = totalOfChickenPur;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        return "FCW{" + "fcwId=" + fcwId + ", store=" + store + ", vendor=" + vendor + ", invoice=" + invoice + ", amount=" + amount + ", submitDate=" + submitDate + ", paidOutAmount=" + paidOutAmount + ", totalPaidOutAmount=" + totalPaidOutAmount + ", totalAmount=" + totalAmount + ", ofChickenPur=" + ofChickenPur + ", totalOfChickenPur=" + totalOfChickenPur + ", userId=" + userId + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", lastModifiedDate=" + lastModifiedDate + ", lastModifiedBy=" + lastModifiedBy + ", dummyVendor=" + dummyVendor + '}';
    }

}
