/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model;

import java.io.Serializable;

/**
 *
 * @author altius
 */
public class UserDetails implements Serializable {

    private Integer retailerId;
    private String imageId;
    private String name;
    private Integer defaultAddressId;
    private String address1;
    private String address2;
    private String stateName;
    private String city;
    private String zipCode;
    private Integer savings;
    private Integer cartCount;

    public Integer getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(Integer retailerId) {
        this.retailerId = retailerId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getSavings() {
        return savings;
    }

    public void setSavings(Integer savings) {
        this.savings = savings;
    }

    public Integer getDefaultAddressId() {
        return defaultAddressId;
    }

    public void setDefaultAddressId(Integer defaultAddressId) {
        this.defaultAddressId = defaultAddressId;
    }

    public Integer getCartCount() {
        return cartCount;
    }

    public void setCartCount(Integer cartCount) {
        this.cartCount = cartCount;
    }

    @Override
    public String toString() {
        return "UserDetails{" + "retailerId=" + retailerId + ", imageId=" + imageId + ", name=" + name + ", defaultAddressId=" + defaultAddressId + ", address1=" + address1 + ", address2=" + address2 + ", stateName=" + stateName + ", city=" + city + ", zipCode=" + zipCode + ", savings=" + savings + ", cartCount=" + cartCount + '}';
    }
}
