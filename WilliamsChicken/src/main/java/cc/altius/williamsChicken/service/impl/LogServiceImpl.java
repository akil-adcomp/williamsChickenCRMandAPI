/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.service.impl;


import cc.altius.williamsChicken.dao.LogDao;
import cc.altius.williamsChicken.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gaurao
 */
@Service("logService")
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public void accessLog(String ip, String username, Integer userId, boolean success, String outcome) {
        try {
            this.logDao.accessLog(ip, username, userId, success, outcome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Method used to get the access log
     *
     * @param ip ip used to get ip address
     * @param username username used to get access log
     * @param userId
     * @param success success used to get the status succeed/failed
     * @param outcome outcome used to get the reason behind success/failure
     */
}
