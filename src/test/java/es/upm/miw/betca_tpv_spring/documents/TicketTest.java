package es.upm.miw.betca_tpv_spring.documents;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TicketTest {

    @Test
    void testGetTotal() {
        Shopping[] shoppingList = {
                new Shopping(1, BigDecimal.ZERO, ShoppingState.COMMITTED, "1", "various",
                        new BigDecimal("7")),
                new Shopping(1, BigDecimal.ZERO, ShoppingState.NOT_COMMITTED, "1", "various",
                        new BigDecimal("3")),
                new Shopping(1, BigDecimal.ZERO, ShoppingState.COMMITTED, "1", "various",
                        new BigDecimal("5")),
                new Shopping(1, BigDecimal.ZERO, ShoppingState.COMMITTED, "1", "various",
                        new BigDecimal("11"))
        };
        Ticket ticket = new Ticket(1, BigDecimal.TEN, new BigDecimal("26.0"), BigDecimal.ZERO,
                shoppingList, null, "note");
        assertEquals(0, new BigDecimal("26.0").compareTo(ticket.getTotal()));
    }

    @Test
    void testGetTotalCommitted() {
        Shopping[] shoppings = {
                new Shopping(1, BigDecimal.ZERO, ShoppingState.COMMITTED, "1", "various",
                        new BigDecimal("7")),
                new Shopping(1, BigDecimal.ZERO, ShoppingState.NOT_COMMITTED, "1", "various",
                        new BigDecimal("3")),
                new Shopping(1, BigDecimal.ZERO, ShoppingState.COMMITTED, "1", "various",
                        new BigDecimal("5")),
                new Shopping(1, BigDecimal.ZERO, ShoppingState.COMMITTED, "1", "various",
                        new BigDecimal("11"))
        };
        Ticket ticket = new Ticket(1, BigDecimal.TEN, new BigDecimal("26.0"), BigDecimal.ZERO,
                shoppings, null, "note");
        assertEquals(0, new BigDecimal("23.0").compareTo(ticket.getTotalCommitted()));
    }
}
