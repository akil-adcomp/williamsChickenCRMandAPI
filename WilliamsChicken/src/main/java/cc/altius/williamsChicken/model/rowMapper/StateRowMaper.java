/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model.rowMapper;

import cc.altius.williamsChicken.model.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author altius
 */
public class StateRowMaper implements RowMapper<State> {

    @Override
    public State mapRow(ResultSet rs, int i) throws SQLException {
        State state = new State();
        state.setStateId(rs.getInt("STATE_ID"));
        state.setStateName(rs.getString("STATE_NAME"));
        return state;
    }
}
