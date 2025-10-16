package com.example.orderservice.service;

import com.example.orderservice.event.OrderEventPublisher;
import com.example.orderservice.model.Order;
import com.example.orderservice.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventPublisher eventPublisher;

    public OrderService(OrderRepository orderRepository, OrderEventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.eventPublisher = eventPublisher;
    }

    public Order createOrder(Order order) {
        double price = switch (order.getProductCode()) {
            case "P001" -> 200.0;
            case "P002" -> 450.0;
            default -> throw new RuntimeException("Invalid product code");
        };

        order.setPrice(price);
        order.setStatus("CONFIRMED");
        order.setCreatedAt(LocalDateTime.now());

        Order saved = orderRepository.save(order);

        eventPublisher.publishOrderCreated(saved);

        return saved;
    }
}