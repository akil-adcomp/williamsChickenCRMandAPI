/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author altius
 */
public class Email implements Serializable {

    private int emailId;
    private String toSend;
    private String ccToSend;
    private String bccToSend;
    private String subject;
    private String body;
    private Date createdDate;
    private Date lastModifiedDate;
    private Date toSendDate;
    private int status;
    private int attempts;
    private String response;

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }

    public String getToSend() {
        return toSend;
    }

    public void setToSend(String toSend) {
        this.toSend = toSend;
    }

    public String getCcToSend() {
        return ccToSend;
    }

    public void setCcToSend(String ccToSend) {
        this.ccToSend = ccToSend;
    }

    public String getBccToSend() {
        return bccToSend;
    }

    public void setBccToSend(String bccToSend) {
        this.bccToSend = bccToSend;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Date getToSendDate() {
        return toSendDate;
    }

    public void setToSendDate(Date toSendDate) {
        this.toSendDate = toSendDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Email{" + "emailId=" + emailId + ", toSend=" + toSend + ", ccToSend=" + ccToSend + ", bccToSend=" + bccToSend + ", subject=" + subject + ", body=" + body + ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate + ", toSendDate=" + toSendDate + ", status=" + status + ", attempts=" + attempts + ", response=" + response + '}';
    }
}
