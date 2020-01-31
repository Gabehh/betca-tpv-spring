package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.documents.User;
import es.upm.miw.betca_tpv_spring.dtos.UserMinimumDto;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserReactRepository extends ReactiveSortingRepository<User, String> {

    Mono<User> findByMobile(String mobile);

    @Query(value = "{}", fields = "{ '_id' : 0, 'mobile' : 1, 'username' : 1}")
    Flux<UserMinimumDto> findAllUsers();
}
