package es.upm.miw.betca_tpv_spring.api_rest_controllers;

import es.upm.miw.betca_tpv_spring.business_controllers.ProviderController;
import es.upm.miw.betca_tpv_spring.documents.Provider;
import es.upm.miw.betca_tpv_spring.dtos.ProviderCreationDto;
import es.upm.miw.betca_tpv_spring.dtos.ProviderSearchDto;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('OPERATOR')")
@RestController
@RequestMapping(ProviderResource.PROVIDERS)
public class ProviderResource {

    public static final String PROVIDERS = "/providers";
    public static final String ID = "/{id}";

    private ProviderController providerController;

    @Autowired
    public ProviderResource(ProviderController providerController) {
        this.providerController = providerController;
    }

    @GetMapping
    public Flux<Provider> search(@RequestParam(required = false) String company,
                                 @RequestParam(required = false) String nif,
                                 @RequestParam(required = false) String phone) {
        ProviderSearchDto providerSearchDto = new ProviderSearchDto(company, nif, phone);
        if (company == null && nif == null && phone == null) {
            return this.providerController.readAll()
                    .doOnEach(log -> LogManager.getLogger(this.getClass()).debug(log));
        } else {
            return this.providerController.search(providerSearchDto)
                    .doOnEach(log -> LogManager.getLogger(this.getClass()).debug(log));
        }
    }

    @PostMapping(produces = {"application/json"})
    public Mono<Provider> create(@RequestBody ProviderCreationDto providerCreationDto) {
        return this.providerController.create(providerCreationDto)
                .doOnNext(log -> LogManager.getLogger(this.getClass()).debug(log));
    }
}
