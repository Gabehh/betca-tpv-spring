package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.TestConfig;
import es.upm.miw.betca_tpv_spring.data_services.DatabaseSeederService;
import es.upm.miw.betca_tpv_spring.documents.Order;
import es.upm.miw.betca_tpv_spring.documents.OrderLine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class OrderRepositoryIT {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DatabaseSeederService databaseSeederService;

    @Test
    void testReadAll() {
        assertTrue(this.orderRepository.findAll().size() > 0);

        Order order1 = this.orderRepository.findAll().stream()
                .filter(order -> "order1".equals(order.getDescription())).findFirst().get();

        assertNotNull(order1.getOpeningDate());
        assertNull(order1.getClosingDate());
        assertEquals("pro1", order1.getProvider().getCompany());
        assertEquals(new Integer(10), order1.getOrderLines()[0].getRequiredAmount());
    }

    @Test
    void testClose(){
        assertTrue(this.orderRepository.findAll().size() > 0);

        Order order1 = this.orderRepository.findAll().stream()
                .filter(order -> "order1".equals(order.getDescription())).findFirst().get();
        order1.close();
        assertNotNull(order1.getClosingDate());
        assertEquals(new Integer(8),order1.getOrderLines()[1].getFinalAmount());
        this.databaseSeederService.deleteAllAndInitializeAndSeedDataBase();
    }

}
