package es.upm.miw.betca_tpv_spring.repositories;

import es.upm.miw.betca_tpv_spring.TestConfig;
import es.upm.miw.betca_tpv_spring.documents.Role;
import es.upm.miw.betca_tpv_spring.documents.User;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestConfig
class UserRepositoryIT {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void seedDb() {
        this.user = new User("666001000", "666001000", "666001000");
        this.userRepository.save(user);
    }

    @Test
    void testFindByMobile() {
        User userBd = userRepository.findByMobile("666001000").get();
        assertEquals("666001000", userBd.getUsername());
        Assert.assertArrayEquals(new Role[]{Role.CUSTOMER}, userBd.getRoles());
    }

    @Test
    void testFindByMobileNull() {
        assertFalse(userRepository.findByMobile(null).isPresent());
    }

    @AfterEach
    void delete() {
        this.userRepository.delete(user);
    }

}
