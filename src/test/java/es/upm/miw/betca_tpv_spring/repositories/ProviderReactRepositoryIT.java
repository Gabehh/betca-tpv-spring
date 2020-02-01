package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import static es.upm.miw.betca_tpv_spring.data_services.DatabaseSeederService.VARIOUS_NAME;
import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class ProviderReactRepositoryIT {

    @Autowired
    private ProviderReactRepository providerReactRepository;

    @Test
    void testFindAllAndDatabaseSeeder() {
        StepVerifier
                .create(this.providerReactRepository.findAll())
                .expectNextMatches(provider -> {
                    assertEquals(VARIOUS_NAME, provider.getCompany());
                    return true;
                })
                .expectNextMatches(provider -> {
                    assertEquals("pro1",provider.getCompany());
                    assertEquals("12345678b",provider.getNif());
                    assertNotNull(provider.getId());
                    assertTrue(provider.isActive());
                    assertNotNull(provider.getAddress());
                    assertNotNull(provider.getPhone());
                    assertNotNull(provider.getEmail());
                    assertNotNull(provider.getNote());
                    assertFalse(provider.toString().matches("@"));
                    return true;
                })
                .thenCancel()
                .verify();
    }

}
