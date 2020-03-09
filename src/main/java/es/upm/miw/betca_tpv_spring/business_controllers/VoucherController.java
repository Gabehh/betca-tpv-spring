package es.upm.miw.betca_tpv_spring.business_controllers;

import es.upm.miw.betca_tpv_spring.business_services.PdfService;
import es.upm.miw.betca_tpv_spring.documents.Voucher;
import es.upm.miw.betca_tpv_spring.dtos.VoucherCreationDto;
import es.upm.miw.betca_tpv_spring.dtos.VoucherSearchDto;
import es.upm.miw.betca_tpv_spring.exceptions.NotFoundException;
import es.upm.miw.betca_tpv_spring.repositories.VoucherReactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class VoucherController {

    private VoucherReactRepository voucherReactRepository;
    private PdfService pdfService;

    @Autowired
    public VoucherController(VoucherReactRepository voucherReactRepository, PdfService pdfService) {
        this.voucherReactRepository = voucherReactRepository;
        this.pdfService = pdfService;
    }

    public Flux<Voucher> searchVoucher(VoucherSearchDto voucherSearchDto) {
        if (voucherSearchDto.getId() == null) {
            return this.voucherReactRepository.findAllByCreationDateBetween(voucherSearchDto.getFirstDate().minusDays(1), voucherSearchDto.getFinalDate().plusDays(1));
        } else
            return this.voucherReactRepository.findByIdAndCreationDateBetween(voucherSearchDto.getId(), voucherSearchDto.getFirstDate().minusDays(1), voucherSearchDto.getFinalDate().plusDays(1));
    }

    public Mono<Voucher> createVoucher(VoucherCreationDto voucherCreationDto) {
        Voucher voucher = new Voucher(voucherCreationDto.getValue());
        return voucherReactRepository.save(voucher);
    }

    public Mono<Voucher> consumeVoucher(String id) {
        return voucherReactRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Voucher code (" + id + ")")))
                .doOnNext(voucher -> {
                    if (!voucher.isUsed()) {
                        voucher.use();
                    }
                })
                .flatMap(voucherReactRepository::save);
    }

    @Transactional
    public Mono<byte[]> printVoucher(String id) {
        Mono<Voucher> voucherReact = voucherReactRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Voucher code (" + id + ")")));

        return pdfService.generateVoucher(voucherReact);
    }

    @Transactional
    public Mono<byte[]> createAndPrintVoucher(VoucherCreationDto voucherCreationDto) {
        return pdfService.generateVoucher(createVoucher(voucherCreationDto));
    }
}
