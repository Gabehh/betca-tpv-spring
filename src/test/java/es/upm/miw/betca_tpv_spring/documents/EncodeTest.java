package es.upm.miw.betca_tpv_spring.documents;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class EncodeTest {

    @Test
    void testGenerateUUIDUrlSafe() {
        String url = new Encode().generateUUIDUrlSafe();
        assertEquals(-1, url.indexOf("+"));
        assertEquals(-1, url.indexOf("/"));
        assertEquals(-1, url.indexOf("="));
    }

}
