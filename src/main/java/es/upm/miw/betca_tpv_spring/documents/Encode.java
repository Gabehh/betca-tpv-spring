package es.upm.miw.betca_tpv_spring.documents;

import java.math.BigInteger;
import java.util.Base64;
import java.util.UUID;

public class Encode {

    public String generateUUIDUrlSafe() {
        byte[] bytes = new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)
                .toByteArray();
        return Base64.getUrlEncoder().encodeToString(bytes).replace("=", "");
    }

}
