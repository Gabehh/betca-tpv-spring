package es.upm.miw.betca_tpv_spring.business_controllers;

import es.upm.miw.betca_tpv_spring.documents.Provider;
import es.upm.miw.betca_tpv_spring.dtos.ProviderSearchDto;
import es.upm.miw.betca_tpv_spring.repositories.ProviderReactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class ProviderController {

    private ProviderReactRepository providerReactRepository;

    @Autowired
    public ProviderController(ProviderReactRepository providerReactRepository) {
        this.providerReactRepository = providerReactRepository;
    }

    public Flux<Provider> search(ProviderSearchDto providerSearchDto) {
        return this.providerReactRepository
                .findByCompanyOrNifOrPhone(
                        providerSearchDto.getCompany(),
                        providerSearchDto.getNif(),
                        providerSearchDto.getPhone());
    }

    public Flux<Provider> readAll() {
        return this.providerReactRepository.findAll();
    }
}
