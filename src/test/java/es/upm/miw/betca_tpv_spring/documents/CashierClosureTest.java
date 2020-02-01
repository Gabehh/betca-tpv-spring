package es.upm.miw.betca_tpv_spring.documents;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CashierClosureTest {

    @Test
    void testClose() {
        CashierClosure cashierClosure = new CashierClosure(BigDecimal.ONE);
        cashierClosure.card(BigDecimal.TEN);
        cashierClosure.card(BigDecimal.ONE);
        cashierClosure.cash(BigDecimal.TEN);
        cashierClosure.cash(BigDecimal.ONE);
        cashierClosure.voucher(BigDecimal.ONE);
        cashierClosure.deposit(BigDecimal.ONE,"+1");
        cashierClosure.deposit(BigDecimal.ONE,"+1");
        cashierClosure.withdrawal(BigDecimal.ONE,"-1");
        assertFalse(cashierClosure.isClosed());
        cashierClosure.close(BigDecimal.TEN,BigDecimal.TEN,"close");
        assertEquals(0,BigDecimal.ONE.compareTo(cashierClosure.getLostCard()));
        assertEquals(0,new BigDecimal("3").compareTo(cashierClosure.getLostCash()));
        assertEquals(0,new BigDecimal("10").compareTo(cashierClosure.getFinalCash()));
        assertTrue(cashierClosure.isClosed());
    }

}
