/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.framework;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author umesh
 */
@Service
public class ApplicationLoadService {

    @Transactional
    public void fetchData() {
        ApplicationSession applicationSession = ApplicationSession.getCurrent();
    }
}
