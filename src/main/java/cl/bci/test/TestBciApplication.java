package cl.bci.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class TestBciApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestBciApplication.class, args);
    }

}
