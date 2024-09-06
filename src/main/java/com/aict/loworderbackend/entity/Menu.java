package com.aict.loworderbackend.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 증가 설정
    private Long menu_id;

    @Column(name = "store_id", nullable = false)
    private Long storeId;

    @Column(name = "menu_name", nullable = false)
    private String menuName;

    @Column(name = "menu_type", nullable = false)
    private String menuType;

    @Column(name = "price", nullable = false)
    private int price;

    // Getters and Setters

    public Long getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Long menu_id) {
        this.menu_id = menu_id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menu_id=" + menu_id +
                ", storeId=" + storeId +
                ", menuName='" + menuName + '\'' +
                ", menuType='" + menuType + '\'' +
                ", price=" + price +
                '}';
    }
}