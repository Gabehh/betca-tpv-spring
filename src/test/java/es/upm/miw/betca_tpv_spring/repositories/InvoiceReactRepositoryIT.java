package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class InvoiceReactRepositoryIT {

    @Autowired
    private InvoiceReactRepository invoiceReactRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    void testFindAllAndDatabaseSeeder() {
        StepVerifier
                .create(this.invoiceReactRepository.findAll())
                .expectNextMatches(invoice -> {
                    assertEquals(1, invoice.simpleId());
                    assertEquals(LocalDate.now().getYear() + "1", invoice.getId());
                    assertNotNull(invoice.getCreationDated());
                    assertNotNull(invoice.getUser());
                    assertNotNull(invoice.getTicket());
                    assertFalse(invoice.toString().matches("@"));
                    return true;
                })
                .expectComplete()
                .verify();
    }
}
