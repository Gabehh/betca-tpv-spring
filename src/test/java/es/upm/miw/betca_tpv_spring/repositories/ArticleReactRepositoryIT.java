package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

@TestConfig
class ArticleReactRepositoryIT {

    @Autowired
    private ArticleReactRepository articleReactRepository;

    @Test
    void testReadAll() {
        StepVerifier
                .create(this.articleReactRepository.findAll())
                .expectNextCount(4)
                .thenCancel()
                .verify();
    }

}
