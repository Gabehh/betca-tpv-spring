package es.upm.miw.betca_tpv_spring.documents;

import javax.xml.bind.DatatypeConverter;
import java.util.Base64;
import java.util.UUID;

public class Encode {

    public String generateUUIDUrlSafe() {
        return Base64.getUrlEncoder().encodeToString(DatatypeConverter
                .parseHexBinary(UUID.randomUUID().toString().replace("-", "")))
                .replace("=", "");
    }
}
