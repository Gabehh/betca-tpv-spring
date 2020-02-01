package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.documents.FamilyComposite;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface FamilyCompositeReactRepository extends ReactiveSortingRepository<FamilyComposite, String> {

}
