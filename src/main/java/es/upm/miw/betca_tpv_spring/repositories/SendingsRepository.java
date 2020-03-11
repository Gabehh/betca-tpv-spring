package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.documents.Sendings;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SendingsRepository extends MongoRepository<Sendings, String> {
}
