package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

@TestConfig
class TicketReactRepositoryIT {

    @Autowired
    private TicketReactRepository ticketReactRepository;

    @Test
    void testReadAll() {
        StepVerifier
                .create(this.ticketReactRepository.findAll())
                .expectNextCount(1)
                .thenCancel()
                .verify();
    }

}
