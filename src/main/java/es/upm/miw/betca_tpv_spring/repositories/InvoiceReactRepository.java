package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.documents.Invoice;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface InvoiceReactRepository extends ReactiveSortingRepository<Invoice, String> {
}
