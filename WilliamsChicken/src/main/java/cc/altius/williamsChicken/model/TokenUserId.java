/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model;

import java.io.Serializable;

/**
 *
 * @author altius
 */
public class TokenUserId implements Serializable {

    private int userId;
    private boolean authorizedFlag;
    private Token token;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public boolean isAuthorizedFlag() {
        return authorizedFlag;
    }

    public void setAuthorizedFlag(boolean authorizedFlag) {
        this.authorizedFlag = authorizedFlag;
    }

    @Override
    public String toString() {
        return "TokenUserId{" + "userId=" + userId + ", authorizedFlag=" + authorizedFlag + ", token=" + token + '}';
    }
}
