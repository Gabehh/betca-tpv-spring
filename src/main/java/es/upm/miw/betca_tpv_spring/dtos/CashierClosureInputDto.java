package es.upm.miw.betca_tpv_spring.dtos;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CashierClosureInputDto {

    @NotNull
    private BigDecimal finalCard;

    @NotNull
    private BigDecimal finalCash;

    private String comment;

    public CashierClosureInputDto() {
        this(BigDecimal.ZERO, BigDecimal.ZERO, "");
    }

    public CashierClosureInputDto(BigDecimal finalCash, BigDecimal finalCard, String comment) {
        this.finalCash = finalCash;
        this.finalCard = finalCard;
        this.comment = comment;
    }

    public BigDecimal getFinalCash() {
        return finalCash;
    }

    public void setFinalCash(BigDecimal finalCash) {
        this.finalCash = finalCash;
    }

    public BigDecimal getFinalCard() {
        return finalCard;
    }

    public void setFinalCard(BigDecimal finalCard) {
        this.finalCard = finalCard;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CashierClosureInputDto{" +
                "finalCard=" + finalCard +
                ", finalCash=" + finalCash +
                ", comment='" + comment + '\'' +
                '}';
    }
}
