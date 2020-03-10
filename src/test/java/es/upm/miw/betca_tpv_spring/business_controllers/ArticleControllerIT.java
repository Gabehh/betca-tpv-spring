package es.upm.miw.betca_tpv_spring.business_controllers;

import es.upm.miw.betca_tpv_spring.TestConfig;
import es.upm.miw.betca_tpv_spring.documents.Tax;
import es.upm.miw.betca_tpv_spring.dtos.ArticleDto;
import es.upm.miw.betca_tpv_spring.exceptions.ConflictException;
import es.upm.miw.betca_tpv_spring.exceptions.NotFoundException;
import es.upm.miw.betca_tpv_spring.repositories.ArticleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

@TestConfig
class ArticleControllerIT {

    @Autowired
    private ArticleController articleController;

    @Autowired
    private ArticleRepository articleRepository;

    private ArticleDto articleDto;

    @BeforeEach
    void seed() {
        this.articleDto = new ArticleDto("no exist", "descrip", "ref", BigDecimal.TEN, null);
    }

    @Test
    void testRead() {
        StepVerifier
                .create(this.articleController.readArticle("8400000000017"))
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

    @Test
    void testReadNotFound() {
        StepVerifier
                .create(this.articleController.readArticle("no exist"))
                .expectError(NotFoundException.class)
                .verify();
    }

    @Test
    void testConflictRequestException() {
        this.articleDto.setCode("8400000000017");
        StepVerifier
                .create(this.articleController.createArticle(this.articleDto))
                .expectError(ConflictException.class)
                .verify();
        this.articleDto.setCode("no exist");
    }

    @Test
    void testProviderNotFoundException() {
        this.articleDto.setProvider("no exist");
        StepVerifier
                .create(this.articleController.createArticle(this.articleDto))
                .expectError(NotFoundException.class)
                .verify();
    }

    @Test
    void testInitStock() {
        StepVerifier
                .create(this.articleController.createArticle(this.articleDto))
                .expectNextMatches(articleDtoCreated ->
                        articleDtoCreated.getStock() != null)
                .expectComplete()
                .verify();
    }

    @Test
    void testReadAll() {
        StepVerifier
                .create(this.articleController.readAll())
                .expectNextCount(9)
                .expectComplete()
                .verify();
    }

    @Test
    void testUpdateArticle() {
        ArticleDto articleDto = new ArticleDto("8400000000017", "articulo editado", "referencia editada", BigDecimal.valueOf(34.5), 15);
        articleDto.setProvider("5e661e811f584734e2b40fa0");
        articleDto.setTax(Tax.FREE);
        articleDto.setDiscontinued(false);

        ArticleDto articleDto2 = new ArticleDto("8400000000017", "Zarzuela - Falda T2", "Zz Falda T2", BigDecimal.valueOf(20), 10);
        articleDto2.setProvider("5e661e811f584734e2b40fa0");
        articleDto2.setTax(Tax.GENERAL);
        articleDto2.setDiscontinued(false);
        StepVerifier
                .create(this.articleController.updateArticle("8400000000017", articleDto))
                .expectNextCount(1)
                .expectComplete()
                .verify();

        StepVerifier
                .create(this.articleController.readArticle("8400000000017"))
                .expectNextMatches(articleDto1 -> "articulo editado".equals(articleDto1.getDescription()))
                .expectComplete()
                .verify();

        StepVerifier
                .create(this.articleController.updateArticle("8400000000017",articleDto2))
                .expectNextMatches(articleDtoOr -> "Zarzuela - Falda T2".equals(articleDtoOr.getDescription()))
                .expectComplete()
                .verify();
    }

    @AfterEach
    void delete() {
        this.articleRepository.deleteById(this.articleDto.getCode());
    }
}
