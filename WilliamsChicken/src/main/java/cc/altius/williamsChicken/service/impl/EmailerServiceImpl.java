/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.service.impl;

import cc.altius.utils.DateUtils;
import cc.altius.utils.PassPhrase;
import cc.altius.williamsChicken.dao.EmailerDao;
import cc.altius.williamsChicken.model.Email;
import cc.altius.williamsChicken.service.EmailerService;
import com.sun.mail.smtp.SMTPTransport;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author altius
 */
@Service
public class EmailerServiceImpl implements EmailerService {

    @Autowired
    private EmailerDao emailerDao;

    @Override
    public void buildEmailTemplate(String emailId) {
        String pass = PassPhrase.getPassword();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode(pass);
        String subject = "Your New Password for Williams Chicken";

        String emailtemplate = "<html>"
                + "<body>"
                + "<h4>Hi</h4>"
                + "<p>You have requested a new password to access your WilliamsChicken account.</p>"
                + "<p>Your new password is: " + pass + "</p>"
                + "<p>If you didn't request this, please ignore this email. If you didn't request this, please ignore this email. Your existing password will continue to be valid.</p>"
                + "</body>"
                + "</html>";

        Email email = new Email();
        email.setBody(emailtemplate);
        email.setToSend(emailId);
        email.setSubject(subject);
        int insertedId = this.emailerDao.addEmail(email);
        if (insertedId > 0) {
            this.emailerDao.updatePasswordByEmailId(emailId, hash);
        }
    }

    @Override
    public void sendEmail() {
        List<Email> emailList = this.emailerDao.getEmailList();
        final String fromMailId = "manish.v@altius.cc  ";
        final String hostName = "smtp.gmail.com";
        final String password = "manish88!";
        int status = 1, attempts = 1;
        String response = null;
        try {
            for (Email mail : emailList) {
                String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMDHMS);
                String subject = "Your New Password for Williams Chicken";
                String toAddress = mail.getToSend();
                String message = mail.getBody();
                Properties props = System.getProperties();
                props.put("mail.smtp.host", "");
                props.put("mail.smtp.auth", "true");
                Session session = Session.getInstance(props, null);
                MimeMessage msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress("williamschicken", "williamschicken"));
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress, "user"));
                msg.setSubject(subject, "UTF-8");
                Multipart mp = new MimeMultipart();
                MimeBodyPart htmlPart = new MimeBodyPart();
                htmlPart.setContent(message, "text/html");
                mp.addBodyPart(htmlPart);
                msg.setContent(mp);

                SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
                t.connect(hostName, fromMailId, password);
                t.sendMessage(msg, msg.getAllRecipients());
                t.close();
                response = "Success";
                this.emailerDao.updateEmail(status, attempts, mail.getEmailId(), response);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public boolean isExitUser(String emailId) {
        return this.emailerDao.isExitUser(emailId);
    }
}
