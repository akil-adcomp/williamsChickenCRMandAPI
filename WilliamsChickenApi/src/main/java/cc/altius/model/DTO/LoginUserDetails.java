/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model.DTO;

import cc.altius.model.Token;
import cc.altius.model.User;
import java.io.Serializable;

/**
 *
 * @author manish
 */
public class LoginUserDetails implements Serializable {

    private Token token;
    private User user;
    private int storeId;
    private String storeName;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public String toString() {
        return "LoginUserDetails{" + "token=" + token + ", user=" + user + ", storeId=" + storeId + ", storeName=" + storeName + '}';
    }
}
