package es.upm.miw.betca_tpv_spring.business_services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BarcodeTest {

    @Test
    void testEan13ControlCodeCalculator() {
        assertEquals("9", new Barcode().ean13ControlCodeCalculator("801518700849"));
    }

    @Test
    void generateEan13code() {
        assertEquals("8400000000017", new Barcode().generateEan13code(840000000001L));
    }

}
