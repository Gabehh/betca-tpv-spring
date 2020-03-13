package es.upm.miw.betca_tpv_spring.api_rest_controllers;

import es.upm.miw.betca_tpv_spring.dtos.ProviderCreationDto;
import es.upm.miw.betca_tpv_spring.dtos.ProviderDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static es.upm.miw.betca_tpv_spring.api_rest_controllers.ProviderResource.PROVIDERS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ApiTestConfig
class ProviderResourceIT {

    @Autowired
    private RestService restService;

    @Autowired
    private WebTestClient webTestClient;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Test
    void testSearch() {
        this.restService.loginAdmin(webTestClient)
                .get().uri(contextPath + PROVIDERS)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testCreate() {
        ProviderCreationDto providerCreationDto =
                new ProviderCreationDto("pro3", "12345678x", "C/TPV-pro, 3", "9166666603", "p3@gmail.com", "p3", true);

        ProviderDto provider = this.restService.loginAdmin(this.webTestClient)
                .post().uri(contextPath + PROVIDERS)
                .body(BodyInserters.fromObject(providerCreationDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProviderDto.class)
                .returnResult().getResponseBody();

        assertNotNull(provider);
        assertEquals("pro3", provider.getCompany());
    }
}