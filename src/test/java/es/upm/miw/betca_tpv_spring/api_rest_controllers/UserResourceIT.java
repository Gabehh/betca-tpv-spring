package es.upm.miw.betca_tpv_spring.api_rest_controllers;

import es.upm.miw.betca_tpv_spring.dtos.UserDto;
import es.upm.miw.betca_tpv_spring.dtos.UserMinimumDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.betca_tpv_spring.api_rest_controllers.UserResource.USERS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@ApiTestConfig
class UserResourceIT {

    @Autowired
    private RestService restService;

    @Autowired
    private WebTestClient webTestClient;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Test
    void testLogin() {
        this.restService.loginAdmin(this.webTestClient);
        assertTrue(this.restService.getTokenDto().getToken().length() > 10);
    }

    @Test
    void testLoginAdminPassNull() {
        webTestClient
                .mutate().filter(basicAuthentication(restService.getAdminMobile(), "kk")).build()
                .post().uri(contextPath + USERS + UserResource.TOKEN)
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    void testLoginNonMobile() {
        webTestClient
                .mutate().filter(basicAuthentication("1", "kk")).build()
                .post().uri(contextPath + USERS + UserResource.TOKEN)
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    void testReadAdminWithAdminRole() {
        this.restService.loginAdmin(this.webTestClient)
                .get().uri(contextPath + USERS + UserResource.MOBILE_ID, this.restService.getAdminMobile())
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserDto.class)
                .value(Assertions::assertNotNull)
                .value(user -> assertEquals(this.restService.getAdminMobile(), user.getMobile()));
    }

    @Test
    void testReadOperatorWithManagerRole() {
        this.restService.loginManager(this.webTestClient)
                .get().uri(contextPath + USERS + UserResource.MOBILE_ID, "666666002")
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserDto.class)
                .value(Assertions::assertNotNull)
                .value(user -> assertEquals("666666002", user.getMobile()));

    }

    @Test
    void testReadCustomerWithRoleOperator() {
        this.restService.loginManager(this.webTestClient)
                .get().uri(contextPath + USERS + UserResource.MOBILE_ID, "666666004")
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserDto.class)
                .value(Assertions::assertNotNull)
                .value(user -> assertEquals("666666004", user.getMobile()));
    }

    @Test
    void testReadOperatorWithRoleOperator() {
        this.restService.loginOperator(this.webTestClient)
                .get().uri(contextPath + USERS + UserResource.MOBILE_ID, "666666003")
                .exchange()
                .expectStatus().isForbidden();
    }

    @Test
    void testReadAll() {
        this.restService.loginAdmin(this.webTestClient)
                .get().uri(contextPath + USERS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(UserMinimumDto.class)
                .value(Assertions::assertNotNull)
                .value(list -> assertTrue(list.size() > 1));
    }

}
