/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model.DTO;

import cc.altius.williamsChicken.model.SalesDetails;
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
public class SalesReportDTO implements Serializable {

    private Date date;
    private Store store;
    private Date submitDate;
    private int begningHeadCount;
    private int storeTransfer;
    private int purchase;
    private int chickenUsage;
    private int birdsWasted;
    private int birdsOnHand;
    private int endingEnventory;
    private int variance;
    private User user;
    private List<SalesDetails> salesDetails;
    private double refounds;

    public SalesReportDTO() {
        salesDetails = new LinkedList<>();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<SalesDetails> getSalesDetails() {
        return salesDetails;
    }

    public void setSalesDetails(List<SalesDetails> salesDetails) {
        this.salesDetails = salesDetails;
    }

    public double getRefounds() {
        return refounds;
    }

    public void setRefounds(double refounds) {
        this.refounds = refounds;
    }

    @Override
    public String toString() {
        return "SalesReportDTO{" + "date=" + date + ", store=" + store + ", submitDate=" + submitDate + ", begningHeadCount=" + begningHeadCount + ", storeTransfer=" + storeTransfer + ", purchase=" + purchase + ", chickenUsage=" + chickenUsage + ", birdsWasted=" + birdsWasted + ", birdsOnHand=" + birdsOnHand + ", endingEnventory=" + endingEnventory + ", variance=" + variance + ", user=" + user + ", salesDetails=" + salesDetails + ", refounds=" + refounds + '}';
    }
}
