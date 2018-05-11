/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model.rowMapper;

import cc.altius.williamsChicken.model.Store;
import cc.altius.williamsChicken.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author altius
 */
public class StoreRowMapper implements RowMapper<Store> {
    
    @Override
    public Store mapRow(ResultSet rs, int i) throws SQLException {
        Store data = new Store();
        data.setCity(rs.getString("STORE_CITY"));
        data.setCreatedBy(rs.getInt("CREATED_DATE"));
        data.setCreatedDate(rs.getDate("CREATED_DATE"));
        data.setLastModifiedBy(rs.getInt("LAST_MODIFIED_BY"));
        data.setLastModifiedDate(rs.getDate("LAST_MODIFIED_DATE"));
        data.setStateId(rs.getInt("STORE_STATE_ID"));
        data.setStateName(rs.getString("STATE_NAME"));
        data.setStoreId(rs.getInt("STORE_ID"));
        data.setStoreName(rs.getString("STORE_NAME"));
        data.setActive(rs.getBoolean("ACTIVE"));
        
        User user = new User();
        user.setUserId(rs.getInt("CREATED_BY"));
        user.setUsername(rs.getString("USERNAME"));
        data.setUser(user);
        return data;
    }
}
