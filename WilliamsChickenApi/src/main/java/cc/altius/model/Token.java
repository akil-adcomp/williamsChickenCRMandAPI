 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author manish
 */
public class Token implements Serializable {

    private String tokenStr;
    private Date expiryDate;
    private boolean authorizedFlag;

    public String getTokenStr() {
        return tokenStr;
    }

    public void setTokenStr(String tokenStr) {
        this.tokenStr = tokenStr;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isAuthorizedFlag() {
        return authorizedFlag;
    }

    public void setAuthorizedFlag(boolean authorizedFlag) {
        this.authorizedFlag = authorizedFlag;
    }

    @Override
    public String toString() {
        return "Token{" + "tokenStr=" + tokenStr + ", expiryDate=" + expiryDate + ", authorizedFlag=" + authorizedFlag + '}';
    }
}
