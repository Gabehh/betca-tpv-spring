package es.upm.miw.betca_tpv_spring.api_rest_controllers;

import es.upm.miw.betca_tpv_spring.business_controllers.VoucherController;
import es.upm.miw.betca_tpv_spring.documents.Voucher;
import es.upm.miw.betca_tpv_spring.dtos.VoucherCreationDto;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('OPERATOR')")
@RestController
@RequestMapping(VoucherResource.VOUCHERS)
public class VoucherResource {

    public static final String VOUCHERS = "/vouchers";
    public static final String VOUCHER_ID = "/{id}";
    public static final String PRINT = "/print";

    private VoucherController voucherController;

    @Autowired
    public VoucherResource(VoucherController voucherController) {
        this.voucherController = voucherController;
    }


    @GetMapping
    public Flux<Voucher> readAll() {
        return this.voucherController.readAll()
                .doOnEach(log -> LogManager.getLogger(this.getClass()).debug(log));
    }

    @GetMapping(value = VOUCHER_ID)
    public Mono<Voucher> read(@PathVariable String id) {
        return this.voucherController.readVoucher(id)
                .doOnSuccess(log -> LogManager.getLogger(this.getClass()).debug(log));
    }

    @PostMapping(produces = {"application/json"})
    public Mono<Voucher> createVoucher(@Valid @RequestBody VoucherCreationDto voucherCreationDto) {
        return this.voucherController.createVoucher(voucherCreationDto)
                .doOnNext(log -> LogManager.getLogger(this.getClass()).debug(log));
    }

    @PutMapping(value = VOUCHER_ID, produces = {"application/json"})
    public Mono<Void> consume(@PathVariable String id) {
        return this.voucherController.consumeVoucher(id)
                .doOnNext(log -> LogManager.getLogger(this.getClass()).debug(log));
    }

    @GetMapping(value = VOUCHER_ID + PRINT, produces = {"application/pdf"})
    public Mono<byte[]> print(@PathVariable String id) {
        return this.voucherController.print(id)
                .doOnNext(log -> LogManager.getLogger(this.getClass()).debug(log));
    }

}
