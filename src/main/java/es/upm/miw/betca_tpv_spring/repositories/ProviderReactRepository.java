package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.documents.Provider;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface ProviderReactRepository extends ReactiveSortingRepository<Provider, String> {

}
