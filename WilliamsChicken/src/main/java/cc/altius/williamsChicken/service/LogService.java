/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.service;

/**
 *
 * @author manish
 */
public interface LogService {
     public void accessLog(String ip, String username, Integer userId, boolean success, String outcome);
}
