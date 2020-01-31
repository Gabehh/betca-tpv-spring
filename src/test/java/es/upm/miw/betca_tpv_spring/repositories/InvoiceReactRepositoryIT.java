package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.TestConfig;
import es.upm.miw.betca_tpv_spring.documents.Invoice;
import es.upm.miw.betca_tpv_spring.documents.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestConfig
class InvoiceReactRepositoryIT {

    @Autowired
    private InvoiceReactRepository invoiceReactRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    void testCreate() {
        Ticket ticket = this.ticketRepository.findById("201901121").get();
        Invoice invoice = new Invoice(5, new BigDecimal("20"), new BigDecimal("4.2"), ticket.getUser(), ticket);
        this.invoiceReactRepository.save(invoice).block();
        StepVerifier
                .create(this.invoiceReactRepository.findById(invoice.getId()))
                .expectNextMatches(bdInvoice -> {
                    assertEquals(5, bdInvoice.simpleId());
                    assertEquals(LocalDate.now().getYear() + "5", invoice.getId());
                    assertNotNull(bdInvoice.getCreationDated());
                    return true;
                })
                .expectComplete()
                .verify();
        this.invoiceReactRepository.deleteById(invoice.getId()).block();
    }
}
