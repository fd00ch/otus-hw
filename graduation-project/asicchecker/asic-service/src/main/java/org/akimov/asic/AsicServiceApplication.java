package org.akimov.asic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AsicServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AsicServiceApplication.class, args);
    }
}