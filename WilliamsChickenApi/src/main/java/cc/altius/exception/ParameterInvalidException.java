/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.exception;

/**
 *
 * @author Altius Customer Services Pvt Ltd
 */
public class ParameterInvalidException extends Exception {

    private String mistake;

    public ParameterInvalidException() {
        super();
        this.mistake = "Invalid Parameter ";
    }

    public ParameterInvalidException(String mistake) {
        super(mistake);
        this.mistake = mistake;
    }

    public String getMistake() {
        return mistake;
    }
}
