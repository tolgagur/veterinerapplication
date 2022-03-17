package com.ozgury.veterinerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
public class VeterinerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(VeterinerAppApplication.class, args);
    }

}
