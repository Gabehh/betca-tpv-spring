package es.upm.miw.betca_tpv_spring.business_controllers;

import es.upm.miw.betca_tpv_spring.TestConfig;
import es.upm.miw.betca_tpv_spring.dtos.ArticleDto;
import es.upm.miw.betca_tpv_spring.dtos.FamilyCompositeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestConfig
public class ArticlesFamilyControllerIT {

    @Autowired
    private ArticlesFamilyController articlesFamilyController;


    private FamilyCompositeDto familyCompositeDto;

    @BeforeEach
    void seed() {
        this.familyCompositeDto = new FamilyCompositeDto("root", null);
    }

    @Test
    void testReadFamilyCompositeArticlesList() {
        StepVerifier
                .create(this.articlesFamilyController.readFamilyCompositeArticlesList("root"))
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

    @Test
    void testReadArticlesFamilyList() {
        assertNotNull(articlesFamilyController.readArticlesFamilyList("root"));
    }

}
