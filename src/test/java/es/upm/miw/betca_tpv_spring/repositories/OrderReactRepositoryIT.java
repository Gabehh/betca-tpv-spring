package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.TestConfig;
import es.upm.miw.betca_tpv_spring.data_services.DatabaseSeederService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class OrderReactRepositoryIT {

    @Autowired
    private OrderReactRepository orderReactRepository;

    @Autowired
    private DatabaseSeederService databaseSeederService;

    @Test
    void testFindAllAndDatabaseSeeder() {
        StepVerifier
                .create(this.orderReactRepository.findAll())
                .expectNextMatches(order -> {
                    assertEquals("order1", order.getDescription());
                    assertNotNull(order.getId());
                    assertNotNull(order.getOpeningDate());
                    assertNull(order.getClosingDate());
                    assertNotNull(order.getOrderLines());
                    assertFalse(order.toString().matches("@"));
                    return true;
                })
                .thenCancel()
                .verify();
    }

    @Test
    void testClose() {
        StepVerifier
                .create(this.orderReactRepository.findAll())
                .expectNextMatches(order -> {
                    assertEquals("order1", order.getDescription());
                    order.close();
                    assertEquals(new Integer(8), order.getOrderLines()[1].getFinalAmount());
                    return true;
                })
                .thenCancel()
                .verify();
        this.databaseSeederService.deleteAllAndInitializeAndSeedDataBase();
    }

}
