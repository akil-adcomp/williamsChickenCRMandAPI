/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.dao.impl;

import cc.altius.utils.DateUtils;
import cc.altius.williamsChicken.dao.StoreDao;
import cc.altius.williamsChicken.model.CustomUserDetails;
import cc.altius.williamsChicken.model.Store;
import cc.altius.williamsChicken.model.rowMapper.StoreRowMapper;
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
 * @author altius
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
        String sql = "SELECT s.*,u.`USER_ID`,u.`USERNAME`, st.`STATE_NAME` FROM store s"
                + " LEFT JOIN `user` u ON s.`CREATED_BY`=u.`USER_ID`"
                + " LEFT JOIN state st ON s.`STATE_ID`=st.`STATE_ID`";
        return this.jdbcTemplate.query(sql, new StoreRowMapper());
    }

    @Override
    public int addStore(Store store) {
        int curUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMDHMS);
        SimpleJdbcInsert storeInsert = new SimpleJdbcInsert(this.dataSource).withTableName("store").usingGeneratedKeyColumns("STORE_ID");
        Map<String, Object> params = new HashMap<>();

        params.put("STORE_CITY", store.getCity());
        params.put("STATE_ID", store.getStateId());
        params.put("STORE_NAME", store.getStoreName());
        params.put("CREATED_DATE", curDate);
        params.put("CREATED_BY", curUser);
        params.put("LAST_MODIFIED_DATE", curDate);
        params.put("LAST_MODIFIED_BY", curUser);
        params.put("ACTIVE", 1);

        return storeInsert.executeAndReturnKey(params).intValue();
    }

    @Override
    public Store getStoreByStoreId(int storeId) {
        String sql = "SELECT s.*,u.`USER_ID`,u.`USERNAME`, st.`STATE_NAME` FROM store s "
                + " LEFT JOIN `user` u ON s.`CREATED_BY`=u.`USER_ID`"
                + " LEFT JOIN state st ON s.`STATE_ID`= st.`STATE_ID`"
                + " WHERE s.`STORE_ID`=?";
        return this.jdbcTemplate.queryForObject(sql, new StoreRowMapper(), storeId);
    }

    @Override
    public int updateStore(Store store) {
        int updatedId = 0;
        int curUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        Date curDate = DateUtils.getCurrentDateObject(DateUtils.IST);
        Map<String, Object> params = new HashMap<>();
        try {
            String sql = "UPDATE store s"
                    + " SET "
                    + " s.`STORE_NAME`=:storeName,"
                    + " s.`STORE_CITY`=:storeCity,"
                    + " s.`STATE_ID`=:stateId,"
                    + " s.`LAST_MODIFIED_DATE`=:lastModifiedDate,"
                    + " s.`LAST_MODIFIED_BY`=:lastModifiedBy"
                    + " WHERE s.`STORE_ID`=:storeId";
            params.put("storeName", store.getStoreName());
            params.put("storeCity", store.getCity());
            params.put("stateId", store.getStateId());
            params.put("lastModifiedDate", curDate);
            params.put("lastModifiedBy", curUser);
            params.put("storeId", store.getStoreId());
            updatedId = this.namedParameterJdbcTemplate.update(sql, params);
            return updatedId;
        } catch (Exception e) {
            e.printStackTrace();
            return updatedId;
        }
    }

    @Override
    public int updateStoreActiveStatus(List<Integer> storeIds, int publishValue) {
        int curUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMDHMS);
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String sql = "UPDATE store s "
                + " SET s.`ACTIVE`=:publishValue, s.`LAST_MODIFIED_DATE`=:lastModifiedDate, s.`LAST_MODIFIED_BY`=:lastModifiedBy "
                + " WHERE s.`STORE_ID` IN (:ids)";
        try {
            parameters.addValue("publishValue", publishValue);
            parameters.addValue("ids", storeIds);
            parameters.addValue("lastModifiedDate", curDate);
            parameters.addValue("lastModifiedBy", curUser);
            return npjt.update(sql, parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return npjt.update(sql, parameters);
    }
}
