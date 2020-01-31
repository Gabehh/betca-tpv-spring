package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

@TestConfig
class VoucherReactRepositoryIT {

    @Autowired
    private VoucherReactRepository voucherReactRepository;

    @Test
    void testFindById() {
        StepVerifier
                .create(this.voucherReactRepository.findAll())
                .expectNextCount(1)
                .thenCancel()
                .verify();
    }
}
