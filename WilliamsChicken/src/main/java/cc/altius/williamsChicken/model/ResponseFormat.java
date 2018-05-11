/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model;

/**
 *
 * @author altius
 */
public class ResponseFormat {

    private String status;
    private String failedReason;
    private int failedValue;
    private String parameter;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFailedReason() {
        return failedReason;
    }

    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public int getFailedValue() {
        return failedValue;
    }

    public void setFailedValue(int failedValue) {
        this.failedValue = failedValue;
    }

    @Override
    public String toString() {
        return "ResponseFormat{" + "status=" + status + ", failedReason=" + failedReason + ", failedValue=" + failedValue + ", parameter=" + parameter + '}';
    }
}
