package com.example.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrderProcessingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderProcessingServiceApplication.class, args);
    }
}
