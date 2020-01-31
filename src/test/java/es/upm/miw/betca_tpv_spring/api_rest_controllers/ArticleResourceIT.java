package es.upm.miw.betca_tpv_spring.api_rest_controllers;

import es.upm.miw.betca_tpv_spring.dtos.ArticleDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ApiTestConfig
class ArticleResourceIT {

    @Autowired
    private RestService restService;

    @Autowired
    private WebTestClient webTestClient;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Test
    void testReadArticleOne() {
        ArticleDto articleDto = this.restService.loginAdmin(webTestClient)
                .get().uri(contextPath + ArticleResource.ARTICLES + ArticleResource.CODE_ID, "1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(ArticleDto.class)
                .returnResult().getResponseBody();
        assertNotNull(articleDto);
    }

    @Test
    void testReadArticleNonExist() {
        this.restService.loginAdmin(webTestClient)
                .get().uri(contextPath + ArticleResource.ARTICLES + ArticleResource.CODE_ID, "kk")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testCreateArticleRepeated() {
        this.restService.loginAdmin(webTestClient)
                .post().uri(contextPath + ArticleResource.ARTICLES)
                .body(BodyInserters.fromObject(
                        new ArticleDto("8400000000017", "repeated", "", BigDecimal.TEN, 10)))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }


    @Test
    void testCreateArticleNegativePrice() {
        this.restService.loginAdmin(webTestClient)
                .post().uri(contextPath + ArticleResource.ARTICLES)
                .body(BodyInserters.fromObject(
                        new ArticleDto("4800000000011", "new", "", new BigDecimal("-1"), 10)))
                .exchange()
                .expectStatus().isBadRequest();
    }

}
