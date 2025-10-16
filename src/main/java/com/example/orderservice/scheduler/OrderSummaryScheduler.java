package com.example.orderservice.scheduler;

import com.example.orderservice.model.Order;
import com.example.orderservice.repositories.OrderRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Component
public class OrderSummaryScheduler {

    private final OrderRepository orderRepository;

    public OrderSummaryScheduler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Runs every minute for demo
    @Scheduled(fixedRate = 60000)
    public void generateDailySummary() throws IOException {
        List<Order> orders = orderRepository.findAll();

        String fileName = "uploads/order-summary-" + LocalDate.now() + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            double total = 0;
            for (Order order : orders) {
                writer.write(order.getProductCode() + "," + order.getQuantity() + "," + order.getPrice() + "\n");
                total += order.getPrice() * order.getQuantity();
            }
            writer.write("Total Revenue: " + total);
        }

        System.out.println("🧾 Daily summary written to " + fileName);
    }
}