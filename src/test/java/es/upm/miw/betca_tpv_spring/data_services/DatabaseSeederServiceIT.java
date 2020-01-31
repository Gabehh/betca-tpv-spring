package es.upm.miw.betca_tpv_spring.data_services;

import es.upm.miw.betca_tpv_spring.TestConfig;
import es.upm.miw.betca_tpv_spring.documents.User;
import es.upm.miw.betca_tpv_spring.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class DatabaseSeederServiceIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DatabaseSeederService databaseSeederService;

    @Test
    void testDeleteAllAndInitialize() {
        this.databaseSeederService.deleteAllAndInitialize();
        assertFalse(userRepository.findByMobile("666666001").isPresent());
        this.databaseSeederService.seedDataBaseJava();
        User user = userRepository.findByMobile("666666001").get();
        assertEquals("u001", user.getUsername());
        assertEquals("u001@gmail.com", user.getEmail());
        assertEquals("66666601C", user.getDni());
        assertTrue(user.getRoles().length >= 1);
    }

}
