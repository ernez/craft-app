package com.ernez.craftapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
public class CraftAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CraftAppApplication.class, args);
    }

}
