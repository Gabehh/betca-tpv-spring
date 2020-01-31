package es.upm.miw.betca_tpv_spring.api_rest_controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ApiTestConfig
class SystemResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Test
    void testReadVersionBadge() {

        byte[] badgeBytes = this.webTestClient
                .get().uri(contextPath + SystemResource.SYSTEM + SystemResource.VERSION_BADGE)
                .exchange()
                .expectStatus().isOk()
                .expectBody(byte[].class)
                .returnResult().getResponseBody();
        assertNotNull(badgeBytes);
        String badge = new String(badgeBytes);
        assertEquals("<svg", badge.substring(0, 4));
    }

    @Test
    void testReadVersion() {
        AppInfoDto appInfoDto = this.webTestClient
                .get().uri(contextPath + SystemResource.SYSTEM + SystemResource.APP_INFO)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AppInfoDto.class)
                .returnResult().getResponseBody();
        assertNotNull(appInfoDto);
        assertNotNull(appInfoDto.getVersion());
    }


    @Test
    void testException() {
        this.webTestClient.post().uri(contextPath + SystemResource.SYSTEM + SystemResource.VERSION_BADGE)
                .exchange()
                .expectStatus().isBadRequest();
    }
}
