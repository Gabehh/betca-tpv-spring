package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.documents.Provider;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

public interface ProviderReactRepository extends ReactiveSortingRepository<Provider, String> {

    Flux<Provider> findByCompanyOrNifOrPhone(String company, String nif, String phone);
}
