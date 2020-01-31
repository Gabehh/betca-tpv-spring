package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.documents.FamilyComposite;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FamilyCompositeRepository extends MongoRepository<FamilyComposite, String> {
    Optional<FamilyComposite> findByReference(String reference);

}
