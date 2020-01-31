package es.upm.miw.betca_tpv_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class}) // Not: /error
public class Application {

    // mvn clean spring-boot:run
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
