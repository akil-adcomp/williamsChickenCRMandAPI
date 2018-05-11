/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.framework;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author altius
 */
@Component
@Scope("application")
public class ApplicationSession {

    @Autowired
    private ApplicationLoadService applicationLoadService;

    /**
     * Method to get the current application session
     *
     * @return Returns the session of applicationContext from the class
     */
    public static ApplicationSession getCurrent() {
        return ApplicationContextProvider.getApplicationContext().getBean(ApplicationSession.class);
    }

    /**
     * Method to reload all the most required lists
     *
     */
    public void reloadAll() {
    }
}
