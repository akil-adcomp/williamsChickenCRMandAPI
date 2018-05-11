/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.exception;

/**
 *
 * @author Altius Customer Services Pvt Ltd
 */
public class AlreadyReceivedLeadException extends Exception {

    private String mistake;

    public AlreadyReceivedLeadException() {
        super();
        this.mistake = "Already received this reservation";
    }

    public AlreadyReceivedLeadException(String mistake) {
        super(mistake);
        this.mistake = mistake;
    }

    public String getMistake() {
        return mistake;
    }
}
