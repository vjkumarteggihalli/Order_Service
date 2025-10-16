package com.example.orderservice.event;

import com.example.orderservice.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher {

    public void publishOrderCreated(Order order) {
        // Simulated publish - prints JSON-like text to the console
        System.out.println("EVENT: OrderCreated -> id=" + order.getId()
                + ", product=" + order.getProductCode()
                + ", qty=" + order.getQuantity()
                + ", price=" + order.getPrice());
    }
}