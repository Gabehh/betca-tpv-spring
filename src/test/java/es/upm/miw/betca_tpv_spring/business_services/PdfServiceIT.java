package es.upm.miw.betca_tpv_spring.business_services;

import es.upm.miw.betca_tpv_spring.TestConfig;
import es.upm.miw.betca_tpv_spring.repositories.TicketReactRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

@TestConfig
class PdfServiceIT {

    @Autowired
    private PdfService pdfService;

    @Autowired
    private TicketReactRepository ticketReactRepository;

    @Test
    void testPdfGenerateTicket() {
        StepVerifier
                .create(this.pdfService.generateTicket(this.ticketReactRepository.findById("201901121")))
                .thenCancel()
                .verify();
    }

}
