package es.upm.miw.betca_tpv_spring.api_rest_controllers;

import es.upm.miw.betca_tpv_spring.dtos.CashierClosureInputDto;
import es.upm.miw.betca_tpv_spring.dtos.CashierLastOutputDto;
import es.upm.miw.betca_tpv_spring.dtos.CashierStateOutputDto;
import es.upm.miw.betca_tpv_spring.repositories.CashierClosureRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;

import static es.upm.miw.betca_tpv_spring.api_rest_controllers.CashierClosureResource.CASHIER_CLOSURES;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ApiTestConfig
class CashierClosureResourceIT {

    @Autowired
    private RestService restService;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private CashierClosureRepository cashierClosureRepository;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Test
    void testFindCashierClosureLast() {
        this.restService.loginAdmin(webTestClient)
                .get().uri(contextPath + CASHIER_CLOSURES + CashierClosureResource.LAST)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CashierLastOutputDto.class)
                .value(Assertions::assertNotNull)
                .value(cashier -> assertTrue(cashier.isClosed()));
    }

    @Test
    void testCreateCashierAndFindCashierClosureLastTotals() {
        this.restService.loginAdmin(webTestClient)
                .post().uri(contextPath + CASHIER_CLOSURES)
                .exchange()
                .expectStatus().isOk();
        this.restService.loginAdmin(webTestClient)
                .get().uri(contextPath + CASHIER_CLOSURES + CashierClosureResource.LAST)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CashierLastOutputDto.class)
                .value(Assertions::assertNotNull)
                .value(cashier -> assertFalse(cashier.isClosed()));
        this.restService.loginAdmin(webTestClient)
                .get().uri(contextPath + CASHIER_CLOSURES + CashierClosureResource.LAST
                + CashierClosureResource.STATE)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CashierStateOutputDto.class)
                .value(Assertions::assertNotNull);
        this.restService.loginAdmin(webTestClient)
                .patch().uri(contextPath + CASHIER_CLOSURES + CashierClosureResource.LAST)
                .body(BodyInserters.fromObject(new CashierClosureInputDto(BigDecimal.ZERO, BigDecimal.ZERO, "")))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testGetCashierClosureLastTotalsWithCashierClosedBadRequest() {
        this.restService.loginAdmin(webTestClient)
                .get().uri(contextPath + CASHIER_CLOSURES + CashierClosureResource.LAST
                + CashierClosureResource.STATE)
                .exchange()
                .expectStatus().isBadRequest();
    }

}
