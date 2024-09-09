package com.aict.loworderbackend.service;

import com.aict.loworderbackend.dto.OrderDetailRequest;
import com.aict.loworderbackend.dto.OrderRequest;
import com.aict.loworderbackend.entity.Order;
import com.aict.loworderbackend.entity.OrderDetail;
import com.aict.loworderbackend.repository.OrderRepository;
import com.aict.loworderbackend.repository.OrderDetailRepository;
import org.apache.catalina.Store;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    // 주문 생성 및 저장
    @Transactional
    public Order createOrder(OrderRequest orderRequest) {
        // 1. Order 객체 생성
        Order order = new Order();
        order.setStoreId(orderRequest.getStoreId());
        order.setOrderTime(LocalDateTime.now());

        // 총합을 저장할 변수
        int totalPrice = 0;

        // 2. Order 엔티티 저장 (주문 정보 먼저 저장, totalPrice는 아직 설정하지 않음)
        Order savedOrder = orderRepository.save(order);

        // 3. OrderDetails 리스트에서 각각의 OrderDetail에 저장된 Order를 설정하고 저장
        for (OrderDetailRequest detailRequest : orderRequest.getOrderDetails()) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(savedOrder);  // OrderDetail에 Order 설정
            detail.setMenuName(detailRequest.getMenuName());
            detail.setPrice(detailRequest.getPrice());
            detail.setQuantity(detailRequest.getQuantity());

            // 4. 총합 계산 (price * quantity)
            totalPrice += detailRequest.getPrice() * detailRequest.getQuantity();

            // 5. OrderDetail 저장
            orderDetailRepository.save(detail);
        }

        // 6. 총합을 Order에 설정하고 업데이트
        savedOrder.setTotalPrice(totalPrice);
        orderRepository.save(savedOrder);  // 수정된 총합을 다시 저장

        // 7. 저장된 Order 반환
        return savedOrder;
    }

    // 모든 주문 조회
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    // 특정 가게(store_id)의 모든 주문 조회
    public List<Order> findOrdersByStoreId(Long storeId) {
        return orderRepository.findOrdersByStoreId(storeId);
    }

    // 특정 주문 ID로 주문 상세 조회
    public Order findOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }
}