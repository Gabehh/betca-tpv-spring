package es.upm.miw.betca_tpv_spring.api_rest_controllers;

import es.upm.miw.betca_tpv_spring.business_controllers.VoucherController;
import es.upm.miw.betca_tpv_spring.documents.Voucher;
import es.upm.miw.betca_tpv_spring.dtos.VoucherCreationDto;
import es.upm.miw.betca_tpv_spring.dtos.VoucherSearchDto;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public Flux<Voucher> search(@RequestParam String id, @RequestParam String firstDate, @RequestParam String finalDate){
        VoucherSearchDto voucherSearchDto;

        if (id.equals("null") || (id.equals(""))) {
            voucherSearchDto = new VoucherSearchDto(LocalDateTime.parse(firstDate, DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse(finalDate, DateTimeFormatter.ISO_DATE_TIME));
        }
        else
            voucherSearchDto = new VoucherSearchDto(id, LocalDateTime.parse(firstDate, DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse(finalDate, DateTimeFormatter.ISO_DATE_TIME));

        return this.voucherController.searchVoucher(voucherSearchDto)
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
        return this.voucherController.printVoucher(id)
                .doOnNext(log -> LogManager.getLogger(this.getClass()).debug(log));
    }

    @PostMapping(value = PRINT, produces = {"application/pdf"})
    public Mono<byte[]> createAndPrint(@Valid @RequestBody VoucherCreationDto voucherCreationDto){
        return this.voucherController.createAndPrintVoucher(voucherCreationDto)
                .doOnNext(log -> LogManager.getLogger(this.getClass()).debug(log));
    }
}
