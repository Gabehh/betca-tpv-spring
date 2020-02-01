package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.TestConfig;
import es.upm.miw.betca_tpv_spring.documents.Tax;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static es.upm.miw.betca_tpv_spring.data_services.DatabaseSeederService.VARIOUS_NAME;
import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class ArticleReactRepositoryIT {

    @Autowired
    private ArticleReactRepository articleReactRepository;

    @Test
    void testFindAllAndDatabaseSeeder() {
        StepVerifier
                .create(this.articleReactRepository.findAll())
                .expectNextMatches(article -> {
                    assertEquals("1", article.getCode());
                    assertEquals(VARIOUS_NAME, article.getReference());
                    assertEquals(VARIOUS_NAME, article.getDescription());
                    assertEquals(0, new BigDecimal("100").compareTo(article.getRetailPrice()));
                    assertNotNull(article.getRegistrationDate());
                    assertEquals(Tax.GENERAL, article.getTax());
                    assertFalse(article.isDiscontinued());
                    assertEquals(new Integer(1000), article.getStock());
                    return true;
                })
                .expectNextMatches(article -> {
                    assertEquals("8400000000017", article.getCode());
                    assertEquals("Zz Falda T2", article.getReference());
                    assertEquals("Zarzuela - Falda T2", article.getDescription());
                    assertEquals(0, new BigDecimal("20").compareTo(article.getRetailPrice()));
                    assertNotNull(article.getRegistrationDate());
                    assertEquals(Tax.GENERAL, article.getTax());
                    assertFalse(article.isDiscontinued());
                    assertEquals(new Integer(10), article.getStock());
                    assertFalse(article.toString().matches("@"));
                    return true;
                })
                .thenCancel()
                .verify();
    }

}
