package com.aict.loworderbackend.dto;

import java.util.List;

public class OrderRequest {
    private Long storeId;
    private List<OrderDetailRequest> orderDetails;

    // Getters and Setters

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public List<OrderDetailRequest> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailRequest> orderDetails) {
        this.orderDetails = orderDetails;
    }
}