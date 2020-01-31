package es.upm.miw.betca_tpv_spring.business_controllers;

import es.upm.miw.betca_tpv_spring.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class CashierClosureControllerIT {

    @Autowired
    private CashierClosureController cashierClosureController;

    @Test
    void testReadCashierClosureLast() {
        StepVerifier
                .create(cashierClosureController.readCashierClosureLast())
                .expectNextMatches(cashierLastOutputDto -> {
                    assertNotNull(cashierLastOutputDto);
                    assertTrue(cashierLastOutputDto.isClosed());
                    return true;
                })
                .expectComplete()
                .verify();
    }

}
