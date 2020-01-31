package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.TestConfig;
import es.upm.miw.betca_tpv_spring.documents.Budget;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

@TestConfig
class BudgetReactRepositoryIT {

    @Autowired
    private BudgetReactRepository budgetReactRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    void testReadAll() {
        Budget budget = new Budget(this.ticketRepository.findById("201901121").get().getShoppingList());
        StepVerifier
                .create(this.budgetReactRepository.save(budget))
                .expectNextCount(1)
                .expectComplete()
                .verify();
        StepVerifier
                .create(this.budgetReactRepository.findAll())
                .expectNextCount(1)
                .thenCancel()
                .verify();
        this.budgetReactRepository.delete(budget).block();
    }

}
