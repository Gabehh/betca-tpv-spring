package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.documents.CustomerDiscount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerDiscountRepository extends MongoRepository<CustomerDiscount, String> {
}
