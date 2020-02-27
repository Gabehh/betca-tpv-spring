package es.upm.miw.betca_tpv_spring.api_rest_controllers;

import es.upm.miw.betca_tpv_spring.dtos.VoucherCreationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;

import static es.upm.miw.betca_tpv_spring.api_rest_controllers.VoucherResource.VOUCHERS;

@ApiTestConfig
public class VoucherResourceIT {

    @Autowired
    private RestService restService;

    @Autowired
    private WebTestClient webTestClient;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Test
    void createVoucher() {
        VoucherCreationDto voucherCreationDto = new VoucherCreationDto(BigDecimal.valueOf(5.05));

        this.restService.loginAdmin(this.webTestClient)
                .post().uri(contextPath + VOUCHERS)
                .body(BodyInserters.fromObject(voucherCreationDto))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testReadAll() {
        this.restService.loginAdmin(this.webTestClient)
                .get().uri(contextPath + VOUCHERS)
                .exchange()
                .expectStatus().isOk();
    }


}
