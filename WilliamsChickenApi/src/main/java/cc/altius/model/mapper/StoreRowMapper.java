/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model.mapper;

import cc.altius.model.Store;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author manish
 */
public class StoreRowMapper implements RowMapper<Store> {

    @Override
    public Store mapRow(ResultSet rs, int i) throws SQLException {
        Store data = new Store();
        data.setStoreId(rs.getInt("STORE_ID"));
        data.setStoreName(rs.getString("STORE_NAME"));
        return data;
    }
}
