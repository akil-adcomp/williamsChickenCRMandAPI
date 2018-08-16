/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model;

import java.io.Serializable;

/**
 *
 * @author Nikhil Pande
 */
public class PaymentMode implements Serializable {

    private int payemntModeId;
    private String payemntModeDesc;

    public int getPayemntModeId() {
        return payemntModeId;
    }

    public void setPayemntModeId(int payemntModeId) {
        this.payemntModeId = payemntModeId;
    }

    public String getPayemntModeDesc() {
        return payemntModeDesc;
    }

    public void setPayemntModeDesc(String payemntModeDesc) {
        this.payemntModeDesc = payemntModeDesc;
    }

    @Override
    public String toString() {
        return "PaymentMode{" + "payemntModeId=" + payemntModeId + ", payemntModeDesc=" + payemntModeDesc + '}';
    }

}
