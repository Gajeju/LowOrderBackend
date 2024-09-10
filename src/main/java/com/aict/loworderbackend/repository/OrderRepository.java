package com.aict.loworderbackend.repository;

import com.aict.loworderbackend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // 특정 가게의 모든 주문 조회
    @Query("SELECT o FROM Order o WHERE o.storeId = :storeId")
    List<Order> findOrdersByStoreId(@Param("storeId") Long storeId);

    List<Order> findByStoreId(Long storeId);
}