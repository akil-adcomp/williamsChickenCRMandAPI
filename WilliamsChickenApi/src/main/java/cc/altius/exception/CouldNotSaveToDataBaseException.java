/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.exception;

import org.apache.commons.lang.exception.NestableRuntimeException;

/**
 *
 * @author citius1
 */
public class CouldNotSaveToDataBaseException extends NestableRuntimeException {

    public CouldNotSaveToDataBaseException(Throwable cause) {
        super(cause);
    }

    public CouldNotSaveToDataBaseException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CouldNotSaveToDataBaseException(String msg) {
        super(msg);
    }
}
