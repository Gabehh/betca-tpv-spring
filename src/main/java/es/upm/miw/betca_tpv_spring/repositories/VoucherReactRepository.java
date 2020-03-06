package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.documents.Voucher;
import org.apache.tomcat.jni.Local;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

import java.sql.Date;
import java.time.LocalDateTime;

public interface VoucherReactRepository extends ReactiveSortingRepository<Voucher, String> {

    Flux<Voucher> findByIdAndCreationDateBetween(String id, LocalDateTime firstDate, LocalDateTime finalDate);

    Flux<Voucher> findAllByCreationDateBetween(LocalDateTime firstDate, LocalDateTime finalDate);

    Flux<Voucher> findAllByCreationDateIsBefore(LocalDateTime finalDate);
}
