package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.TestConfig;
import es.upm.miw.betca_tpv_spring.documents.Invoice;
import es.upm.miw.betca_tpv_spring.documents.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
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
                    assertEquals(0,new BigDecimal("48.75").compareTo(invoice.getBaseTax()));
                    assertEquals(0,new BigDecimal("12.95").compareTo(invoice.getTax()));
                    assertNotNull(invoice.getTicket());
                    assertFalse(invoice.toString().matches("@"));
                    return true;
                })
                .expectComplete()
                .verify();
    }
}
