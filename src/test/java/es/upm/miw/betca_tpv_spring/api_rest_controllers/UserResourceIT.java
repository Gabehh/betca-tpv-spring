package es.upm.miw.betca_tpv_spring.api_rest_controllers;

import es.upm.miw.betca_tpv_spring.dtos.UserDto;
import es.upm.miw.betca_tpv_spring.dtos.UserMinimumDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
                .post().uri(contextPath + UserResource.USERS + UserResource.TOKEN)
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    void testLoginNonMobile() {
        webTestClient
                .mutate().filter(basicAuthentication("1", "kk")).build()
                .post().uri(contextPath + UserResource.USERS + UserResource.TOKEN)
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    void testReadAdminWithAdminRole() {
        UserDto userDto = this.restService.loginAdmin(this.webTestClient)
                .get().uri(contextPath + UserResource.USERS + UserResource.MOBILE_ID, this.restService.getAdminMobile())
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserDto.class)
                .returnResult().getResponseBody();
        assertNotNull(userDto);
        assertEquals(this.restService.getAdminMobile(), userDto.getMobile());
    }

    @Test
    void testReadOperatorWithManagerRole() {
        UserDto userDto = this.restService.loginManager(this.webTestClient)
                .get().uri(contextPath + UserResource.USERS + UserResource.MOBILE_ID, "666666002")
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserDto.class)
                .returnResult().getResponseBody();
        assertNotNull(userDto);
        assertEquals("666666002", userDto.getMobile());
    }

    @Test
    void testReadCustomerWithRoleOperator() {
        UserDto userDto = this.restService.loginManager(this.webTestClient)
                .get().uri(contextPath + UserResource.USERS + UserResource.MOBILE_ID, "666666004")
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserDto.class)
                .returnResult().getResponseBody();
        assertNotNull(userDto);
        assertEquals("666666004", userDto.getMobile());
    }

    @Test
    void testReadOperatorWithRoleOperator() {
        this.restService.loginOperator(this.webTestClient)
                .get().uri(contextPath + UserResource.USERS + UserResource.MOBILE_ID, "666666003")
                .exchange()
                .expectStatus().isForbidden();
    }

    @Test
    void testReadAll() {
        List<UserMinimumDto> userMinimumDtoList = this.restService.loginAdmin(this.webTestClient)
                .get().uri(contextPath + UserResource.USERS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(UserMinimumDto.class)
                .returnResult().getResponseBody();
        assertNotNull(userMinimumDtoList);
        assertTrue(userMinimumDtoList.size() > 1);
    }

}
