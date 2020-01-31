package es.upm.miw.betca_tpv_spring.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Document
public class CashierClosure {

    @Id
    private String id;
    private LocalDateTime openingDate;
    private BigDecimal initialCash;
    private BigDecimal salesCard;
    private BigDecimal salesCash;
    private BigDecimal usedVouchers;
    private BigDecimal deposit;
    private BigDecimal withdrawal;
    private BigDecimal lostCard;
    private BigDecimal lostCash;
    private BigDecimal finalCash;
    private String comment;
    private LocalDateTime closureDate;

    public CashierClosure(BigDecimal initialCash) {
        this.openingDate = LocalDateTime.now();
        this.initialCash = initialCash;
        this.salesCard = BigDecimal.ZERO;
        this.salesCash = BigDecimal.ZERO;
        this.usedVouchers = BigDecimal.ZERO;
        this.deposit = BigDecimal.ZERO;
        this.withdrawal = BigDecimal.ZERO;
        this.comment = "";
        this.closureDate = null;

    }

    public void card(BigDecimal card) {
        this.salesCard = this.salesCard.add(card);
    }

    public void cash(BigDecimal cash) {
        this.salesCash = this.salesCash.add(cash);
    }

    public void voucher(BigDecimal voucher) {
        this.usedVouchers = this.usedVouchers.add(voucher);
    }

    public void deposit(BigDecimal cash, String comment) {
        this.deposit = this.deposit.add(cash);
        this.comment += "Deposit (" + cash.setScale(2, RoundingMode.HALF_UP).toString() + "): "
                + comment + ". ";
    }

    public void withdrawal(BigDecimal cash, String comment) {
        this.withdrawal = this.withdrawal.add(cash);
        this.comment += "Withdrawal (" + cash.setScale(2, RoundingMode.HALF_UP).toString() + "): "
                + comment + ". ";
    }

    public void close(BigDecimal finalCard, BigDecimal finalCash, String comment) {
        this.lostCard = this.salesCard.subtract(finalCard);
        this.lostCash = this.initialCash.add(this.salesCash).add(this.deposit)
                .subtract(this.withdrawal).subtract(finalCash);
        this.finalCash = finalCash;
        this.comment += comment + ". ";
        this.closureDate = LocalDateTime.now();
    }

    public boolean isClosed() {
        return getClosureDate() != null;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    public BigDecimal getInitialCash() {
        return initialCash;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public BigDecimal getWithdrawal() {
        return withdrawal;
    }

    public BigDecimal getSalesCard() {
        return salesCard;
    }

    public BigDecimal getSalesCash() {
        return salesCash;
    }

    public BigDecimal getUsedVouchers() {
        return usedVouchers;
    }

    public BigDecimal getLostCard() {
        return lostCard;
    }

    public BigDecimal getLostCash() {
        return lostCash;
    }

    public BigDecimal getFinalCash() {
        return finalCash;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getClosureDate() {
        return closureDate;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && id.equals(((CashierClosure) obj).id);
    }

    @Override
    public String toString() {
        return "CashierClosure{" +
                "id='" + id + '\'' +
                ", openingDate=" + openingDate +
                ", initialCash=" + initialCash +
                ", salesCard=" + salesCard +
                ", salesCash=" + salesCash +
                ", usedVouchers=" + usedVouchers +
                ", deposit=" + deposit +
                ", withdrawal=" + withdrawal +
                ", lostCard=" + lostCard +
                ", lostCash=" + lostCash +
                ", comment='" + comment + '\'' +
                ", closureDate=" + closureDate +
                '}';
    }
}
