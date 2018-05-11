/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model.DTO;

import cc.altius.williamsChicken.model.Token;
import cc.altius.williamsChicken.model.User;
import javax.mail.Address;



/**
 *
 * @author altius
 */
public class LoginUserDetails {

    private Token token;
    private int retailerId;
    private int notificationCount;
    private int cartCount;
    private String password;
    private boolean authorizedFlag;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(int retailerId) {
        this.retailerId = retailerId;
    }

    public int getNotificationCount() {
        return notificationCount;
    }

    public void setNotificationCount(int notificationCount) {
        this.notificationCount = notificationCount;
    }

    public int getCartCount() {
        return cartCount;
    }

    public void setCartCount(int cartCount) {
        this.cartCount = cartCount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthorizedFlag() {
        return authorizedFlag;
    }

    public void setAuthorizedFlag(boolean authorizedFlag) {
        this.authorizedFlag = authorizedFlag;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginUserDetails{" + "user=" + user + ", retailerId=" + retailerId + ", notificationCount=" + notificationCount + ", cartCount=" + cartCount + ", password=" + password + ", authorizedFlag=" + authorizedFlag + ", token=" + token + '}';
    }
}
