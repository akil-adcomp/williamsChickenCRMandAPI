/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.model.DTO;

import cc.altius.model.Sales;
import java.io.Serializable;

/**
 *
 * @author altius
 */
public class SalesSummaryDTO implements Serializable{
    private Sales sales;
    private SalesReportDTO salesReportDTO;

    public Sales getSales() {
        return sales;
    }

    public void setSales(Sales sales) {
        this.sales = sales;
    }

    public SalesReportDTO getSalesReportDTO() {
        return salesReportDTO;
    }

    public void setSalesReportDTO(SalesReportDTO salesReportDTO) {
        this.salesReportDTO = salesReportDTO;
    }

    @Override
    public String toString() {
        return "SalesSummaryDTO{" + "sales=" + sales + ", salesReportDTO=" + salesReportDTO + '}';
    }
}
