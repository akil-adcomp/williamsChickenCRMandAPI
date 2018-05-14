/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.dao.impl;

import cc.altius.utils.DateUtils;
import cc.altius.williamsChicken.dao.VendorDao;
import cc.altius.williamsChicken.model.CustomUserDetails;
import cc.altius.williamsChicken.model.Vendor;
import cc.altius.williamsChicken.model.rowMapper.VendorRowMapper;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author manish
 */
@Repository
public class VendorDaoImpl implements VendorDao {

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
    public int addVendor(Vendor vendor) {
        int vendorId = 0;
        int curUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMDHMS);
        Map<String, Object> params = new HashMap<>();
        SimpleJdbcInsert vendorInsert = new SimpleJdbcInsert(this.dataSource).withTableName("vendor").usingGeneratedKeyColumns("VENDOR_ID");
        try {
            params.put("VENDOR_NAME", vendor.getVendorName());
            params.put("VENDOR_CITY", vendor.getCity());
            params.put("STATE_ID", vendor.getStateId());
            params.put("VENDOR_ADDRESS", vendor.getVendorAddress());
            params.put("CREATED_DATE", curDate);
            params.put("ACTIVE", 1);
            params.put("CREATED_BY", curUser);
            params.put("CREATED_DATE", curDate);
            params.put("LAST_MODIFIED_BY", curUser);
            params.put("LAST_MODIFIED_DATE", curDate);
            vendorId = vendorInsert.executeAndReturnKey(params).intValue();
            return vendorId;
        } catch (Exception e) {
            e.printStackTrace();
            return vendorId;
        }

    }

    @Override
    public Vendor getVendorByVendorId(int vendorId) {
        String sql = "SELECT v.*,u.`USERNAME`, s.`STATE_NAME`FROM vendor v "
                + " LEFT JOIN `user` u ON v.`LAST_MODIFIED_BY`=u.`USER_ID`"
                + " LEFT JOIN state s ON v.`STATE_ID`=s.`STATE_ID`"
                + " WHERE v.`VENDOR_ID`=?";
        return this.jdbcTemplate.queryForObject(sql, new VendorRowMapper(), vendorId);
    }

    @Override
    public int updateVendor(Vendor vendor) {
        int updatedId = 0;
        int curUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        Date curDate = DateUtils.getCurrentDateObject(DateUtils.IST);
        Map<String, Object> params = new HashMap<>();
        try {
            String sql = "UPDATE vendor v"
                    + " SET "
                    + " v.`VENDOR_NAME`=:vendorName,"
                    + " v.`VENDOR_CITY`=:vendorCity,"
                    + " v.`STATE_ID`=:vendorStateId,"
                    + " v.`LAST_MODIFIED_DATE`=:lastModifiedDate,"
                    + " v.`LAST_MODIFIED_BY`=:lastModifiedBy"
                    + " WHERE v.`VENDOR_ID`=:vendorId";
            params.put("vendorName", vendor.getVendorName());
            params.put("vendorCity", vendor.getCity());
            params.put("vendorStateId", vendor.getStateId());
            params.put("lastModifiedDate", curDate);
            params.put("lastModifiedBy", curUser);
            params.put("vendorId", vendor.getVendorId());
            updatedId = this.namedParameterJdbcTemplate.update(sql, params);
            return updatedId;
        } catch (Exception e) {
            e.printStackTrace();
            return updatedId;
        }
    }

    @Override
    public List<Vendor> getVendorList() {
        String sql = "SELECT v.*,u.`USERNAME`,s.`STATE_NAME` FROM vendor v "
                + " LEFT JOIN `user` u ON v.`LAST_MODIFIED_BY`=u.`USER_ID`"
                + " LEFT JOIN state s ON v.`STATE_ID`=s.`STATE_ID`"
                + " ORDER BY v.`VENDOR_ID`";
        return this.jdbcTemplate.query(sql, new VendorRowMapper());
    }

    @Override
    public int updateVendorActiveStatus(List<Integer> vendorIds, int publishValue) {
        int curUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMDHMS);
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String sql = "UPDATE vendor v "
                + " SET v.`ACTIVE`=:publishValue, v.`LAST_MODIFIED_DATE`=:lastModifiedDate, v.`LAST_MODIFIED_BY`=:lastModifiedBy "
                + " WHERE v.`VENDOR_ID` IN (:ids)";
        try {
            parameters.addValue("publishValue", publishValue);
            parameters.addValue("ids", vendorIds);
            parameters.addValue("lastModifiedDate", curDate);
            parameters.addValue("lastModifiedBy", curUser);
            return npjt.update(sql, parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return npjt.update(sql, parameters);
    }
}
