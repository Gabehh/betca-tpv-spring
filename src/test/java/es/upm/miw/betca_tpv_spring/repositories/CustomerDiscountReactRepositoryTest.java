package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class CustomerDiscountReactRepositoryTest {

    @Autowired
    private CustomerDiscountReactRepository customerDiscountReactRepository;

    @Test
    void testFindAllAndDatabaseSeeder() {
        StepVerifier
                .create(this.customerDiscountReactRepository.findAll())
                .expectNextMatches(discount -> {
                    assertNotNull(discount.getId());
                    Assertions.assertEquals("discount1", discount.getDescription());
                    assertNotNull(discount.getRegistrationDate());
                    assertEquals(0, BigDecimal.TEN.compareTo(discount.getDiscount()));
                    assertEquals(0, BigDecimal.TEN.compareTo(discount.getMinimumPurchase()));
                    assertNotNull(discount.getUser());
                    return true;
                })
                .thenCancel()
                .verify();
    }
}
