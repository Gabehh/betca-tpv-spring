package es.upm.miw.betca_tpv_spring.business_controllers;

import es.upm.miw.betca_tpv_spring.documents.Sendings;
import es.upm.miw.betca_tpv_spring.dtos.SendingsCreationDto;
import es.upm.miw.betca_tpv_spring.exceptions.NotFoundException;
import es.upm.miw.betca_tpv_spring.repositories.SendingsReactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class SendingsController {

    private SendingsReactRepository sendingsReactRepository;

    @Autowired
    public SendingsController(SendingsReactRepository sendingsReactRepository){
        this.sendingsReactRepository = sendingsReactRepository;
    }

    public Mono<Sendings> readSendings(String id){
        return this.sendingsReactRepository.findById(id)
            .switchIfEmpty(Mono.error(new NotFoundException("Sending id (" + id + ")")));
    }

    public Flux<Sendings> readAll() {
        return this.sendingsReactRepository.findAll();
    }

    public Mono<Sendings> createSendings(SendingsCreationDto sendingsCreationDto) {
        Sendings sendings = new Sendings(sendingsCreationDto.getId(), "u005");
        return sendingsReactRepository.save(sendings);
    }
}
