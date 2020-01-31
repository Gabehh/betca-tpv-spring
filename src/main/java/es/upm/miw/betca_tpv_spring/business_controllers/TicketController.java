package es.upm.miw.betca_tpv_spring.business_controllers;

import es.upm.miw.betca_tpv_spring.business_services.PdfService;
import es.upm.miw.betca_tpv_spring.documents.*;
import es.upm.miw.betca_tpv_spring.dtos.ShoppingDto;
import es.upm.miw.betca_tpv_spring.dtos.TicketCreationInputDto;
import es.upm.miw.betca_tpv_spring.exceptions.NotFoundException;
import es.upm.miw.betca_tpv_spring.repositories.ArticleReactRepository;
import es.upm.miw.betca_tpv_spring.repositories.CashierClosureReactRepository;
import es.upm.miw.betca_tpv_spring.repositories.TicketReactRepository;
import es.upm.miw.betca_tpv_spring.repositories.UserReactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
public class TicketController {

    private ArticleReactRepository articleReactRepository;
    private TicketReactRepository ticketReactRepository;
    private UserReactRepository userReactRepository;
    private CashierClosureReactRepository cashierClosureReactRepository;
    private PdfService pdfService;

    @Autowired
    public TicketController(TicketReactRepository ticketReactRepository, UserReactRepository userReactRepository,
                            ArticleReactRepository articleReactRepository, CashierClosureReactRepository cashierClosureReactRepository,
                            PdfService pdfService) {
        this.ticketReactRepository = ticketReactRepository;
        this.userReactRepository = userReactRepository;
        this.articleReactRepository = articleReactRepository;
        this.cashierClosureReactRepository = cashierClosureReactRepository;
        this.pdfService = pdfService;
    }

    private Mono<Integer> nextIdStartingDaily() {
        return ticketReactRepository.findFirstByOrderByCreationDateDescIdDesc()
                .map(ticket -> {
                    if (ticket.getCreationDate().isAfter(LocalDateTime.of(LocalDate.now(), LocalTime.MIN))) {
                        return ticket.simpleId() + 1;
                    } else {
                        return 1;
                    }
                })
                .switchIfEmpty(Mono.just(1));
    }

    private Mono<Void> updateArticlesStock(TicketCreationInputDto ticketCreationDto) {
        Flux<Article> articlesFlux = Flux.empty();
        for (ShoppingDto shoppingDto : ticketCreationDto.getShoppingCart()) {
            Mono<Article> articleReact = this.articleReactRepository.findById(shoppingDto.getCode())
                    .switchIfEmpty(Mono.error(new NotFoundException("Article (" + shoppingDto.getCode() + ")")))
                    .map(article -> {
                        article.setStock(article.getStock() - shoppingDto.getAmount());
                        return article;
                    });
            articlesFlux = Flux.merge(articleReactRepository.saveAll(articleReact));
        }
        return articlesFlux.then();
    }

    private Mono<Ticket> createTicket(TicketCreationInputDto ticketCreationDto) {
        Shopping[] shoppingArray = ticketCreationDto.getShoppingCart().stream().map(shoppingDto ->
                new Shopping(shoppingDto.getAmount(), shoppingDto.getDiscount(),
                        shoppingDto.isCommitted() ? ShoppingState.COMMITTED : ShoppingState.NOT_COMMITTED,
                        shoppingDto.getCode(), shoppingDto.getDescription(), shoppingDto.getRetailPrice()))
                .toArray(Shopping[]::new);
        Ticket ticket = new Ticket(0, ticketCreationDto.getCard(), ticketCreationDto.getCash(),
                ticketCreationDto.getVoucher(), shoppingArray, null,
                ticketCreationDto.getNote());
        Mono<User> user = this.userReactRepository.findByMobile(ticketCreationDto.getUserMobile())
                .doOnNext(ticket::setUser);
        Mono<Integer> nextId = this.nextIdStartingDaily()
                .doOnNext(ticket::setId);
        Mono<CashierClosure> cashierClosureReact = this.cashierClosureReactRepository.findFirstByOrderByOpeningDateDesc()
                .map(cashierClosure -> {
                    cashierClosure.voucher(ticketCreationDto.getVoucher());
                    cashierClosure.cash(ticketCreationDto.getCash());
                    cashierClosure.card(ticketCreationDto.getCard());
                    return cashierClosure;
                });
        Mono<Void> cashierClosureUpdate = this.cashierClosureReactRepository.saveAll(cashierClosureReact).then();

        return Mono.when(user, nextId, updateArticlesStock(ticketCreationDto), cashierClosureUpdate)
                .then(this.ticketReactRepository.save(ticket));
    }


    public Mono<byte[]> createTicketAndPdf(TicketCreationInputDto ticketCreationDto) {
        return pdfService.generateTicket(this.createTicket(ticketCreationDto));
    }

}
