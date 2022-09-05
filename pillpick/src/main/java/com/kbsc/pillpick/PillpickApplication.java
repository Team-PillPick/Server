package com.kbsc.pillpick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PillpickApplication {

    public static void main(String[] args) {
        SpringApplication.run(PillpickApplication.class, args);
    }

}
