/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model.DTO;

import cc.altius.williamsChicken.model.BankRegisterDetails;
import cc.altius.williamsChicken.model.Store;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nikhil Pande
 */
public class BankRegisterDTO implements Serializable {

    private Date submitDate;
    private Store store;
    private double totalAmount;
    private List<BankRegisterDetails> listBankRegisterDetails;

    public BankRegisterDTO() {
        listBankRegisterDetails = new LinkedList<>();
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<BankRegisterDetails> getListBankRegisterDetails() {
        return listBankRegisterDetails;
    }

    public void setListBankRegisterDetails(List<BankRegisterDetails> listBankRegisterDetails) {
        this.listBankRegisterDetails = listBankRegisterDetails;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void addTotalAmount(double amount) {
        this.totalAmount += amount;
    }

    @Override
    public String toString() {
        return "BankRegisterDTO{" + "submitDate=" + submitDate + ", store=" + store + ", totalAmount=" + totalAmount + ", listBankRegisterDetails=" + listBankRegisterDetails + '}';
    }

}
