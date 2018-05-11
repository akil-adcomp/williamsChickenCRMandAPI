/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.dao.impl;

import cc.altius.dao.UserDao;
import cc.altius.model.UserDetails;
import cc.altius.utils.LogUtils;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author altius
 */
@Repository
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserDetails getUserDetails(int retailerId) {
        try {
            String selectSql = "SELECT r.`RETAILER_ID`,r.`IMAGE_ID`,c.`NAME`,a.`ADDRESS_ID` as `DEFAULT_ADDRESS_ID`,a.`ADDRESS1`,a.`ADDRESS2`,s.`STATE_NAME`,zip.`CITY_NAME` AS CITY,zip.`ZIP_CODE`,(SELECT COUNT(*) FROM cart WHERE cart.`RETAILER_ID`=?) AS `CART_COUNT`,(SELECT IFNULL(SUM(off.`DISCOUNT_VALUE`),0) val FROM `order` o LEFT JOIN offer off ON o.`OFFER_ID`=off.`OFFER_ID` LEFT JOIN order_details od ON o.`ORDER_ID`=od.`ORDER_ID` WHERE o.`RETAILER_ID`=? AND od.`SCAN_DATE` IS NOT NULL ) AS savings FROM retailer r LEFT JOIN contact c ON r.`CONTACT_ID` = c.`CONTACT_ID` LEFT JOIN address a ON r.`ADDRESS_ID` = a.`ADDRESS_ID` LEFT JOIN zip ON a.`ZIP_ID` = zip.`ZIP_ID` LEFT JOIN state s ON zip.`STATE_ID`=s.`STATE_ID` LEFT JOIN cart ON r.`RETAILER_ID`=cart.`RETAILER_ID` WHERE r.`RETAILER_ID`=? GROUP BY r.`RETAILER_ID` ";
            UserDetails userDetails = (UserDetails) this.jdbcTemplate.queryForObject(selectSql, new Object[]{retailerId,retailerId,retailerId}, new BeanPropertyRowMapper(UserDetails.class));
            return userDetails;
        } catch (Exception e) {
            LogUtils.debugLogger.debug(LogUtils.buildStringForSystemLog("\n Error in get user details.\n with Params :[retailer Id =" + retailerId + " ] .\n Because " + e.getMessage()));
            LogUtils.systemLogger.info(LogUtils.buildStringForSystemLog("\n Error in get user details.\n with Params :[retailer Id =" + retailerId + " ] .\n Because " + e.getMessage()));
            return null;
        }
    }
}
