package es.upm.miw.betca_tpv_spring.business_controllers;

import es.upm.miw.betca_tpv_spring.documents.Role;
import es.upm.miw.betca_tpv_spring.documents.Voucher;
import es.upm.miw.betca_tpv_spring.dtos.ArticleDto;
import es.upm.miw.betca_tpv_spring.dtos.VoucherCreationDto;
import es.upm.miw.betca_tpv_spring.exceptions.ForbiddenException;
import es.upm.miw.betca_tpv_spring.exceptions.NotFoundException;
import es.upm.miw.betca_tpv_spring.repositories.VoucherReactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class VoucherController {

    private VoucherReactRepository voucherReactRepository;

    @Autowired
    public VoucherController(VoucherReactRepository voucherReactRepository) {
        this.voucherReactRepository = voucherReactRepository;
    }

    public Mono<Voucher> readVoucher(String id) {
        return this.voucherReactRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Vouche code (" + id + ")")));

    }

    public Flux<Voucher> readAll() {
        return this.voucherReactRepository.findAll();
    }

    public Mono<Voucher> createVoucher(VoucherCreationDto voucherCreationDto){
        Voucher voucher = new Voucher(voucherCreationDto.getValue());
        return voucherReactRepository.save(voucher);
    }

    public Mono<Void> consumeVoucher(String id) {
        Mono<Voucher> mono = voucherReactRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Vouche code (" + id + ")")))
                .doOnNext(voucher -> {
                    if(!voucher.isUsed())
                        voucher.use();
                });
        return this.voucherReactRepository.saveAll(mono).then();
    }
}
