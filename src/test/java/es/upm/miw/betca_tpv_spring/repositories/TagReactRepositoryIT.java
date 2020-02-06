package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class TagReactRepositoryIT {

    @Autowired
    private TagReactRepository tagReactRepository;

    @Test
    void testFindAllAndDatabaseSeeder() {
        StepVerifier
                .create(this.tagReactRepository.findAll())
                .expectNextMatches(tag -> {
                    assertEquals("tag1", tag.getDescription());
                    assertNotNull(tag.getId());
                    assertNotNull(tag.getArticleList());
                    assertTrue(tag.getArticleList().size() > 0);
                    assertFalse(tag.toString().matches("@"));
                    return true;
                })
                .thenCancel()
                .verify();
    }

}

