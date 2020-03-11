package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.documents.FamilyComposite;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Mono;

public interface FamilyCompositeReactRepository extends ReactiveSortingRepository<FamilyComposite, String> {
    Mono<FamilyComposite> findByReference(String reference);
}
