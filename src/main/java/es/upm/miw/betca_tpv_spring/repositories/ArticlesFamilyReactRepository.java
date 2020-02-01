package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.documents.ArticlesFamily;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Mono;

public interface ArticlesFamilyReactRepository extends ReactiveSortingRepository<ArticlesFamily, String> {
    Mono<ArticlesFamily> findByReference(String reference);

}
