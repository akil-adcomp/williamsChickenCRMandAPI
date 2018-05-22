/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model.DTO;

import java.io.Serializable;

/**
 *
 * @author altius
 */
public class SalesReportDTO implements Serializable{
    
    private double netSalesLastWeek;
    private double netSalesLastYear;

    public double getNetSalesLastWeek() {
        return netSalesLastWeek;
    }

    public void setNetSalesLastWeek(double netSalesLastWeek) {
        this.netSalesLastWeek = netSalesLastWeek;
    }

    public double getNetSalesLastYear() {
        return netSalesLastYear;
    }

    public void setNetSalesLastYear(double netSalesLastYear) {
        this.netSalesLastYear = netSalesLastYear;
    }

    @Override
    public String toString() {
        return "SalesReportDTO{" + "netSalesLastWeek=" + netSalesLastWeek + ", netSalesLastYear=" + netSalesLastYear + '}';
    }

}
