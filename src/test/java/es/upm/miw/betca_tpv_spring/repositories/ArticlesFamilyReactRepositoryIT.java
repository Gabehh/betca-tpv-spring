package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.TestConfig;
import es.upm.miw.betca_tpv_spring.documents.ArticlesFamily;
import es.upm.miw.betca_tpv_spring.documents.FamilyArticle;
import es.upm.miw.betca_tpv_spring.documents.FamilyComposite;
import es.upm.miw.betca_tpv_spring.documents.FamilyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestConfig
class ArticlesFamilyReactRepositoryIT {

    @Autowired
    private ArticlesFamilyReactRepository articlesFamilyReactRepository;

    @Test
    void testReadRoot() {
        StepVerifier
                .create(this.articlesFamilyReactRepository.findByReference("root"))
                .expectNextMatches(family -> {
                    assertNotNull(family.getId());
                    assertEquals(FamilyType.ARTICLES,family.getFamilyType());
                    assertEquals("root",family.getReference());
                    assertEquals("root",family.getDescription());
                    assertNull(family.getArticle());
                    assertNotNull(family.getArticlesFamilyList());
                    return true;
                })
                .expectComplete()
                .verify();
    }

    @Test
    void testReadZzPolo() {
        StepVerifier
                .create(this.articlesFamilyReactRepository.findByReference("Zz Polo"))
                .expectNextMatches(family -> {
                    assertNotNull(family.getId());
                    assertEquals(FamilyType.SIZES,family.getFamilyType());
                    assertEquals("Zz Polo",family.getReference());
                    assertEquals("Zarzuela - Polo",family.getDescription());
                    assertNull(family.getArticle());
                    assertNotNull(family.getArticlesFamilyList());
                    assertFalse(family.toString().matches("@"));
                    return true;
                })
                .expectComplete()
                .verify();
    }

    @Test
    void testReadZzPoloT2() {
        StepVerifier
                .create(this.articlesFamilyReactRepository.findByReference("Zz Polo T2"))
                .expectNextMatches(family -> {
                    assertNotNull(family.getId());
                    assertEquals(FamilyType.ARTICLE,family.getFamilyType());
                    assertEquals("Zz Polo T2",family.getReference());
                    assertEquals("Zarzuela - Polo T2",family.getDescription());
                    assertNotNull(family.getArticle());
                    assertTrue(family.getArticlesFamilyList().isEmpty());
                    family.add(null);
                    family.remove(null);
                    return true;
                })
                .expectComplete()
                .verify();
    }

}
