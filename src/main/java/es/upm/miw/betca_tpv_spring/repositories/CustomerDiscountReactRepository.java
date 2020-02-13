package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.documents.CustomerDiscount;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface CustomerDiscountReactRepository  extends ReactiveSortingRepository<CustomerDiscount, String> {
}
