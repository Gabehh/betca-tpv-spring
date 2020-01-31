package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

@TestConfig
class TagReactRepositoryIT {

    @Autowired
    private TagReactRepository tagReactRepository;

    @Test
    void testReadAll() {
        StepVerifier
                .create(this.tagReactRepository.findAll())
                .expectNextCount(1)
                .thenCancel()
                .verify();
    }

}
