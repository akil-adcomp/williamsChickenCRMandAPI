/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.exception;

/**
 *
 * @author Altius Customer Services Pvt Ltd
 */
public class DataStoreException extends Exception {

    private String mistake;

    public DataStoreException() {
        super();
        this.mistake = "Unkown error occurred";
    }

    public DataStoreException(String mistake) {
        super(mistake);
        this.mistake = mistake;
    }

    public String getMistake() {
        return mistake;
    }
}
