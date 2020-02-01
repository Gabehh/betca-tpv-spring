package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.TestConfig;
import es.upm.miw.betca_tpv_spring.exceptions.ConflictException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDate;

import static junit.framework.TestCase.assertNull;
import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class VoucherReactRepositoryIT {

    @Autowired
    private VoucherReactRepository voucherReactRepository;

    @Test
    void testFindAllAndDatabaseSeeder() {
        StepVerifier
                .create(this.voucherReactRepository.findAll())
                .expectNextMatches(voucher -> {
                    assertNotNull(voucher.getCreationDate());
                    assertNotNull(voucher.getValue());
                    assertNotNull(voucher.getId());
                    assertNull(voucher.getDateOfUse());
                    assertFalse(voucher.isUsed());
                    assertFalse(voucher.toString().matches("@"));
                    return true;
                })
                .expectNextMatches(voucher -> {
                    assertNotNull(voucher.getDateOfUse());
                    assertTrue(voucher.isUsed());
                    assertThrows(ConflictException.class, voucher::use);
                    return true;
                })
                .thenCancel()
                .verify();
    }
}
