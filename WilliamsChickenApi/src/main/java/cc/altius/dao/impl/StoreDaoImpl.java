/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.dao.impl;

import cc.altius.dao.StoreDao;
import cc.altius.model.Store;
import cc.altius.model.mapper.StoreRowMapper;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author manish
 */
@Repository
public class StoreDaoImpl implements StoreDao {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Store> getStoreList() {
        String sql = "SELECT s.`STORE_ID`,s.`STORE_NAME` FROM store s WHERE s.`ACTIVE`=1;";
        return this.jdbcTemplate.query(sql, new StoreRowMapper());
    }

    @Override
    public Store getStoreIdByUserId(int userId) {
        String sql = "SELECT s.`STORE_ID`,s.`STORE_NAME` FROM `user` u "
                + " LEFT JOIN manager m ON u.`USER_ID`=m.`USER_ID`"
                + " LEFT JOIN store_manager_mapping sm ON m.`MANAGER_ID`=sm.`MANAGER_ID`"
                + " LEFT JOIN store s ON sm.`STORE_ID`=s.`STORE_ID`"
                + " WHERE u.`USER_ID`=?";
        return this.jdbcTemplate.queryForObject(sql, new StoreRowMapper(), userId);
    }
}
