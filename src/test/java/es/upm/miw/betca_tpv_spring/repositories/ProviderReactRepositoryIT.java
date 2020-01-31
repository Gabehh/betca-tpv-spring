package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

@TestConfig
class ProviderReactRepositoryIT {

    @Autowired
    private ProviderReactRepository providerReactRepository;

    @Test
    void testReadAll() {
        StepVerifier
                .create(this.providerReactRepository.findAll())
                .expectNextCount(1)
                .thenCancel()
                .verify();
    }

}
