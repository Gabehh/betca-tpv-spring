package es.upm.miw.betca_tpv_spring.api_rest_controllers;

import es.upm.miw.betca_tpv_spring.documents.Tax;
import es.upm.miw.betca_tpv_spring.dtos.ArticleDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;

import static es.upm.miw.betca_tpv_spring.api_rest_controllers.ArticleResource.ARTICLES;
import static es.upm.miw.betca_tpv_spring.api_rest_controllers.ArticleResource.CODE_ID;

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
        this.restService.loginAdmin(webTestClient)
                .get().uri(contextPath + ARTICLES + CODE_ID, "1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(ArticleDto.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testReadArticleNonExist() {
        this.restService.loginAdmin(webTestClient)
                .get().uri(contextPath + ARTICLES + CODE_ID, "kk")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testCreateArticleRepeated() {
        this.restService.loginAdmin(webTestClient)
                .post().uri(contextPath + ARTICLES)
                .body(BodyInserters.fromObject(
                        new ArticleDto("8400000000017", "repeated", "", BigDecimal.TEN, 10)))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }


    @Test
    void testCreateArticleNegativePrice() {
        this.restService.loginAdmin(webTestClient)
                .post().uri(contextPath + ARTICLES)
                .body(BodyInserters.fromObject(
                        new ArticleDto("4800000000011", "new", "", new BigDecimal("-1"), 10)))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testReadAllArticles() {
        this.restService.loginAdmin(webTestClient)
                .get().uri(contextPath + ARTICLES)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateArticle(){
        ArticleDto articleDto = new ArticleDto("8400000000017", "articulo editado", "referencia editada", BigDecimal.valueOf(34.5), 15);
        articleDto.setProvider("5e661e811f584734e2b40fa0");
        articleDto.setTax(Tax.FREE);
        articleDto.setDiscontinued(false);
        ArticleDto articleDto2 = new ArticleDto("8400000000017", "Zarzuela - Falda T2", "Zz Falda T2", BigDecimal.valueOf(20), 10);
        articleDto2.setProvider("5e661e811f584734e2b40fa0");
        articleDto2.setTax(Tax.GENERAL);
        articleDto2.setDiscontinued(false);
        this.restService.loginAdmin(webTestClient)
                .put().uri(contextPath + ARTICLES + CODE_ID,"8400000000017")
                .body(BodyInserters.fromObject(articleDto))
                .exchange().expectStatus().isOk();
        this.restService.loginAdmin(webTestClient)
                .put().uri(contextPath + ARTICLES + CODE_ID,"8400000000017")
                .body(BodyInserters.fromObject(articleDto2))
                .exchange().expectStatus().isOk();
    }

}
