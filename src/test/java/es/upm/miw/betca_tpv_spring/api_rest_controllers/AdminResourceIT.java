package es.upm.miw.betca_tpv_spring.api_rest_controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@ApiTestConfig
class AdminResourceIT {

    @Autowired
    private RestService restService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDeleteAndSeedDB() {
        this.restService.deleteDB(webTestClient);
        this.restService.reLoadTestDB(webTestClient);
    }

}
