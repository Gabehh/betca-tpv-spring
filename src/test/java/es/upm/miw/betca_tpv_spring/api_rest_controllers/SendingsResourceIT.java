package es.upm.miw.betca_tpv_spring.api_rest_controllers;

import es.upm.miw.betca_tpv_spring.dtos.SendingsCreationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static es.upm.miw.betca_tpv_spring.api_rest_controllers.SendingsResource.SENDINGS;

@ApiTestConfig
public class SendingsResourceIT {

    @Autowired
    private RestService restService;

    @Autowired
    private WebTestClient webTestClient;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Test
     void createSendings(){
        SendingsCreationDto sendingsCreationDto = new SendingsCreationDto();

        this.restService.loginAdmin(this.webTestClient)
                .post().uri(contextPath + SENDINGS)
                .body(BodyInserters.fromObject(sendingsCreationDto))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
     void testReadAll(){
        this.restService.loginAdmin(this.webTestClient)
                .get().uri(contextPath + SENDINGS)
                .exchange()
                .expectStatus().isOk();
    }
}
