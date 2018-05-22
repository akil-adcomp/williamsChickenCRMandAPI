/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model.DTO.Mapper;

import cc.altius.model.DTO.SalesReportDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author altius
 */
public class SalesReportDTOMapper implements RowMapper<SalesReportDTO> {

    @Override
    public SalesReportDTO mapRow(ResultSet rs, int i) throws SQLException {
        SalesReportDTO data = new SalesReportDTO();
        data.setNetSalesLastWeek(rs.getDouble("salesLastWeek"));
        return data;
    }
}
