package cc.altius.framework;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**Method to set the applicationContext to load bean definitions
     *
     * @param context 
     * context used to get the applicationContext from
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    /**Method to get the details of application context
     *
     * @return Returns the object of ApplicationContext, null when no object could be found
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
