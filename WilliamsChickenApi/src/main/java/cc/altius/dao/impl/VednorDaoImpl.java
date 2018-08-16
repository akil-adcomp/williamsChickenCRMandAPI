/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.dao.impl;

import cc.altius.dao.VendorDao;
import cc.altius.model.Vendor;
import cc.altius.model.mapper.VendorRowMapper;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author altius
 */
@Repository
public class VednorDaoImpl implements VendorDao {

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
    public List<Vendor> getVendorList() {
        String sql = "SELECT v.`VENDOR_ID`,v.`VENDOR_NAME`FROM vendor v WHERE v.`ACTIVE`=1 ORDER BY v.`VENDOR_NAME`";
        return this.jdbcTemplate.query(sql, new VendorRowMapper());
    }
}
