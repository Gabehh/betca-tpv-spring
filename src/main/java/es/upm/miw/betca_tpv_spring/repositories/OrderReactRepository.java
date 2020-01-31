package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.documents.Order;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface OrderReactRepository extends ReactiveSortingRepository<Order, String> {

}
