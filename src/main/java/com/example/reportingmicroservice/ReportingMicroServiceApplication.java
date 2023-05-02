package com.example.reportingmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ReportingMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportingMicroServiceApplication.class, args);
    }

}
