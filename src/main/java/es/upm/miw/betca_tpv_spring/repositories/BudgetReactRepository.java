package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.documents.Budget;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface BudgetReactRepository extends ReactiveSortingRepository<Budget, String> {

}
