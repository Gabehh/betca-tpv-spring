package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class TicketReactRepositoryIT {

    @Autowired
    private TicketReactRepository ticketReactRepository;

    @Test
    void testFindAllAndDatabaseSeeder() {
        StepVerifier
                .create(this.ticketReactRepository.findAll())
                .expectNextMatches(ticket -> {
                    assertEquals("201901121", ticket.getId());
                    assertNotNull(ticket.getCreationDate());
                    assertNotNull(ticket.getReference());
                    assertNotNull(ticket.getShoppingList());
                    assertEquals(0,BigDecimal.TEN.compareTo(ticket.getCard()));
                    assertEquals(0,new BigDecimal("25").compareTo(ticket.getCash()));
                    assertEquals(0,BigDecimal.ZERO.compareTo(ticket.getVoucher()));
                    assertEquals(0,new BigDecimal("20").compareTo(ticket.getTotalCommitted()));
                    assertEquals(0,new BigDecimal("61.7").compareTo(ticket.getTotal()));
                    assertFalse(ticket.toString().matches("@"));
                    return true;
                })
                .thenCancel()
                .verify();
    }

}

