package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.documents.CashierClosure;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CashierClosureRepository extends MongoRepository<CashierClosure, String> {

    CashierClosure findFirstByOrderByOpeningDateDesc();

}
