/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.dao;

import cc.altius.model.UserDetails;

/**
 *
 * @author shrutika
 */
public interface UserDao {

    public UserDetails getUserDetails(int retailerId);
}
