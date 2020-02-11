package es.upm.miw.betca_tpv_spring.business_controllers;

import es.upm.miw.betca_tpv_spring.TestConfig;
import es.upm.miw.betca_tpv_spring.dtos.CashierClosureInputDto;
import es.upm.miw.betca_tpv_spring.dtos.ShoppingDto;
import es.upm.miw.betca_tpv_spring.dtos.TicketCreationInputDto;
import es.upm.miw.betca_tpv_spring.repositories.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class TicketControllerIT {

    @Autowired
    private TicketController ticketController;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CashierClosureController cashierClosureController;

    @Test
    void testUpdateStockWhenCreateTicket() {
        int stock = this.articleRepository.findById("1").get().getStock();
        StepVerifier
                .create(this.cashierClosureController.createCashierClosureOpened()).expectComplete().verify();
        ShoppingDto shoppingDto =
                new ShoppingDto("1", "prueba", BigDecimal.TEN, 1, BigDecimal.ZERO,
                        BigDecimal.TEN, true);
        TicketCreationInputDto ticketCreationInputDto = new TicketCreationInputDto(null, BigDecimal.TEN
                , BigDecimal.ZERO, BigDecimal.ZERO, Collections.singletonList(shoppingDto), "Nota del ticket...");
        StepVerifier
                .create(this.ticketController.createTicketAndPdf(ticketCreationInputDto))
                .expectNextCount(1)
                .expectComplete()
                .verify();
        assertEquals(new Integer(stock - 1), this.articleRepository.findById("1").get().getStock());
        shoppingDto.setAmount(-1);
        StepVerifier
                .create(this.ticketController.createTicketAndPdf(ticketCreationInputDto))
                .expectNextCount(1)
                .expectComplete()
                .verify();
        assertEquals(new Integer(stock), this.articleRepository.findById("1").get().getStock());
        StepVerifier
                .create(this.cashierClosureController.close(new CashierClosureInputDto())).expectComplete().verify();
    }
}
