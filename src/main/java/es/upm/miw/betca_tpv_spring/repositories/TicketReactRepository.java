package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.documents.Ticket;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Mono;

public interface TicketReactRepository extends ReactiveSortingRepository<Ticket, String> {

    Mono<Ticket> findFirstByOrderByCreationDateDescIdDesc();

}
