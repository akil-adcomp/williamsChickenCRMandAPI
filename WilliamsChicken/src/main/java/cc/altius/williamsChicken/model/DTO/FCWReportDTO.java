/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model.DTO;

import cc.altius.williamsChicken.model.FCWStoreDetails;
import cc.altius.williamsChicken.model.Store;
import cc.altius.williamsChicken.model.User;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author altius
 */
public class FCWReportDTO implements Serializable {

    private Date date;
    private Store store;
    private Date submitDate;
    private User user;
    private double totalAmount;
    private int chickenTotal;
    private double totalPaidAmount;
    private List<FCWStoreDetails> storeDetails;

    public FCWReportDTO() {
        storeDetails = new LinkedList<>();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getChickenTotal() {
        return chickenTotal;
    }

    public void setChickenTotal(int chickenTotal) {
        this.chickenTotal = chickenTotal;
    }

    public List<FCWStoreDetails> getStoreDetails() {
        return storeDetails;
    }

    public void setStoreDetails(List<FCWStoreDetails> storeDetails) {
        this.storeDetails = storeDetails;
    }

    public double getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(double totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    public void addTotalPaidAmout(double paidAmount) {
        this.totalPaidAmount += paidAmount;
    }

    public void addChickenTotal(int chickenNo) {
        this.chickenTotal += chickenNo;
    }

    public void addTotalAmount(double amount) {
        this.totalAmount += amount;
    }

    @Override
    public String toString() {
        return "FCWReportDTO{" + "date=" + date + ", store=" + store + ", submitDate=" + submitDate + ", user=" + user + ", totalAmount=" + totalAmount + ", chickenTotal=" + chickenTotal + ", totalPaidAmount=" + totalPaidAmount + ", storeDetails=" + storeDetails + '}';
    }
}
