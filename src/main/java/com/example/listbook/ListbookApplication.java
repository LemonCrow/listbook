package com.example.listbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ListbookApplication {

    public static void main(String[] args) {

        SpringApplication.run(ListbookApplication.class, args);
    }

}
