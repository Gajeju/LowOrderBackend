package com.aict.loworderbackend.controller;

import com.aict.loworderbackend.dto.OrderRequest;
import com.aict.loworderbackend.entity.Order;
import com.aict.loworderbackend.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 특정 가게의 주문 조회
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<Order>> getOrdersByStoreId(@PathVariable Long storeId) {
        List<Order> orders = orderService.findOrdersByStoreId(storeId);
        return ResponseEntity.ok(orders);
    }

    // 주문 생성
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
        Order savedOrder = orderService.createOrder(orderRequest);
        return ResponseEntity.ok(savedOrder);
    }
}