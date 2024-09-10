package com.aict.loworderbackend.controller;

import com.aict.loworderbackend.dto.OrderDTO;
import com.aict.loworderbackend.dto.OrderDetailDTO;
import com.aict.loworderbackend.dto.OrderRequest;
import com.aict.loworderbackend.entity.Order;
import com.aict.loworderbackend.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 특정 가게의 주문 조회
    @GetMapping("/{storeId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByStoreId(@PathVariable Long storeId) {
        List<Order> orders = orderService.findOrdersByStoreId(storeId);
        List<OrderDTO> orderDTOs = orders.stream()
                .map(order -> {
                    List<OrderDetailDTO> detailDTOs = order.getOrderDetails().stream()
                            .map(detail -> new OrderDetailDTO(detail.getDetailId(), detail.getMenuName(), detail.getPrice(), detail.getQuantity()))
                            .collect(Collectors.toList());
                    return new OrderDTO(order.getOrderId(), order.getStoreId(), order.getTotalPrice(), order.getOrderTime(), detailDTOs);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(orderDTOs);
    }

    @PutMapping("/{orderId}/complete")
    public ResponseEntity<String> completeOrder(@PathVariable Long orderId) {
        boolean isUpdated = orderService.updateOrderComplete(orderId);
        if (isUpdated) {
            return ResponseEntity.ok("Order marked as complete.");
        } else {
            return ResponseEntity.badRequest().body("Order not found or update failed.");
        }
    }

    // 주문 생성
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
        Order savedOrder = orderService.createOrder(orderRequest);
        return ResponseEntity.ok(savedOrder);
    }
}