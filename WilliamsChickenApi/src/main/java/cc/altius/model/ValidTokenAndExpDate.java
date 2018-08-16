/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model;

/**
 *
 * @author altius
 */
public class ValidTokenAndExpDate {

    private boolean isValid;
    private Integer userId;

    public boolean isIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ValidToken{" + "isValid=" + isValid + ", userId=" + userId + '}';
    }
}
