package com.example.orderservice.controller;

import com.example.orderservice.model.Order;
import com.example.orderservice.service.BatchOrderService;
import com.example.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final BatchOrderService batchOrderService;

    public OrderController(OrderService orderService, BatchOrderService batchOrderService) {
        this.orderService = orderService;
        this.batchOrderService = batchOrderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order created = orderService.createOrder(order);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Order>> uploadBatch(@RequestParam("file") MultipartFile file) throws Exception {
        // Save uploaded file to uploads folder
        String filePath = "uploads/" + file.getOriginalFilename();
        file.transferTo(new java.io.File(filePath));

        List<Order> savedOrders = batchOrderService.processBatchOrders(filePath);
        return ResponseEntity.ok(savedOrders);
    }
}
