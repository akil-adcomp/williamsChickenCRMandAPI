/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.dao.impl;

import cc.altius.utils.DateUtils;
import cc.altius.williamsChicken.dao.ManagerDao;
import cc.altius.williamsChicken.model.CustomUserDetails;
import cc.altius.williamsChicken.model.Manager;
import cc.altius.williamsChicken.model.rowMapper.ManagerRowMapper;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author manish
 */
@Repository
public class ManagerDaoImpl implements ManagerDao {

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
    public List<Manager> getManagerList() {
        String sql = "SELECT m.*,u.`USERNAME`,s.`STORE_NAME` FROM manager m "
                + " LEFT JOIN `user` u ON m.`CREATED_BY`=u.`USER_ID`"
                + " LEFT JOIN store_manager_mapping smm ON m.`MANAGER_ID`=smm.`MANAGER_ID`"
                + " LEFT JOIN store s ON smm.`STORE_ID`=s.`STORE_ID`"
                + " ORDER BY m.`MANAGER_ID`";
        return this.jdbcTemplate.query(sql, new ManagerRowMapper());
    }

    /**
     *
     * @param manager
     * @return
     */
    @Override
    @Transactional
    public int addManager(Manager manager) {
        int curUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMDHMS);
        SimpleJdbcInsert userInsert = new SimpleJdbcInsert(this.dataSource).withTableName("user").usingGeneratedKeyColumns("USER_ID");
        Map<String, Object> params = new HashMap<>();

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashPass = encoder.encode(manager.getPassword());
        params.put("USERNAME", manager.getFirstName() + " " + manager.getLastName());
        params.put("PASSWORD", hashPass);
        params.put("ACTIVE", 1);
        params.put("EXPIRED", false);
        params.put("EXPIRES_ON", DateUtils.getOffsetFromCurrentDateObject(DateUtils.IST, -1));
        params.put("FAILED_ATTEMPTS", 0);
        params.put("OUTSIDE_ACCESS", 1);
        params.put("CREATED_BY", curUser);
        params.put("CREATED_DATE", curDate);
        params.put("LAST_MODIFIED_BY", curUser);
        params.put("LAST_MODIFIED_DATE", curDate);
        params.put("EMAIL_ID", manager.getEmailId());
        params.put("PHONE_NO", manager.getPhoneNo());

        int userId = userInsert.executeAndReturnKey(params).intValue();
        params.clear();

        String sqlString = "INSERT INTO user_role (USER_ID, ROLE_ID) VALUES(?, ?)";

        this.jdbcTemplate.update(sqlString, userId, "ROLE_MANAGER");

        SimpleJdbcInsert managerInsert = new SimpleJdbcInsert(this.dataSource).withTableName("manager").usingGeneratedKeyColumns("MANAGER_ID");
        params.put("FIRST_NAME", manager.getFirstName());
        params.put("LAST_NAME", manager.getLastName());
        params.put("EMAIL_ID", manager.getEmailId());
        params.put("PHONE_NUMBER", manager.getPhoneNo());
        params.put("USER_ID", userId);
        params.put("ACTIVE", 1);
        params.put("CREATED_BY", curUser);
        params.put("CREATED_DATE", curDate);
        params.put("LAST_MODIFIED_BY", curUser);
        params.put("LAST_MODIFIED_DATE", curDate);

        int managerId = managerInsert.executeAndReturnKey(params).intValue();
        params.clear();

        SimpleJdbcInsert storeManagerMappingInsert = new SimpleJdbcInsert(this.dataSource).withTableName("store_manager_mapping").usingGeneratedKeyColumns("STORE_MANAGER_ID");
        params.put("STORE_ID", manager.getStoreId());
        params.put("MANAGER_ID", managerId);
        int storeManagerMapId = storeManagerMappingInsert.executeAndReturnKey(params).intValue();

        return storeManagerMapId;
    }

    @Override
    public Manager getManagerByManagerId(int managerId) {
        String sql = "SELECT m.*,u.`USERNAME` ,sm.`STORE_ID`,s.`STORE_NAME` FROM manager m "
                + " LEFT JOIN `user` u ON m.`CREATED_BY`=u.`USER_ID`"
                + " LEFT JOIN store_manager_mapping sm ON m.`MANAGER_ID`=sm.`MANAGER_ID`"
                + "  LEFT JOIN store s ON sm.`STORE_ID`=s.`STORE_ID`"
                + " WHERE m.`MANAGER_ID`=?";
        return this.jdbcTemplate.queryForObject(sql, new ManagerRowMapper(), managerId);
    }

    @Override
    public int updateManager(Manager manager) {
        int updatedId = 0;
        int curUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        Date curDate = DateUtils.getCurrentDateObject(DateUtils.IST);
        Map<String, Object> params = new HashMap<>();
        try {
            String sql = "UPDATE manager m"
                    + " SET "
                    + " m.`FIRST_NAME`=:firstName,"
                    + " m.`LAST_NAME`=:lastName,"
                    + " m.`EMAIL_ID`=:emailId,"
                    + " m.`PHONE_NUMBER`=:phoneNo,"
                    + " m.`LAST_MODIFIED_DATE`=:lastModifiedDate,"
                    + " m.`LAST_MODIFIED_BY`=:lastModifiedBy"
                    + " WHERE m.`MANAGER_ID`=:managerId";
            params.put("firstName", manager.getFirstName());
            params.put("lastName", manager.getLastName());
            params.put("emailId", manager.getEmailId());
            params.put("phoneNo", manager.getPhoneNo());
            params.put("lastModifiedDate", curDate);
            params.put("lastModifiedBy", curUser);
            params.put("managerId", manager.getManagerId());
            updatedId = this.namedParameterJdbcTemplate.update(sql, params);
            return updatedId;
        } catch (Exception e) {
            e.printStackTrace();
            return updatedId;
        }
    }

    @Override
    public int updateManagerActiveStatus(List<Integer> managerIds, int publishValue) {
        int curUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        String curDate = DateUtils.getCurrentDateString(DateUtils.IST, DateUtils.YMDHMS);
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String sql = "UPDATE manager m "
                + " SET m.`ACTIVE`=:publishValue, m.`LAST_MODIFIED_DATE`=:lastModifiedDate, m.`LAST_MODIFIED_BY`=:lastModifiedBy "
                + " WHERE m.`MANAGER_ID` IN (:ids)";
        try {
            parameters.addValue("publishValue", publishValue);
            parameters.addValue("ids", managerIds);
            parameters.addValue("lastModifiedDate", curDate);
            parameters.addValue("lastModifiedBy", curUser);
            return npjt.update(sql, parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return npjt.update(sql, parameters);
    }
}
