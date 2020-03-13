package es.upm.miw.betca_tpv_spring.business_controllers;

import es.upm.miw.betca_tpv_spring.documents.Provider;
import es.upm.miw.betca_tpv_spring.dtos.ProviderCreationDto;
import es.upm.miw.betca_tpv_spring.dtos.ProviderDto;
import es.upm.miw.betca_tpv_spring.dtos.ProviderSearchDto;
import es.upm.miw.betca_tpv_spring.repositories.ProviderReactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class ProviderController {

    private ProviderReactRepository providerReactRepository;

    @Autowired
    public ProviderController(ProviderReactRepository providerReactRepository) {
        this.providerReactRepository = providerReactRepository;
    }

    public Flux<ProviderDto> search(ProviderSearchDto providerSearchDto) {
        return this.providerReactRepository
                .findByCompanyOrNifOrPhone(
                        providerSearchDto.getCompany(),
                        providerSearchDto.getNif(),
                        providerSearchDto.getPhone())
                .map(ProviderDto::new);
    }

    public Flux<ProviderDto> readAll() {
        return this.providerReactRepository.findAll()
                .map(ProviderDto::new);
    }

    public Mono<ProviderDto> create(ProviderCreationDto providerCreationDto) {
        Provider provider = Provider.builder(providerCreationDto.getCompany())
                .nif(providerCreationDto.getNif())
                .address(providerCreationDto.getAddress())
                .phone(providerCreationDto.getPhone())
                .email(providerCreationDto.getEmail())
                .note(providerCreationDto.getNote())
                .build();
        return this.providerReactRepository.save(provider)
                .map(ProviderDto::new);
    }

    private Boolean exists(String company) {
        return this.providerReactRepository.findByCompany(company);
    }
}
