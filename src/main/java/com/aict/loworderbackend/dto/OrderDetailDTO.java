package com.aict.loworderbackend.dto;

public class OrderDetailDTO {
    private Long detailId;
    private String menuName;
    private int price;
    private int quantity;

    // Constructor, Getters, Setters
    public OrderDetailDTO(Long detailId, String menuName, int price, int quantity) {
        this.detailId = detailId;
        this.menuName = menuName;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}