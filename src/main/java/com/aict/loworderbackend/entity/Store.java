package com.aict.loworderbackend.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "store_name")
    private String storeName;

    private String password;

    @Column(name = "store_type")
    private String storeType;

    // 기본 생성자 (필수, JPA가 엔티티를 로드할 때 필요)
    public Store() {
    }

    // storeId만 초기화하는 생성자
    public Store(Long storeId) {
        this.storeId = storeId;
    }

    // Getters and Setters
    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }
}