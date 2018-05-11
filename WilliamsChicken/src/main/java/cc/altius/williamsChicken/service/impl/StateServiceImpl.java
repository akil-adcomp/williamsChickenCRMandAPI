/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.service.impl;

import cc.altius.williamsChicken.dao.StateDao;
import cc.altius.williamsChicken.model.State;
import cc.altius.williamsChicken.service.StateService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author altius
 */
@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StateDao stateDao;

    @Override
    public List<State> getStateList() {
        return this.stateDao.getStateList();
    }
}
