/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.dao;

import cc.altius.williamsChicken.model.Email;
import java.util.List;

/**
 *
 * @author altius
 */
public interface EmailerDao {

    public int addEmail(Email email);

    public void sendEmail();

    public List<Email> getEmailList();

    public void updateEmail(int status, int attempts, int emailerId, String response);

    public int updatePasswordByEmailId(String emailId, String password);

    public boolean isExitUser(String emailId);
}
