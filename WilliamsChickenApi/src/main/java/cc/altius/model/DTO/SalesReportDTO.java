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
public class SalesReportDTO implements Serializable {

    private double netSalesLastWeek;
    private double netSalesLastYear;
    private int begningHeadCount;
    private double totalDeposit;
    private double totalPaidOuts;

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

    public int getBegningHeadCount() {
        return begningHeadCount;
    }

    public void setBegningHeadCount(int begningHeadCount) {
        this.begningHeadCount = begningHeadCount;
    }

    public double getTotalDeposit() {
        return totalDeposit;
    }

    public void setTotalDeposit(double totalDeposit) {
        this.totalDeposit = totalDeposit;
    }

    public double getTotalPaidOuts() {
        return totalPaidOuts;
    }

    public void setTotalPaidOuts(double totalPaidOuts) {
        this.totalPaidOuts = totalPaidOuts;
    }

    @Override
    public String toString() {
        return "SalesReportDTO{" + "netSalesLastWeek=" + netSalesLastWeek + ", netSalesLastYear=" + netSalesLastYear + ", begningHeadCount=" + begningHeadCount + ", totalDeposit=" + totalDeposit + ", totalPaidOuts=" + totalPaidOuts + '}';
    }

}
