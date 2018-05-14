/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.model.DTO.mapper;

import cc.altius.williamsChicken.model.DTO.PayrollReportDTO;
import cc.altius.williamsChicken.model.Employee;
import cc.altius.williamsChicken.model.PayrollEmployeeDetails;
import cc.altius.williamsChicken.model.Store;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 *
 * @author altius
 */
public class PayrollResultSetExtractor implements ResultSetExtractor<List<PayrollReportDTO>> {

    @Override
    public List<PayrollReportDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
        int oldStoreId = 0;
        int newStoreId = -1;
        String newCreated = null;
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String oldCreatedDate = sdf.format(d);
        List<PayrollReportDTO> payrollReportList = new LinkedList<>();
        PayrollReportDTO payroll = new PayrollReportDTO();
        boolean isFirst = true;
        while (rs.next()) {
            newCreated = sdf.format(rs.getDate("date"));
            newStoreId = rs.getInt("STORE_ID");
            if (oldStoreId != newStoreId || !oldCreatedDate.equals(newCreated)) {
                if (!isFirst) {
                    payrollReportList.add(payroll);
                }
                isFirst = false;
                payroll = new PayrollReportDTO();
                payroll.setDate(rs.getDate("date"));

                Store s = new Store();
                s.setStoreId(rs.getInt("STORE_ID"));
                s.setStoreName(rs.getString("STORE_NAME"));
                payroll.setStore(s);

                payroll.setSubmitDate(rs.getTimestamp("SUBMIT_DATE"));

            }
            PayrollEmployeeDetails payrollDetails = new PayrollEmployeeDetails();
            payrollDetails.setOt(rs.getInt("OT"));
            payrollDetails.setRegHour(rs.getInt("REG_HOUR"));
            payrollDetails.setPayRate(rs.getDouble("PAY_RATE"));

            Employee e = new Employee();
            e.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
            e.setFirstName(rs.getString("name"));
            payrollDetails.setEmployee(e);
            payroll.getPayrollDetails().add(payrollDetails);
            double regularPay = (payrollDetails.getRegHour() * payrollDetails.getPayRate());
            double overTimePerHr = (payrollDetails.getPayRate() * 1.5);
            double overTimePay = (payrollDetails.getOt() * overTimePerHr);
            double totalPayAmount = (regularPay + overTimePay);
            payrollDetails.setDailyTotalPay(totalPayAmount);
            payrollDetails.setTotalOTPay(overTimePay);
            payrollDetails.setTotalRegPay(regularPay);
            oldStoreId = newStoreId;
            oldCreatedDate = newCreated;
        }
        if (!isFirst) {
            payrollReportList.add(payroll);
        }
        return payrollReportList;
    }
}
