package es.upm.miw.betca_tpv_spring.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import es.upm.miw.betca_tpv_spring.documents.CashierClosure;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CashierLastOutputDto {

    private Boolean closed;

    @JsonInclude(Include.NON_NULL)
    private LocalDateTime closureDate;

    private BigDecimal finalCash;

    public CashierLastOutputDto() {
        // Empty for framework
    }

    public CashierLastOutputDto(CashierClosure cashierClosure) {
        this.closed = cashierClosure.isClosed();
        this.closureDate = cashierClosure.getClosureDate();
        this.finalCash = cashierClosure.getFinalCash();
    }

    public Boolean isClosed() {
        return closed;
    }


    public LocalDateTime getClosureDate() {
        return closureDate;
    }

    public BigDecimal getFinalCash() {
        return finalCash;
    }

    @Override
    public String toString() {
        return "CashierLastOutputDto{" +
                "closed=" + closed +
                ", closureDate=" + closureDate +
                ", finalCash=" + finalCash +
                '}';
    }
}
