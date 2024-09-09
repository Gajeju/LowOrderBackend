package com.aict.loworderbackend.repository;

import com.aict.loworderbackend.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    // 특정 주문 ID로 관련된 주문 상세 삭제
    @Transactional
    @Modifying
    @Query("DELETE FROM OrderDetail od WHERE od.order.orderId = :orderId")
    void deleteByOrderId(@Param("orderId") Long orderId);
}