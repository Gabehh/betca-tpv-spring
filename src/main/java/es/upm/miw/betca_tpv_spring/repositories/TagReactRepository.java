package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.documents.Tag;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface TagReactRepository extends ReactiveSortingRepository<Tag, String> {

}
