package com.tananushka.mom.producerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProducerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerAppApplication.class, args);
    }
}
