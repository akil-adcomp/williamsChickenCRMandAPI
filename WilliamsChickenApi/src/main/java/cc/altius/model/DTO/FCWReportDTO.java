/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model.DTO;

import cc.altius.model.FCWStoreDetails;
import cc.altius.model.Store;
import cc.altius.model.User;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author altius
 */
public class FCWReportDTO {

    private Date date;
    private Store store;
    private Date submitDate;
    private User user;
    private double dailyTotal;
    private int chickenTotal;
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

    public double getDailyTotal() {
        return dailyTotal;
    }

    public void setDailyTotal(double dailyTotal) {
        this.dailyTotal = dailyTotal;
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

    public void addChickenTotal(int chickenNo) {
        this.chickenTotal += chickenNo;
    }
    
    public void addDailyTotal(double amount) {
        this.dailyTotal += amount;
    }
}
