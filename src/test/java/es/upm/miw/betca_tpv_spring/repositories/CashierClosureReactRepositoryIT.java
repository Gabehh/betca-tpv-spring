package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.TestConfig;
import es.upm.miw.betca_tpv_spring.documents.Tax;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class CashierClosureReactRepositoryIT {

    @Autowired
    private CashierClosureReactRepository cashierClosureReactRepository;

    @Test
    void testFindAllAndDatabaseSeeder() {
        StepVerifier
                .create(this.cashierClosureReactRepository.findAll())
                .expectNextMatches(cashierClosure -> {
                    assertNotNull(cashierClosure.getId());
                    assertNotNull(cashierClosure.getClosureDate());
                    assertNotNull(cashierClosure.getOpeningDate());
                    assertEquals(0, BigDecimal.ZERO.compareTo(cashierClosure.getInitialCash()));
                    assertEquals(0, BigDecimal.ZERO.compareTo(cashierClosure.getSalesCash()));
                    assertEquals(0, BigDecimal.ZERO.compareTo(cashierClosure.getSalesCard()));
                    assertEquals(0, BigDecimal.ZERO.compareTo(cashierClosure.getUsedVouchers()));
                    assertEquals(0, BigDecimal.ZERO.compareTo(cashierClosure.getDeposit()));
                    assertEquals(0, BigDecimal.ZERO.compareTo(cashierClosure.getWithdrawal()));
                    assertEquals(0, BigDecimal.ZERO.compareTo(cashierClosure.getLostCash()));
                    assertEquals(0, BigDecimal.ZERO.compareTo(cashierClosure.getLostCard()));
                    assertEquals(0, BigDecimal.ZERO.compareTo(cashierClosure.getFinalCash()));
                    assertNotNull(cashierClosure.getComment());
                    assertFalse(cashierClosure.toString().matches("@"));
                    return true;
                })
                .thenCancel()
                .verify();
    }

}
