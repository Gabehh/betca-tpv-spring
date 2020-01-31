package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.documents.Voucher;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface VoucherReactRepository extends ReactiveSortingRepository<Voucher, String> {

}
