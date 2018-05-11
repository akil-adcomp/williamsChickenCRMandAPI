/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.framework;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author shrutika
 */
@Service
public class ApplicationLoadService {

    @Transactional
    public void fetchData() {
        ApplicationSession applicationSession = ApplicationSession.getCurrent();
    }
}
