/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.service;


/**
 *
 * @author altius
 */
public interface EmailerService {

    public void buildEmailTemplate(String emailId);

    public void sendEmail();
    
    public boolean isExitUser(String emailId);
    
}
