package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.documents.CashierClosure;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Mono;

public interface CashierClosureReactRepository extends ReactiveSortingRepository<CashierClosure, String> {

    Mono<CashierClosure> findFirstByOrderByOpeningDateDesc();

}
