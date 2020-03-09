package es.upm.miw.betca_tpv_spring.api_rest_controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.betca_tpv_spring.api_rest_controllers.ProviderResource.PROVIDERS;

@ApiTestConfig
class ProviderResourceIT {

    @Autowired
    private RestService restService;

    @Autowired
    private WebTestClient webTestClient;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Test
    void search() {
        this.restService.loginAdmin(webTestClient)
                .get().uri(contextPath + PROVIDERS)
                .exchange()
                .expectStatus().isOk();
    }
}