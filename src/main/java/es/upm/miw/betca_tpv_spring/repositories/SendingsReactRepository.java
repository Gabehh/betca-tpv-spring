package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.documents.Sendings;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface SendingsReactRepository extends ReactiveSortingRepository<Sendings, String>{

}
