package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import com.example.orderservice.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BatchOrderService {

    private final OrderRepository orderRepository;

    public BatchOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> processBatchOrders(String filePath) throws IOException {
        List<Order> orders = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // skip header if present
            line = br.readLine();
            if (line == null) return orders;

            // If the header does not contain "productCode" replace logic accordingly.
            if (!line.toLowerCase().contains("product")) {
                // first line is data
                String[] fields = line.split(",");
                orders.add(toOrder(fields));
            }

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                orders.add(toOrder(fields));
            }
        }

        return orderRepository.saveAll(orders);
    }

    private Order toOrder(String[] fields) {
        Order order = new Order();
        order.setProductCode(fields[0].trim());
        order.setQuantity(Integer.parseInt(fields[1].trim()));

        double price = switch (order.getProductCode()) {
            case "P001" -> 100.0;
            case "P002" -> 200.0;
            default -> 0.0;
        };
        order.setPrice(price);
        order.setStatus("CONFIRMED");
        order.setCreatedAt(LocalDateTime.now());
        return order;
    }
}