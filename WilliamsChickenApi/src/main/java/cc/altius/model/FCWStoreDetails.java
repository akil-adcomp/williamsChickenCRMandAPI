/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model;

/**
 *
 * @author altius
 */
public class FCWStoreDetails {

    private Vendor vendor;
    private String invoiceNo;
    private Double amount;
    private int chickenNo;

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getChickenNo() {
        return chickenNo;
    }

    public void setChickenNo(int chickenNo) {
        this.chickenNo = chickenNo;
    }
}
