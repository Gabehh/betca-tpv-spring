package es.upm.miw.betca_tpv_spring.business_services;

import es.upm.miw.betca_tpv_spring.TestConfig;
import es.upm.miw.betca_tpv_spring.exceptions.PdfException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestConfig
class PdfBuilderIT {

    @Test
    void testPdfBuilder() {
        assertThrows(PdfException.class, () -> new PdfBuilder("&-InvalidName"));
    }
}
