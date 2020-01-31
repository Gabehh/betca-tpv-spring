package es.upm.miw.betca_tpv_spring.dtos;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CashierStateOutputDto {

    private BigDecimal salesTotal;

    private BigDecimal totalVoucher;

    private BigDecimal totalCard;

    private BigDecimal totalCash;

    public CashierStateOutputDto() {
        // Empty for framework
    }

    public CashierStateOutputDto(BigDecimal salesTotal, BigDecimal totalCard, BigDecimal totalCash, BigDecimal totalVoucher) {
        this.salesTotal = salesTotal;
        this.totalCard = totalCard;
        this.totalCash = totalCash;
        this.totalVoucher = totalVoucher;

    }

    public BigDecimal getTotalCard() {
        return totalCard.setScale(2, RoundingMode.HALF_UP);
    }

    public void setTotalCard(BigDecimal totalCard) {
        this.totalCard = totalCard;
    }

    public BigDecimal getTotalCash() {
        return totalCash.setScale(2, RoundingMode.HALF_UP);
    }

    public void setTotalCash(BigDecimal totalCash) {
        this.totalCash = totalCash;
    }

    public BigDecimal getTotalVoucher() {
        return totalVoucher.setScale(2, RoundingMode.HALF_UP);
    }

    public void setTotalVoucher(BigDecimal totalVoucher) {
        this.totalVoucher = totalVoucher;
    }

    public BigDecimal getSalesTotal() {
        return salesTotal.setScale(2, RoundingMode.HALF_UP);
    }

    public void setSalesTotal(BigDecimal salesTotal) {
        this.salesTotal = salesTotal;
    }

    @Override
    public String toString() {
        return "CashierStateOutputDto{" +
                "salesTotal=" + salesTotal +
                ", totalVoucher=" + totalVoucher +
                ", totalCard=" + totalCard +
                ", totalCash=" + totalCash +
                '}';
    }
}
