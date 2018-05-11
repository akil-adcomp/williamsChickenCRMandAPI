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
public class TokenPassword implements Serializable {

    private String password;
    private TokenUserId tokenUserId;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TokenUserId getTokenUserId() {
        return tokenUserId;
    }

    public void setTokenUserId(TokenUserId tokenUserId) {
        this.tokenUserId = tokenUserId;
    }

    @Override
    public String toString() {
        return "TokenPassword{" + "password=" + password + ", tokenUserId=" + tokenUserId + '}';
    }
}
